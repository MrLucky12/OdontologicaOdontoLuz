package com.zumaran.odontooga.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zumaran.odontooga.app.models.entity.Dentist;
import com.zumaran.odontooga.app.models.service.IDentistService;
import com.zumaran.odontooga.app.models.service.IUploadFileService;
import com.zumaran.odontooga.app.util.paginator.PageRender;

@Controller
@SessionAttributes("dentist")
@RequestMapping({"/admin/dentist"})
public class DentistController {
    
    @Autowired
    private IDentistService dentistService;

    @Autowired
    private IUploadFileService UFS;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
		Page<Dentist> dentists = dentistService.findAll(pageRequest);
		PageRender<Dentist> pageRender = new PageRender<Dentist>("/admin/dentist/index", dentists);

        model.addAttribute("title", "Odontologos");
        model.addAttribute("dentists", dentists);
        model.addAttribute("page", pageRender);
        return "dentist/index";
    }

    // VIEW PHOTO
    @GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> photo(@PathVariable String filename) {

		Resource resource = null;

		try { resource = UFS.load(filename); } catch (MalformedURLException e) { e.printStackTrace(); }

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
	}

    // DENTIST DETAIL
    @GetMapping(value = "/show/{id}")
	public String showDentist(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Dentist dentist = dentistService.get(id);
		if (dentist == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/admin/dentist/index";
		}
		model.put("item", dentist);
		model.put("titulo", "Detalle Odontologo: " + dentist.getName() + " " + dentist.getLastName());
		return "dentist/show";
	}

    //VIEW | ADD 
    @RequestMapping(value = "/add")
    public String add(Map<String, Object> model) {
        Dentist dentist = new Dentist();
        //List<Category> category = categoryService.getAll();
        //model.put("categories", category);
        model.put("dentist", dentist);
        model.put("title", "Formulario Odontologo");
        return "dentist/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
	public String save(@Valid Dentist dentist, BindingResult result, Model model,
			@RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Odontologo");
			return "dentist/add";
		}

		if (!photo.isEmpty()) {

			if (dentist.getId() != null && dentist.getId() > 0 && dentist.getPhoto() != null && dentist.getPhoto().length() > 0) 
            { UFS.delete(dentist.getPhoto()); }

			String uniqueFilename = null;
			try { uniqueFilename = UFS.copy(photo); } catch (IOException e) { e.printStackTrace(); }

			flash.addFlashAttribute("info", "File Upload '" + uniqueFilename + "'");
			dentist.setPhoto(uniqueFilename);
		}

		String mensajeFlash = (dentist.getId() != null) ? "Odontologo editado con éxito!" : "Odontologo creado con éxito!";

		dentistService.save(dentist);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/admin/dentist/index";
	}





}
