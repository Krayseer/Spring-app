package com.example.hibernate.services;

import static com.example.hibernate.constants.Exceptions.*;
import com.example.hibernate.models.Comment;
import com.example.hibernate.repositories.CommentRepository;
import com.example.hibernate.repositories.TutorialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.nio.channels.NonWritableChannelException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TutorialRepository tutorialRepository;

    @Cacheable(value="comments")
    public List<Comment> getCommentsByTutorialId(Long id){
        log.info("Get tutorial comments from database");
        List<Comment> comment = commentRepository.findByTutorialId(id);
        if (comment.isEmpty())
            throw EXCEPTION_EMPTY_LIST;
        return comment;
    }

    @Cacheable(value="comments")
    public Comment getCommentById(Long id){
        log.info("Get comment by id: " + id);
        return commentRepository.findById(id).orElseThrow(() -> EXCEPTION_COMMENT_ID_NOT_EXISTS);
    }

    @CacheEvict(value="comments", allEntries = true)
    public Comment createComment(Long id, Comment comment){
        log.info("Create comment for tutorial with id: " + id + ": " + comment);
        return tutorialRepository.findById(id).map(tutorial -> {
            comment.setTutorial(tutorial);
            return commentRepository.save(comment);
        }).orElseThrow(() -> EXCEPTION_COMMENT_ID_NOT_EXISTS);
    }

    @CacheEvict(value="comments", allEntries = true)
    public Comment updateComment(Long id, Comment commentRequest){
        log.info("Update comment with id: " + id + ". " + commentRequest);
        Comment comment = commentRepository.findById(id).orElseThrow(() -> EXCEPTION_COMMENT_ID_NOT_EXISTS);
        comment.setContent(commentRequest.getContent());
        return commentRepository.save(comment);
    }

    @CacheEvict(value="comments", allEntries = true)
    public void deleteComment(Long id){
        log.info("Delete comment with id: " + id);
        if(!commentRepository.existsById(id))
            throw EXCEPTION_COMMENT_ID_NOT_EXISTS;
        commentRepository.deleteById(id);
    }

    @CacheEvict(value="comments", allEntries = true)
    public void deleteAllTutorialComments(Long id){
        log.info("Delete all tutorials by id: " + id);
        if (!tutorialRepository.existsById(id))
            throw EXCEPTION_COMMENT_ID_NOT_EXISTS;
        commentRepository.deleteByTutorialId(id);
    }
}
