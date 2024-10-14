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

    public NewsletterEntry updateNewsletterByTimestamp(long timestamp, NewsletterEntry updatedEntry) {
        // Find the entry by timestamp
        List<NewsletterEntry> allEntries = newsletterRepository.findAll();
        for (NewsletterEntry entry : allEntries) {
            if (entry.getId().getTimestamp() == timestamp) {
                // Update the title and content
                entry.setTitle(updatedEntry.getTitle());
                entry.setContent(updatedEntry.getContent());

                // Save the updated entry
                return newsletterRepository.save(entry);
            }
        }
        // Return null if no entry found with the given timestamp
        return null;
    }

    public boolean deleteNewsletterByTimestamp(long timestamp){
        List<NewsletterEntry> allEntries = newsletterRepository.findAll();
        for (NewsletterEntry entry : allEntries) {
            if (entry.getId().getTimestamp() == timestamp) {
                // Delete the entry by its ID (or timestamp)
                newsletterRepository.delete(entry);
                return true; // Return true if the entry was successfully deleted
            }
        }
        // Return false if no entry was found with the given timestamp
        return false;
    }
}
// controller -> service -> repository