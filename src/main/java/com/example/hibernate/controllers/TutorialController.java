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
@RequestMapping("/api")
public class TutorialController {
    private final TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        log.info("Start endpoint: getAllTutorials");
        List<Tutorial> tutorials = tutorialService.getAllTutorials(title);
        log.info("End endpoint: getAllTutorials");
        return ResponseEntity.ok(tutorials);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        log.info("Start endpoint: getTutorialById");
        Tutorial tutorial = tutorialService.getTutorialById(id);
        log.info("End endpoint: getTutorialById");
        return ResponseEntity.ok(tutorial);
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        log.info("Start endpoint: findByPublished");
        List<Tutorial> tutorials = tutorialService.getTutorialByPublished();
        log.info("End endpoint: findByPublished");
        return ResponseEntity.ok(tutorials);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        log.info("Start endpoint: createTutorial");
        Tutorial newTutorial = tutorialService.createTutorial(tutorial);
        log.info("End endpoint: createTutorial");
        return new ResponseEntity<>(newTutorial, HttpStatus.CREATED);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id,
                                                   @RequestBody Tutorial tutorial) {
        log.info("Start endpoint: updateTutorial");
        Tutorial newTutorial = tutorialService.updateTutorial(id, tutorial);
        log.info("End endpoint: updateTutorial");
        return new ResponseEntity<>(newTutorial, HttpStatus.OK);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        log.info("Start endpoint: deleteAllTutorials");
        tutorialService.deleteAllTutorials();
        log.info("End endpoint: deleteAllTutorials");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        log.info("Start endpoint: deleteTutorial");
        tutorialService.deleteTutorialById(id);
        log.info("End endpoint: deleteTutorial");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

