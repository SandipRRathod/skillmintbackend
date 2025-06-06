package com.skillmint.entitys;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StartupCampaign {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String status;
	    private int reach;
	    private String description;
	    
	    @ElementCollection
	    private List<String> tasks;
	    
	    
	    @ManyToOne
		@JoinColumn(name = "startup_id")// foreign key column
		@JsonBackReference
		private Startup startup;
}
