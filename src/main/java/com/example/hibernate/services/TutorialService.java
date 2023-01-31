package com.example.hibernate.services;

import static com.example.hibernate.constants.Exceptions.*;
import com.example.hibernate.models.Tutorial;
import com.example.hibernate.repositories.TutorialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TutorialService {
    private final TutorialRepository tutorialRepository;

    @Cacheable(value= "tutorials")
    public List<Tutorial> getAllTutorials(String title){
        log.info("Get all tutorials from database");
        return title == null ? tutorialRepository.findAll() : tutorialRepository.findByTitleContaining(title);
    }

    @Cacheable(value= "tutorials")
    public Tutorial getTutorialById(Long id){
        log.info("Get tutorial by id: " + id);
        return tutorialRepository.findById(id).orElseThrow(() -> EXCEPTION_TUTORIAL_ID_NOT_EXISTS);
    }

    @Cacheable(value= "tutorials")
    public List<Tutorial> getTutorialByPublished(){
        log.info("Get published tutorials from database");
        return tutorialRepository.findByPublished(true);
    }

    @CacheEvict(value= "tutorials", allEntries = true)
    public Tutorial createTutorial(Tutorial tutorial){
        log.info("Create new tutorial: " + tutorial);
        return tutorialRepository.save(Tutorial
                .builder()
                .title(tutorial.getTitle())
                .description(tutorial.getDescription())
                .published(tutorial.isPublished()).build());
    }

    @CacheEvict(value= "tutorials", allEntries = true)
    public Tutorial updateTutorial(Long id, Tutorial tutorial){
        log.info("Update tutorial with id: " + id + " on: " + tutorial);
        Tutorial newTutorial = tutorialRepository.findById(id).orElseThrow(() -> EXCEPTION_TUTORIAL_ID_NOT_EXISTS);
        newTutorial.setTitle(tutorial.getTitle());
        newTutorial.setDescription(tutorial.getDescription());
        newTutorial.setPublished(tutorial.isPublished());
        return tutorialRepository.save(newTutorial);
    }

    @CacheEvict(value= "tutorials", allEntries = true)
    public void deleteAllTutorials(){
        log.info("Delete all tutorials from database");
        tutorialRepository.deleteAll();
    }

    @CacheEvict(value= "tutorials", allEntries = true)
    public void deleteTutorialById(Long id){
        log.info("Delete tutorial from database with id: " + id);
        Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(() -> EXCEPTION_TUTORIAL_ID_NOT_EXISTS);
        tutorialRepository.deleteById(tutorial.getId());
    }
}
