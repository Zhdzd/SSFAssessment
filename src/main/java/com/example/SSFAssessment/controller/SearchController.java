package com.example.SSFAssessment.controller;

import java.util.List;

import com.example.SSFAssessment.model.Book;
import com.example.SSFAssessment.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;



@Controller
@RequestMapping(path = "/search", produces=MediaType.TEXT_HTML_VALUE)
public class SearchController {
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

   @Autowired
    private BookService bookService;

    @GetMapping
    public String getBooks(@RequestParam(required=true,name="book") String searchBook, Model model){
        
        String bookForQuery = searchBook.replace(" ", "+");
        List<Book> results = bookService.search(bookForQuery);

    
        model.addAttribute("searchBook", searchBook.toUpperCase());
        model.addAttribute("results" , results);
        return "result";
    }
    
}
