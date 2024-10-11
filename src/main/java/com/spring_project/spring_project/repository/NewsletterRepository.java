package com.spring_project.spring_project.repository;

import com.spring_project.spring_project.entity.NewsletterEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsletterRepository extends MongoRepository<NewsletterEntry, ObjectId> {

}
// controller -> service -> repository