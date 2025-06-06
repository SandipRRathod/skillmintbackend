package com.skillmint.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillmint.entitys.User;
import com.skillmint.repositorys.UserRepo;

@Service
public class UserService {
	
	private UserRepo repo;

	public UserService(UserRepo repo) {
		this.repo = repo;
	}
	
	
	//create user
	public User addUser(User user) {
		
		Boolean u=repo.existsByUserEmail(user.getUserEmail());
		
		if (u) {
			return null;
		}
		
		return repo.save(user);
	}
	
	//login user
	public User getUser(String email, String pass) {
		return repo.findByUserEmailAndUserPass(email, pass);
	}

	
	//get all users
	public List<User> getAll() {
		return repo.findAll();
	}

	//get single user
	public Optional<User> getUser(long userId) {
		// TODO Auto-generated method stub
		return repo.findById(userId);
	}
	
	// update user
	public User updateUser(User prevUser) {
	    User updatedUser = repo.findById(prevUser.getId()).orElseThrow(() -> new RuntimeException("User not found"));

	    updatedUser.setUserName(prevUser.getUserName());
	    updatedUser.setUserEmail(prevUser.getUserEmail());
	    updatedUser.setUserPhNo(prevUser.getUserPhNo());
	    updatedUser.setUserSkills(prevUser.getUserSkills());
	    updatedUser.setUserInterests(prevUser.getUserInterests());

	    return repo.save(updatedUser);
	}


	public int getPoints(long userId) {
		
		return repo.findById(userId).get().getUserPoints();
	}

	
	

}
