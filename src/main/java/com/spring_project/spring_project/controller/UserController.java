package com.spring_project.spring_project.controller;

import com.spring_project.spring_project.entity.NewsletterEntry;
import com.spring_project.spring_project.entity.Users;
import com.spring_project.spring_project.service.NewsletterEntryService;
import com.spring_project.spring_project.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<Users> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody Users user){
        userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody Users user, @PathVariable String userName){
        Users userInDB = userService.findByUserName(userName);
        if (userInDB != null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveEntry(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
