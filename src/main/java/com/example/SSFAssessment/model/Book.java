package com.example.SSFAssessment.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Book {
    private String worksId;
    private String title;
    private String description;
    private String excerpt;
    private boolean cached;
    private String bookCover;

    public String getWorksId(){
        return worksId;
    }
    public void setWorksId(String worksId){
        this.worksId = worksId;
    }
 
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public boolean isCached() {
        return cached;
    }

    public void setCached(boolean cached) {
        this.cached = cached;
    }
    public JsonObject toJson(){
        return Json.createObjectBuilder()
        .add("title", title)
        .add("key", worksId)
        .build();
    }
    public static Book create(JsonObject jObj){
        Book book = new Book();
        book.setTitle(jObj.getString("title"));
        book.setWorksId(jObj.getString("key"));
        return book;
    }
    public static Book create(String jsonString){
        try(InputStream is = new ByteArrayInputStream(jsonString.getBytes())){
            JsonReader reader = Json.createReader(is);
            return create(reader.readObject());
        } catch (Exception ex){}
        return new Book();
        }
    public String toString(){
        return this.toJson().toString();
    }

}
