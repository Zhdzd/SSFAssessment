package com.example.SSFAssessment.controller;

import com.example.SSFAssessment.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping(path = "/search")
public class SearchController {
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

   // @Autowired
    //private BookService bookService;

    @GetMapping
    public String getBooks(@RequestParam(required=true) String book, Model model){
        model.addAttribute("book", book.toUpperCase());
        return "result";
    }
    
}