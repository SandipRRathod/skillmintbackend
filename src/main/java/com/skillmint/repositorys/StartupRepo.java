package com.skillmint.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillmint.entitys.Startup;

@Repository
public interface StartupRepo extends JpaRepository<Startup, Long>{

	Startup findByOrgEmailAndOrgPass(String email, String pass);

	boolean existsByOrgEmail(String orgEmail);

}
