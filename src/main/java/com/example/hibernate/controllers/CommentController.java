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
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> getTutorialComments(@PathVariable(value = "tutorialId") Long tutorialId) {
        log.info("Start endpoint: getTutorialComments");
        List<Comment> comments = commentService.getCommentsByTutorialId(tutorialId);
        log.info("Start endpoint: getTutorialComments");
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Long id) {
        log.info("Start endpoint: getCommentById");
        Comment comment = commentService.getCommentById(id);
        log.info("End endpoint: getCommentById");
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
                                                 @RequestBody Comment commentRequest) {
        log.info("Start endpoint: createComment");
        Comment comment = commentService.createComment(tutorialId, commentRequest);
        log.info("End endpoint: createComment");
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment commentRequest) {
        log.info("Start endpoint: updateComment");
        Comment comment = commentService.updateComment(id, commentRequest);
        log.info("End endpoint: updateComment");
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        log.info("Start endpoint: deleteComment");
        commentService.deleteComment(id);
        log.info("End endpoint: deleteComment");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<HttpStatus> deleteAllTutorialComments(@PathVariable(value = "tutorialId") Long id) {
        log.info("Start endpoint: deleteAllTutorialComments");
        commentService.deleteAllTutorialComments(id);
        log.info("End endpoint: deleteAllTutorialComments");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
