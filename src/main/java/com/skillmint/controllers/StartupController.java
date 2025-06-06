package com.skillmint.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillmint.entitys.Startup;
import com.skillmint.entitys.User;
import com.skillmint.services.StartupService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/startup")
@CrossOrigin
@Slf4j
public class StartupController {

    private final StartupService service;

    public StartupController(StartupService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> addStartup(@RequestBody Startup startup) {
        Startup savedStartup = service.addStartup(startup);

        if (savedStartup != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body("Startup added successfully..!");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body("Startup already exists....!");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getStartup(@RequestParam String email,@RequestParam String pass) {
        Startup startup = service.getStartup(email,pass);

        if (startup != null) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(startup);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            		 .body("Invalid email or password.");
        }
    }
    
    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                                 .body(service.getAll());
       
    }
    
    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getStartup(@PathVariable long userId) {
        Optional<Startup> startup = service.getStartup(userId);

        if (startup.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
            		             .body(startup);
        } else {
        	log.error("Login User Procces Failed..");
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid User Id.");
        }
    }
    
    
    @PutMapping("/update")	
    public ResponseEntity<?> updateUser(@RequestBody Startup startup) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(startup));
    }
    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Something went wrong: " + e.getMessage());
    }
}
