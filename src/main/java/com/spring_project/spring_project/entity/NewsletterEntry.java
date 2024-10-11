package com.spring_project.spring_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "newsletters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsletterEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
    @DocumentReference
    private List<Comment> comments;

}
