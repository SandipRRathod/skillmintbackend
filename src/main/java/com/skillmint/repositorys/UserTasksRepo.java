package com.skillmint.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillmint.entitys.UserTasks;

@Repository
public interface UserTasksRepo extends JpaRepository<UserTasks, Long>{

	List<UserTasks> findAllByUser_Id(Long id);


}