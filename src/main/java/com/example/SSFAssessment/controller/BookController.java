package com.example.SSFAssessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;


@RequestMapping(path = "/book", produces=MediaType.TEXT_HTML_VALUE)
public class BookController {

    @GetMapping(path ="{worksId}" )
    public String getDetails(String details, Model model){
        
        model.addAttribute("details", details);
        return "detail";

    }
    
}
