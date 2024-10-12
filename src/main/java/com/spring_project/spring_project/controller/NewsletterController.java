package com.spring_project.spring_project.controller;

import com.spring_project.spring_project.entity.NewsletterEntry;
import com.spring_project.spring_project.service.NewsletterService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/news")
public class NewsletterController {

    @Autowired
    private NewsletterService newsletterService;

    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<NewsletterEntry>> allMovies(){
        return new ResponseEntity<List<NewsletterEntry>>(newsletterService.allNews(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Optional<NewsletterEntry>> getNewsById(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<NewsletterEntry>>(newsletterService.newsById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NewsletterEntry> createNewsletter(@RequestBody Map<String, String> news){
        String title = news.get("title");
        String content = news.get("content");

        NewsletterEntry newsletterEntry = newsletterService.createNewsLetter(title, content);

        return new ResponseEntity<>(newsletterEntry, HttpStatus.CREATED);
    }

}
// controller -> service -> repository