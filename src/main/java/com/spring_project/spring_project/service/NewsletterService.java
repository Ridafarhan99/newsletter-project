package com.spring_project.spring_project.service;

import com.spring_project.spring_project.entity.NewsletterEntry;
import com.spring_project.spring_project.repository.NewsletterRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NewsletterService {

    @Autowired
    private NewsletterRepository newsletterRepository;

    public List<NewsletterEntry> allNews(){
        return newsletterRepository.findAll();
    }

    public Optional<NewsletterEntry> newsById(ObjectId id){
        return newsletterRepository.findById(id);
    }

    public NewsletterEntry createNewsLetter(String title, String content){
        NewsletterEntry newsletterEntry = new NewsletterEntry();
        newsletterEntry.setTitle(title);
        newsletterEntry.setContent(content);
        newsletterEntry.setDate(LocalDateTime.now());

        return newsletterRepository.save(newsletterEntry);
    }
}
// controller -> service -> repository