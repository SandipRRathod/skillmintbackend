package com.skillmint.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillmint.entitys.Startup;
import com.skillmint.entitys.User;
import com.skillmint.repositorys.StartupRepo;

@Service
public class StartupService {
	
	private StartupRepo repo;

	public StartupService(StartupRepo repo) {
		this.repo = repo;
	}
	
	public Startup addStartup(Startup s) {
		
		Boolean o =repo.existsByOrgEmail(s.getOrgEmail());
		
		if (o) {
			return null;
		}
		
		return repo.save(s);
	}
	
	public Startup getStartup(String email, String pass) {
		return repo.findByOrgEmailAndOrgPass(email, pass);
	}

	public List<Startup> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Optional<Startup> getStartup(long userId) {
		// TODO Auto-generated method stub
		return repo.findById(userId);
	}

	public Startup updateUser(Startup prevStartup) {
		Startup updatedStartup = repo.findById(prevStartup.getId()).orElseThrow(() -> new RuntimeException("Startup not found"));
		
		updatedStartup.setOrgName(prevStartup.getOrgName());
		updatedStartup.setOrgEmail(prevStartup.getOrgEmail());
		updatedStartup.setOrgPhNo(prevStartup.getOrgPhNo()); 
		
			    return repo.save(updatedStartup);
	}


}
