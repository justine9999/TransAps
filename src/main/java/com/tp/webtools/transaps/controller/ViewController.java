package com.tp.webtools.transaps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping("/")
    String home(ModelMap modal) {
        modal.addAttribute("title","App CRUD Example");
        return "index";
    }
 
    @RequestMapping("/partials/{page}")
    String partialHandler(@PathVariable("page") final String page) {
        return page;
    }
 
}
