package com.example.hibernate.controllers;

import com.example.hibernate.models.Tutorial;
import com.example.hibernate.services.TutorialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tutorials")
public class TutorialController {
    private final TutorialService tutorialService;

    @GetMapping
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = tutorialService.getAllTutorials(title);
        return ResponseEntity.ok(tutorials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Tutorial tutorial = tutorialService.getTutorialById(id);
        return ResponseEntity.ok(tutorial);
    }

    @GetMapping("/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        List<Tutorial> tutorials = tutorialService.getTutorialByPublished();
        return ResponseEntity.ok(tutorials);
    }

    @PostMapping
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial newTutorial = tutorialService.createTutorial(tutorial);
        return new ResponseEntity<>(newTutorial, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id,
                                                   @RequestBody Tutorial tutorial) {
        Tutorial newTutorial = tutorialService.updateTutorial(id, tutorial);
        return new ResponseEntity<>(newTutorial, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        tutorialService.deleteAllTutorials();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        tutorialService.deleteTutorialById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

