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
public class StartupTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskTitle;      // Task Title
    private String points;         // Points
    private String description;    // Description
    private String status;         // Status
    private String dueDate;        // Due Date
    
    @ManyToOne
	@JoinColumn(name = "startup_id")// foreign key column
	@JsonBackReference
	private Startup startup;
}
