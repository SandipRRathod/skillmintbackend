package com.skillmint.entitys;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "app_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String userEmail;
    
    private int userPoints;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPass;
    private String userPhNo;
    
    private List<String> userSkills;
  
    private List<String> userInterests;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<UserTasks> userTasks;

}
