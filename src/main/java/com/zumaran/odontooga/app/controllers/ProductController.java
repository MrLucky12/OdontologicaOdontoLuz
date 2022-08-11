package com.zumaran.odontooga.app.controllers;

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
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zumaran.odontooga.app.models.entity.*;
import com.zumaran.odontooga.app.models.service.ICategoryService;
import com.zumaran.odontooga.app.models.service.IProductService;
import com.zumaran.odontooga.app.util.paginator.PageRender;

@Controller
@SessionAttributes("product")
@RequestMapping({"/admin/product"})
public class ProductController {
    
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestParam( name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 4);
        Page<Product> products = productService.getAll(pageRequest);
        PageRender<Product> pageRender = new PageRender<Product>( "/admin/product/index", products);

        model.addAttribute("title", "Inventario");
        //model.addAttribute("products", productService.getAll());
        model.addAttribute("products", products);
        model.addAttribute("page", pageRender);
        return "product/index";
    }

    //VIEW | ADD 
    @RequestMapping(value = "/add")
    public String add(Map<String, Object> model) {
        Product product = new Product();
        List<Category> category = categoryService.getAll();
        model.put("categories", category);
        model.put("product", product);
        model.put("title", "Formulario Employee");
        return "product/add";
    }

    //METHOD | SAVE VALUE
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(@Valid Product product, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if(result.hasErrors()) {
            model.addAttribute("title", "Completar o corregir campos!");
            //CATEGORY OPTION
            List<Category> category = categoryService.getAll();
            model.addAttribute("categories", category);
            return "product/add";
        }

        String mensajeFlash = (product.getId() != null) ? "Producto editado exitosamente" : "Producto creado !!!";

        productService.save(product);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/admin/product/index";
    }


}
