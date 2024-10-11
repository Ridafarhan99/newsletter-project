package com.spring_project.spring_project.controller;

import com.spring_project.spring_project.entity.Comment;
import com.spring_project.spring_project.service.CommentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Map<String, Object> payload){
        String commentBody = (String) payload.get("commentBody");
        ObjectId commentId = new ObjectId((String) payload.get("_id"));

        Comment newComment = commentService.addComment(commentBody, commentId);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
}
