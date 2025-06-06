package com.skillmint.controllers;

import com.skillmint.entitys.StartupTasks;
import com.skillmint.services.StartupTasksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/startup/tasks")
@CrossOrigin
public class StartupTasksController {

    private final StartupTasksService service;

    public StartupTasksController(StartupTasksService service) {
        this.service = service;
    }

    // 🔹 Create Task
    @PostMapping("/add/{id}")
    public ResponseEntity<StartupTasks> addTask(@PathVariable long id,@RequestBody StartupTasks task) {
        StartupTasks savedTask = service.addTask(id,task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    // 🔹 Get All Tasks
    @GetMapping("/all")
    public ResponseEntity<List<StartupTasks>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }

    // 🔹 Get Task by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        Optional<StartupTasks> task = service.getTaskById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Task not found with ID: " + id);
        }
    }

    
    @GetMapping("/list/{sId}")
    public ResponseEntity<?> getTaskByStartupId(@PathVariable Long sId) {
        List<StartupTasks> task = service.getTaskByStartupId(sId);
        
        
        if (task.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("0 task, Add Tasks");
		}
        
        if (!task.isEmpty()) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Task not found with ID: " + sId);
        }
    }
    // 🔹 Update Task
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody StartupTasks updatedTask) {
        try {
            StartupTasks updated = service.updateTask(id, updatedTask);
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
