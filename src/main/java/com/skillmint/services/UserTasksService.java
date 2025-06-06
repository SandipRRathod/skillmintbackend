package com.skillmint.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillmint.entitys.User;
import com.skillmint.entitys.UserTasks;
import com.skillmint.repositorys.UserRepo;
import com.skillmint.repositorys.UserTasksRepo;

@Service
public class UserTasksService {

    private final UserTasksRepo repo;
    
    private final UserRepo userRepo;

    public UserTasksService(UserTasksRepo repo,UserRepo userRepo) {
        this.repo = repo;
        this.userRepo=userRepo;
    }

    // 🔹 Create a new task
    public UserTasks addTask(long id,UserTasks task) {
    	
    	User user=userRepo.findById(id).get();
    	
    	task.setUser(user);
    	
        return repo.save(task);
    }

    // 🔹 Get all tasks
    public List<UserTasks> getAllTasks() {
        return repo.findAll();
    }
    
    public List<UserTasks> getAllTasksByUserId(Long id) {
        return repo.findAllByUser_Id(id);
    }

    // 🔹 Get task by ID
    public Optional<UserTasks> getTaskById(Long id) {
        return repo.findById(id);
    }

    // 🔹 Update task by ID
    public UserTasks updateTask(Long id, UserTasks updatedTask) {
        return repo.findById(id)
                .map(task -> {
                    task.setTaskName(updatedTask.getTaskName());
                    task.setTaskStatus(updatedTask.getTaskStatus());
                    task.setTaskPoints(updatedTask.getTaskPoints());
                    task.setTaskDueDate(updatedTask.getTaskDueDate());
                    task.setTaskDescription(updatedTask.getTaskDescription());
                    return repo.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    // 🔹 Delete task by ID
    public boolean deleteTask(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
