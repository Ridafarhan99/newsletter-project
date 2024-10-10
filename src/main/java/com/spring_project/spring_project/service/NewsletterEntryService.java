package com.spring_project.spring_project.service;

import com.spring_project.spring_project.entity.NewsletterEntry;
import com.spring_project.spring_project.repository.NewsletterRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsletterEntryService {
    @Autowired
    private NewsletterRepository newsletterRepository;

    public List<NewsletterEntry> getAllNewsletters() {
        return newsletterRepository.findAll();
    }

    public void saveEntry(NewsletterEntry newsletterEntry){
        newsletterRepository.save(newsletterEntry);
    }

    public Optional<NewsletterEntry> findById(ObjectId id){
        return newsletterRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        newsletterRepository.deleteById(id);
    }
}
// controller -> service -> repository