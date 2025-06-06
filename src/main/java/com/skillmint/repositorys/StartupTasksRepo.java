package com.skillmint.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillmint.entitys.StartupTasks;

@Repository
public interface StartupTasksRepo extends JpaRepository<StartupTasks, Long>{

	List<StartupTasks> findAllByStartup_Id(long id);

}
