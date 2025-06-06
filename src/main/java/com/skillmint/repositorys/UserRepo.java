package com.skillmint.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillmint.entitys.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByUserEmailAndUserPass(String userEmail, String userPass);

	boolean existsByUserEmail(String userEmail);

	

}
