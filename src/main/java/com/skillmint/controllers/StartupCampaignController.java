package com.skillmint.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillmint.entitys.StartupCampaign;
import com.skillmint.services.StartupCampaignService;

@RestController
@RequestMapping("/api/startup/campaigns")
@CrossOrigin
public class StartupCampaignController {

    private final StartupCampaignService service;

    public StartupCampaignController(StartupCampaignService service) {
        this.service = service;
    }

    // GET all campaigns
    @GetMapping("/all")
    public ResponseEntity<List<StartupCampaign>> getAll() {
        return ResponseEntity.ok(service.getAllCampaigns());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        StartupCampaign campaign = service.getCampaignById(id);
        if (campaign != null) {
            return ResponseEntity.ok(campaign);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaign not found with ID: " + id);
        }
    }

    // GET by Startup ID
    @GetMapping("/list/{sId}")
    public ResponseEntity<List<StartupCampaign>> getByStartupId(@PathVariable Long sId) {
        return ResponseEntity.ok(service.getAllCampaignsByStartUp(sId));
    }

    // POST - Add Campaign
    @PostMapping("/add/{id}")
    public ResponseEntity<?> add(@PathVariable Long id, @RequestBody StartupCampaign campaign) {
        try {
            StartupCampaign saved = service.addCampaign(id, campaign);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating campaign: " + e.getMessage());
        }
    }

    // PUT - Update Campaign
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StartupCampaign campaign) {
        StartupCampaign updated = service.updateCampaign(id, campaign);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaign not found for update with ID: " + id);
        }
    }

    // DELETE - Delete Campaign
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteCampaign(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Campaign deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaign not found or already deleted: " + e.getMessage());
        }
    }
}
