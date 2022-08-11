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

import com.zumaran.odontooga.app.models.entity.Client;
import com.zumaran.odontooga.app.models.service.IClientService;
import com.zumaran.odontooga.app.models.service.IUploadFileService;
import com.zumaran.odontooga.app.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
@RequestMapping({"/admin/client"})
public class ClientController {

    @Autowired
    private IClientService clientService;

    @Autowired
    private IUploadFileService UFS;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
		Page<Client> clients = clientService.findAll(pageRequest);
		PageRender<Client> pageRender = new PageRender<Client>("/admin/client/index", clients);

        model.addAttribute("title", "Pacientes");
        model.addAttribute("clients", clients);
        model.addAttribute("page", pageRender);
        return "client/index";
    }

    // VIEW PHOTO
    @GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> photo(@PathVariable String filename) {

		Resource resource = null;

		try { resource = UFS.load(filename); } catch (MalformedURLException e) { e.printStackTrace(); }

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
	}

    //VIEW | ADD 
    @RequestMapping(value = "/add")
    public String add(Map<String, Object> model) {
        Client client = new Client();
        //List<Category> category = categoryService.getAll();
        //model.put("categories", category);
        model.put("client", client);
        model.put("title", "Formulario Paciente");
        return "client/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
	public String save(@Valid Client client, BindingResult result, Model model,
			@RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Paciente");
			return "client/add";
		}

		if (!photo.isEmpty()) {

			if (client.getId() != null && client.getId() > 0 && client.getPhoto() != null && client.getPhoto().length() > 0) 
            { UFS.delete(client.getPhoto()); }

			String uniqueFilename = null;
			try { uniqueFilename = UFS.copy(photo); } catch (IOException e) { e.printStackTrace(); }

			flash.addFlashAttribute("info", "File Upload '" + uniqueFilename + "'");
			client.setPhoto(uniqueFilename);
		}

		String mensajeFlash = (client.getId() != null) ? "Odontologo editado con éxito!" : "Odontologo creado con éxito!";

		clientService.save(client);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/admin/client/index";
	}


}
