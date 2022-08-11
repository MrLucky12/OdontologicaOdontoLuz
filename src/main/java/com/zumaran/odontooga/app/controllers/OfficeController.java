package com.zumaran.odontooga.app.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zumaran.odontooga.app.models.entity.Dentist;
import com.zumaran.odontooga.app.models.entity.Office;
import com.zumaran.odontooga.app.models.service.IDentistService;
import com.zumaran.odontooga.app.models.service.IOfficeService;
import com.zumaran.odontooga.app.models.service.IUploadFileService;
import com.zumaran.odontooga.app.util.paginator.PageRender;

@Controller
@SessionAttributes("office")
@RequestMapping({"/admin/office"})
public class OfficeController {
    
    @Autowired
    private IOfficeService officeService;

    @Autowired
    private IDentistService dentistService;

    @Autowired
    private IUploadFileService UFS;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestParam( name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 3);
        Page<Office> offices = officeService.getAll(pageRequest);
        PageRender<Office> pageRender = new PageRender<Office>( "/admin/office/index", offices);

        model.addAttribute("title", "Consultorios");
        //model.addAttribute("products", productService.getAll());
        model.addAttribute("offices", offices);
        model.addAttribute("page", pageRender);
        return "office/index";
    }

    //VIEW | ADD 
    @RequestMapping(value = "/add")
    public String add(Map<String, Object> model) {
        Office office = new Office();
        List<Dentist> dentists = dentistService.getAll();
        model.put("dentists", dentists);
        model.put("office", office);
        model.put("title", "Formulario Consultorio");
        return "office/add";
    }

    //METHOD | SAVE VALUE
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(@Valid Office office, BindingResult result, Model model,
                    @RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status) {
        if(result.hasErrors()) {
            model.addAttribute("title", "Completar o corregir campos!");
            //DENTIST OPTION
            List<Dentist> dentists = dentistService.getAll();
            model.addAttribute("dentists", dentists);
            return "office/add";
        }

        if (!photo.isEmpty()) {

			if (office.getId() != null && office.getId() > 0 && office.getPhoto() != null && office.getPhoto().length() > 0) 
            { UFS.delete(office.getPhoto()); }

			String uniqueFilename = null;
			try { uniqueFilename = UFS.copy(photo); } catch (IOException e) { e.printStackTrace(); }

			flash.addFlashAttribute("info", "File Upload '" + uniqueFilename + "'");
			office.setPhoto(uniqueFilename);
		}

        String mensajeFlash = (office.getId() != null) ? "Consultorio editado exitosamente" : "Consultorio creado !!!";

        officeService.save(office);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/admin/office/index";
    }

    // lorem
    

}
