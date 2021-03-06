package com.example.SSFAssessment.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.SSFAssessment.model.Book;
import com.example.SSFAssessment.repositories.BookRepository;
import com.fasterxml.jackson.databind.cfg.ContextAttributes.Impl;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class DetailService {
        private static final Logger logger = LoggerFactory.getLogger(DetailService.class);
        private String UrlDetails = "https://openlibrary.org/works/";
        
    @Autowired
    private BookRepository bookRepo; 

    public Book getBook(String worksId){
        // if worksId in cache {
            // take data from cache
        // } else {} 
        Optional<String> opt = bookRepo.get(worksId);
        if(opt.isEmpty()) {
            String url = UrlDetails +  worksId + (".json");
            logger.info("URL has been set" + url);
            RequestEntity<Void> req = RequestEntity.get(url).build();
            RestTemplate template = new RestTemplate();
            ResponseEntity<String> resp = template.exchange(req, String.class);
            
            String body = resp.getBody();
            try(InputStream is = new ByteArrayInputStream(body.getBytes())){
                JsonReader reader = Json.createReader(is);
                JsonObject data = reader.readObject();
                String description = data.getString("description");
                String title = data.getString("title");
    
                logger.info(">> description taken as string");
    
                JsonArray excerptArray = data.getJsonArray("excerpts");
                logger.info(">>JsonArray created....");
                JsonObject excerptObject = excerptArray.getJsonObject(0);
                logger.info(">>>excerptObject created");
                String excerpt = excerptObject.getString("excerpt");
    
    
                Book bookDetails = new Book();
                bookDetails.setTitle(title);
                bookDetails.setDescription(description);
                bookDetails.setExcerpt(excerpt);
    
                List<String> forCache = new ArrayList<>();
                forCache.add(bookDetails.getTitle());
                forCache.add(bookDetails.getDescription());
                forCache.add(bookDetails.getExcerpt());

                // save to cache 
                bookRepo.save(worksId, forCache.toString());

                return  bookDetails;
            } catch(Exception ex){
                ex.printStackTrace();
                return null;
            }
        } else {
            // get from ccache
            // put into book
            // return book
            return null;
        }
    }
}
