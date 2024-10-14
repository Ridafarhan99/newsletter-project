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
@CrossOrigin(origins = "*")
public class NewsletterController {

    @Autowired
    private NewsletterService newsletterService;

    @GetMapping
    public ResponseEntity<List<NewsletterEntry>> allMovies(){
        return new ResponseEntity<List<NewsletterEntry>>(newsletterService.allNews(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
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

    @PutMapping("/{timestamp}")
    public ResponseEntity<NewsletterEntry> updateNewsletter(
            @PathVariable String timestamp, @RequestBody NewsletterEntry newsDetails
    ) {
        System.out.println("ID: "+timestamp);
        System.out.println("newsDetails: "+newsDetails);

        // Set the ID for the entry being updated
        // Convert the timestamp from String to long
        long timestampLong;
        try {
            timestampLong = Long.parseLong(timestamp);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Invalid timestamp format
        }

        // Call the service to update the newsletter by timestamp
        NewsletterEntry updatedEntry = newsletterService.updateNewsletterByTimestamp(timestampLong, newsDetails);

        if (updatedEntry != null) {
            return new ResponseEntity<>(updatedEntry, HttpStatus.OK); // Return the updated entry
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No entry found with that timestamp
        }
    }
}
// controller -> service -> repository