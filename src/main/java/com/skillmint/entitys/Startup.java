package com.skillmint.entitys;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Startup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String orgName;
	
    private String orgEmail;
    
    private int points;
	
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String orgPass;
	
	private String orgPhNo;
	
	 @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonManagedReference
	    private List<StartupTasks> startupTasks;
	 
	 @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonManagedReference
	    private List<StartupCampaign> campaigns;

}
