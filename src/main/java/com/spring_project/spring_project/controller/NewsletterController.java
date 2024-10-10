package com.spring_project.spring_project.controller;

import com.spring_project.spring_project.entity.NewsletterEntry;
import com.spring_project.spring_project.service.NewsletterEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/news")
public class NewsletterController {
    @Autowired
    private NewsletterEntryService newsletterEntryService;

    //Recorded stored inside this hashmaped
    private Map<Long, NewsletterEntry> newsletterEntries= new HashMap<>();

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<NewsletterEntry> allData = newsletterEntryService.getAllNewsletters();
        if (allData != null && !allData.isEmpty()){
            return new ResponseEntity<>(allData, HttpStatus.OK);
        }
        return new ResponseEntity<>(allData, HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<NewsletterEntry> createEntry(@RequestBody NewsletterEntry myEntry){
        try {
            myEntry.setDate(LocalDateTime.now());
            newsletterEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("id/{myID}")
    public ResponseEntity<NewsletterEntry> getNewsletterEntryById(@PathVariable ObjectId myID){
        Optional<NewsletterEntry> newsletterEntry = newsletterEntryService.findById(myID);
        if(newsletterEntry.isPresent()){
            return new ResponseEntity<>(newsletterEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }


    @PutMapping("id/{myID}")
    public ResponseEntity<?> updateNewsletterEntryById(@PathVariable ObjectId myID, @RequestBody NewsletterEntry newEntry){
        NewsletterEntry oldEntry = newsletterEntryService.findById(myID).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            newsletterEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }

        return new ResponseEntity<>(oldEntry,HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{myID}")
    public ResponseEntity<?> deleteNewsletterEntryById(@PathVariable ObjectId myID){
        newsletterEntryService.deleteById(myID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
