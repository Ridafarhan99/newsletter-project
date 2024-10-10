package com.spring_project.spring_project.repository;

import com.spring_project.spring_project.entity.NewsletterEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsletterRepository extends MongoRepository<NewsletterEntry, ObjectId> {

}
