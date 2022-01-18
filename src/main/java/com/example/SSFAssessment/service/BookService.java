package com.example.SSFAssessment.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import com.example.SSFAssessment.model.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private String urlSearch = "http://openlibrary.org/search.json";


    public List<Book> search (String searchTerm){
        
        String url = UriComponentsBuilder
            .fromUriString(urlSearch)
            .queryParam("title", searchTerm.trim().replace(" ","+"))
            .queryParam("fields", "key+title")
            .queryParam("limit", "20")
            .toUriString();

        logger.info("url: " +url);
        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        if(resp.getStatusCode() !=HttpStatus.OK)
            throw new IllegalArgumentException("Error");

            String body = resp.getBody();
        try(InputStream is = new ByteArrayInputStream(body.getBytes())){
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();
            JsonArray searchResults = data.getJsonArray("");
          
        }catch (Exception ex){}
        


    }
}
