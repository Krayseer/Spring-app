package com.example.hibernate.services;

import com.example.hibernate.constants.Exceptions;
import com.example.hibernate.models.Tutorial;
import com.example.hibernate.repositories.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorialService {
    private final TutorialRepository tutorialRepository;

    public List<Tutorial> getAllTutorials(String title){
        return title == null ? tutorialRepository.findAll() : tutorialRepository.findByTitleContaining(title);
    }

    public Tutorial getTutorialById(Long id){
        return tutorialRepository.findById(id).orElseThrow(() -> Exceptions.TUTORIAL_ID_NOT_EXISTS);
    }

    public List<Tutorial> getTutorialByPublished(){
        return tutorialRepository.findByPublished(true);
    }

    public Tutorial createTutorial(Tutorial tutorial){
        return tutorialRepository.save(Tutorial
                .builder()
                .title(tutorial.getTitle())
                .description(tutorial.getDescription())
                .published(tutorial.isPublished()).build());
    }

    public Tutorial updateTutorial(Long id, Tutorial tutorial){
        Tutorial newTutorial = tutorialRepository.findById(id).orElseThrow(() -> Exceptions.TUTORIAL_ID_NOT_EXISTS);
        newTutorial.setTitle(tutorial.getTitle());
        newTutorial.setDescription(tutorial.getDescription());
        newTutorial.setPublished(tutorial.isPublished());
        return tutorialRepository.save(newTutorial);
    }

    public void deleteAllTutorials(){
        tutorialRepository.deleteAll();
    }

    public void deleteTutorialById(Long id){
        Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(() -> Exceptions.TUTORIAL_ID_NOT_EXISTS);
        tutorialRepository.deleteById(tutorial.getId());
    }
}
