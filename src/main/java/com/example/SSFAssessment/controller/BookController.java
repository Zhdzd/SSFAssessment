package com.example.SSFAssessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SSFAssessment.model.Book;
import com.example.SSFAssessment.service.DetailService;

import org.springframework.http.MediaType;


@RequestMapping(path = "/book", produces=MediaType.TEXT_HTML_VALUE)
public class BookController {

    private DetailService detailService;

    @GetMapping(path ="{worksId}" )
    public String getDetails(@PathVariable("worksId")String worksId, Model model){
        
        Book book = detailService.getBook(worksId);
        model.addAttribute("worksId", worksId);
        model.addAttribute("book", book);
        
        return "detail";

    }
    
}
