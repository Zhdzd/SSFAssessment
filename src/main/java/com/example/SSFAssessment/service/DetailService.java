package com.example.SSFAssessment.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import com.example.SSFAssessment.model.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        private String UrlDetails = "https://openlibrary.org/";

    public Book book(String worksId){

        String url = UrlDetails +  worksId + (".json");

        RequestEntity<Void> req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        
        String body = resp.getBody();
        try(InputStream is = new ByteArrayInputStream(body.getBytes())){
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();
            String description = data.getString("description");

            JsonArray excerptArray = data.getJsonArray("excerpts");
            JsonObject excerptObject = excerptArray.getJsonObject(0);

        



            
            
            
        }
        





    }
}
