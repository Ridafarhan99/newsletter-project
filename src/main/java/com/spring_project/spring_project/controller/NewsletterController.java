package com.spring_project.spring_project.controller;

import com.spring_project.spring_project.entity.NewsletterEntry;
import com.spring_project.spring_project.service.NewsletterEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsletterController {
    @Autowired
    private NewsletterEntryService newsletterEntryService;

    //Recorded stored inside this hashmaped
    private Map<Long, NewsletterEntry> newsletterEntries= new HashMap<>();

    @GetMapping
    public List<NewsletterEntry> getAll(){
        return newsletterEntryService.getAllNewsletters();
    }


    @PostMapping
    public NewsletterEntry createEntry(@RequestBody NewsletterEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        newsletterEntryService.saveEntry(myEntry);
        return myEntry;
    }


    @GetMapping("id/{myID}")
    public NewsletterEntry getNewsletterEntryById(@PathVariable ObjectId myID){
        return newsletterEntryService.findById(myID).orElse( null);
    }


    @PutMapping("id/{myID}")
    public NewsletterEntry updateNewsletterEntryById(@PathVariable ObjectId myID, @RequestBody NewsletterEntry newEntry){
        NewsletterEntry oldEntry = newsletterEntryService.findById(myID).orElse(null);
        if(oldEntry != null){
            oldEntry.setContent(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
        }

        newsletterEntryService.saveEntry(newEntry);
        return newEntry;
    }


    @DeleteMapping("id/{myID}")
    public boolean deleteNewsletterEntryById(@PathVariable ObjectId myID){
        newsletterEntryService.deleteById(myID);
        return true;

    }
}
