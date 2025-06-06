package com.skillmint.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillmint.entitys.Startup;
import com.skillmint.entitys.StartupTasks;
import com.skillmint.repositorys.StartupRepo;
import com.skillmint.repositorys.StartupTasksRepo;

@Service
public class StartupTasksService {

    private final StartupTasksRepo repo;
    
    private final StartupRepo startupRepo;

    public StartupTasksService(StartupTasksRepo repo,StartupRepo startupRepo) {
        this.repo = repo;
        this.startupRepo=startupRepo;
    }

    // 🔹 Create
    public StartupTasks addTask(long id,StartupTasks task) {
    	
    	Startup startup=startupRepo.findById(id).get();
    	
    	task.setStartup(startup);
    	
        return repo.save(task);
    }

    // 🔹 Read All
    public List<StartupTasks> getAllTasks() {
        return repo.findAll();
    }

    // 🔹 Read by ID
    public Optional<StartupTasks> getTaskById(Long id) {
        return repo.findById(id);
    }

    // 🔹 Update
    public StartupTasks updateTask(Long id, StartupTasks updatedTask) {
        return repo.findById(id).map(existingTask -> {
            existingTask.setTaskTitle(updatedTask.getTaskTitle());
            existingTask.setPoints(updatedTask.getPoints());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setDueDate(updatedTask.getDueDate());
            return repo.save(existingTask);
        }).orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
    }

    // 🔹 Delete
    public boolean deleteTask(Long id) {
        Optional<StartupTasks> task = repo.findById(id);
        if (task.isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

	public List<StartupTasks> getTaskByStartupId(Long id) {
		return repo.findAllByStartup_Id(id);
	}
}
