package com.example.hibernate.services;

import com.example.hibernate.constants.Exceptions;
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

    @Cacheable(value="allTutorials")
    public List<Tutorial> getAllTutorials(String title){
        log.info("Get all tutorials");
        return title == null ? tutorialRepository.findAll() : tutorialRepository.findByTitleContaining(title);
    }

    @Cacheable(value="allTutorials")
    public Tutorial getTutorialById(Long id){
        log.info("Get tutorial by id: " + id);
        return tutorialRepository.findById(id).orElseThrow(() -> Exceptions.TUTORIAL_ID_NOT_EXISTS);
    }

    @Cacheable(value="allTutorials")
    public List<Tutorial> getTutorialByPublished(){
        log.info("Get published tutorials");
        return tutorialRepository.findByPublished(true);
    }

    @CacheEvict(value="allTutorials", allEntries = true)
    public Tutorial createTutorial(Tutorial tutorial){
        log.info("Create new tutorial: " + tutorial);
        return tutorialRepository.save(Tutorial
                .builder()
                .title(tutorial.getTitle())
                .description(tutorial.getDescription())
                .published(tutorial.isPublished()).build());
    }

    @CacheEvict(value="allTutorials", allEntries = true)
    public Tutorial updateTutorial(Long id, Tutorial tutorial){
        Tutorial newTutorial = tutorialRepository.findById(id).orElseThrow(() -> Exceptions.TUTORIAL_ID_NOT_EXISTS);
        newTutorial.setTitle(tutorial.getTitle());
        newTutorial.setDescription(tutorial.getDescription());
        newTutorial.setPublished(tutorial.isPublished());
        return tutorialRepository.save(newTutorial);
    }

    @CacheEvict(value="allTutorials", allEntries = true)
    public void deleteAllTutorials(){
        tutorialRepository.deleteAll();
    }

    @CacheEvict(value="allTutorials", allEntries = true)
    public void deleteTutorialById(Long id){
        Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(() -> Exceptions.TUTORIAL_ID_NOT_EXISTS);
        tutorialRepository.deleteById(tutorial.getId());
    }
}
