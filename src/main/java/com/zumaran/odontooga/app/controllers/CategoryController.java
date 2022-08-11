package com.zumaran.odontooga.app.controllers;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.zumaran.odontooga.app.models.entity.Category;
import com.zumaran.odontooga.app.models.service.ICategoryService;

@Controller
@SessionAttributes("category")
@RequestMapping({"/admin/category"})
public class CategoryController {
    
    @Autowired
    private ICategoryService categoryDAO;

    //VIEW | ALL DATA
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        //Category category = new Category();
        //model.put("category", category);
        model.addAttribute("title", "Categoria de Productos");
        model.addAttribute("categories", categoryDAO.getAll());
        return "category/index";
    }

    // METHOD | COUNT PRODUCT BY CATEGORY
    @GetMapping(value = "/count/{id}")
    public String countProductsByCategory(@PathVariable Long id) {
        return categoryDAO.countProductsByCategory(id);
    }

    // METHOD | CATEGORY BY ID
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String productByArea(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        if (id>0) {
            model.put("products", categoryDAO.getProductByCategory(id));
            return "category/extra_1";
        }
        return "redirect:/area/index";
    }

     //VIEW | ADD 
     @RequestMapping(value = "/add")
     public String add(Map<String, Object> model) {
         Category category = new Category();
         model.put("category", category);
         model.put("title", "Formulario Categoria");
         model.put("localDate", LocalDate.now());
         return "category/add";
     }

    //METHOD | SAVE VALUE
    //@PostMapping("/add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(@Valid Category category, BindingResult result, Model model, SessionStatus status) {
         if(result.hasErrors()) {
             model.addAttribute("title", "Completar Correctamente los campos!");
             return "category/add";
         }
        categoryDAO.save(category);
        status.setComplete();
        model.addAttribute("title", "Categoria anadida");
        return "redirect:/admin/category/index";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value="id") Long id) {
        if(id>0) { categoryDAO.delete(id);}
        return "redirect:/admin/category/index";
    }

    @RequestMapping(value="/add/{id}")
    public String edit(@PathVariable(value="id") Long id, Map<String, Object> model) {
        Category category = null;
        if(id>0) {category = categoryDAO.get(id);}
        else { return "redirect:/admin/category/index";}
        model.put("category", category);
        model.put("title", "Editar Area");
        return "category/add";
    }


}
