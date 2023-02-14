package com.example.hibernate.controllers;

import com.example.hibernate.models.Comment;
import com.example.hibernate.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/tutorials/{tutorialId}")
    public ResponseEntity<List<Comment>> getTutorialComments(@PathVariable(value = "tutorialId") Long tutorialId) {
        List<Comment> comments = commentService.getCommentsByTutorialId(tutorialId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Long id) {
        Comment comment = commentService.getCommentById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
                                                 @RequestBody Comment commentRequest) {
        Comment comment = commentService.createComment(tutorialId, commentRequest);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment commentRequest) {
        Comment comment = commentService.updateComment(id, commentRequest);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{tutorialId}")
    public ResponseEntity<HttpStatus> deleteAllTutorialComments(@PathVariable(value = "tutorialId") Long id) {
        commentService.deleteAllTutorialComments(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
