package com.spring_project.spring_project.repository;

import com.spring_project.spring_project.entity.NewsletterEntry;
import com.spring_project.spring_project.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, ObjectId> {
    Users findByUserName(String userName);
}
