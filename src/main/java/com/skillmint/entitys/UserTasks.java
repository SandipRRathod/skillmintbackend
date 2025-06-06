package com.skillmint.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class UserTasks {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String taskName;
	
	private String taskStatus;
	
	private String taskPoints;
	
	private String taskDescription;
	
	private String taskDueDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")// foreign key column
	@JsonBackReference
	private User user;


}
