package com.controller;


import com.entities.Author;
import com.entities.Language;
import com.entities.Publisher;
import com.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegistrationService registrationService;


    @PostMapping("/author")
    public ResponseEntity<Author> registerAuthor(@RequestBody Author author)
    {
        registrationService.registerAuthor(author);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAuthor()
    {
        return ResponseEntity.ok(registrationService.getAllAuthor());
    }

    @GetMapping("/publishers")
    public ResponseEntity<List<Publisher>> getAllPublisher()
    {
        List<Publisher> publishers = registrationService.getAllPublisher();
        return ResponseEntity.ok(publishers);
    }

    @PostMapping("/publisher")
    public ResponseEntity<Publisher> registerPublisher(@RequestBody Publisher publisher)
    {
        return ResponseEntity.ok(registrationService.registerPublisher(publisher));
    }

    @GetMapping("/languages")
    public ResponseEntity<List<Language>> getAllLangs()
    {
        List<Language> languages = registrationService.getAllLang();
        return ResponseEntity.ok(languages);
    }

    @PostMapping("/language")
    public ResponseEntity<Language> registerLanguage(@RequestBody Language language)
    {
        return ResponseEntity.ok(registrationService.registerLang(language));
    }
}
