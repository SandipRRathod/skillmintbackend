package com.skillmint.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillmint.entitys.UserTasks;
import com.skillmint.services.UserTasksService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user/tasks")
@CrossOrigin
@Slf4j
public class UserTasksController {

    private final UserTasksService service;

    public UserTasksController(UserTasksService service) {
        this.service = service;
    }

    // 🔹 Create Task
    @PostMapping("/add/{id}")
    public ResponseEntity<UserTasks> addTask(@PathVariable long id,@RequestBody UserTasks task) {
        UserTasks savedTask = service.addTask(id,task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    // 🔹 Get All Tasks
    @GetMapping("/all")
    public ResponseEntity<List<UserTasks>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }

    // 🔹 Get Task by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
    	
    	
    	List<UserTasks> us=service.getAllTasksByUserId(id);
    	
    	if (us.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("0 task, Add Tasks");
		}
    	
    	if (!us.isEmpty()) {
    	    return ResponseEntity.ok(us);
    	}

    	
        return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Task not found with ID: " + id);
    }


    // 🔹 Update Task
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody UserTasks updatedTask) {
        try {
            UserTasks updated = service.updateTask(id, updatedTask);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 🔹 Delete Task
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        boolean deleted = service.deleteTask(id);
        if (deleted) {
            return ResponseEntity.ok("Task deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
    }
}

