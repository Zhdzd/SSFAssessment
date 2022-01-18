package com.example.SSFAssessment.service;

import java.util.List;

import com.example.SSFAssessment.model.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private String urlSearch = "http://openlibrary.org/search.json";


    public List<Book> search (String searchTerm){
        
        String url = UriComponentsBuilder
            .fromUriString(urlSearch)
            .queryParam("title", searchTerm.trim().replace(" ","+"))
            .queryParam("key", )
            .queryParam("limit", 20)
            .toUriString();

        logger.info("url: " +url);

        RequestEntity<Void> req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        


    }
}
