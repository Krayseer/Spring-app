package com.example.hibernate.services;

import static com.example.hibernate.constants.Exceptions.*;
import com.example.hibernate.models.Comment;
import com.example.hibernate.repositories.CommentRepository;
import com.example.hibernate.repositories.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TutorialRepository tutorialRepository;

    public List<Comment> getCommentsByTutorialId(Long id){
        List<Comment> comment = commentRepository.findByTutorialId(id);
        if (comment.isEmpty())
            throw EXCEPTION_TUTORIAL_ID_NOT_EXISTS;
        return comment;
    }

    public Comment getCommentById(Long id){
        return commentRepository.findById(id).orElseThrow(() -> EXCEPTION_COMMENT_ID_NOT_EXISTS);
    }

    public Comment createComment(Long id, Comment comment){
        return tutorialRepository.findById(id).map(tutorial -> {
            comment.setTutorial(tutorial);
            return commentRepository.save(comment);
        }).orElseThrow(() -> EXCEPTION_COMMENT_ID_NOT_EXISTS);
    }

    public Comment updateComment(Long id, Comment commentRequest){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> EXCEPTION_COMMENT_ID_NOT_EXISTS);
        comment.setContent(commentRequest.getContent());
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id){
        if(!commentRepository.existsById(id))
            throw EXCEPTION_COMMENT_ID_NOT_EXISTS;
        commentRepository.deleteById(id);
    }

    public void deleteAllTutorialComments(Long id){
        if (!tutorialRepository.existsById(id))
            throw EXCEPTION_COMMENT_ID_NOT_EXISTS;
        commentRepository.deleteByTutorialId(id);
    }
}
