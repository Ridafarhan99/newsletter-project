package com.spring_project.spring_project.service;

import com.spring_project.spring_project.entity.Comment;
import com.spring_project.spring_project.repository.CommentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Comment addComment(String commentBody, ObjectId commentId){
        Comment userComment = commentRepository.insert(new Comment(commentBody));


        Query query = new Query(Criteria.where("_id").is(commentId));
        Update update = new Update().push("comments", userComment);

        mongoTemplate.updateFirst(query, update, "newsletters");

        return userComment;
    }
}
// controller -> service -> repository