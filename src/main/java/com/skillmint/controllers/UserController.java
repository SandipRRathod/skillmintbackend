package com.skillmint.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillmint.entitys.User;
import com.skillmint.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Slf4j
public class UserController {
	
	private UserService service;
	

	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping("/save")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        User savedUser = service.addUser(user);
        
//        log.info("{}",user);

        if (savedUser != null) {
        	
            return ResponseEntity.status(HttpStatus.CREATED)
            		             .body("User added successfully!");
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User Already Exists...");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam String email, @RequestParam String password) {
    	log.info("Started User Login process..");
        User user = service.getUser(email, password);

        if (user != null) {
        	log.info("Login User Procces Succecfully..");
 
            return ResponseEntity.status(HttpStatus.ACCEPTED)
            		             .body(user);
        } else {
        	log.error("Login User Procces Failed..");
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password.");
        }
    }
    
    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUser(@PathVariable long userId) {
        Optional<User> user = service.getUser(userId);

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
            		             .body(user);
        } else {
        	
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid User Id.");
        }
    }

    @GetMapping("/get-points/{userId}")
    public ResponseEntity<?> getPoints(@PathVariable long userId) {
        int points = service.getPoints(userId);
        
        
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(points);
        
    }
    
    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                                 .body(service.getAll());
       
    }
    
 // Update user profile
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(updatedUser));
    }
    
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong: " + e.getMessage());
    }

	

}
