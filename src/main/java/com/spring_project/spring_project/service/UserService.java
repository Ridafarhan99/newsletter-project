package com.spring_project.spring_project.service;

import com.spring_project.spring_project.entity.NewsletterEntry;
import com.spring_project.spring_project.entity.Users;
import com.spring_project.spring_project.repository.NewsletterRepository;
import com.spring_project.spring_project.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Users> getAll() {
        return userRepository.findAll();
    }

    public void saveEntry(Users user){
        userRepository.save(user);
    }

    public Optional<Users> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public Users findByUserName(String username){
        return userRepository.findByUserName(username);
    }
}
// controller -> service -> repository