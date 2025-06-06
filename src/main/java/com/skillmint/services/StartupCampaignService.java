package com.skillmint.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillmint.entitys.Startup;
import com.skillmint.entitys.StartupCampaign;
import com.skillmint.repositorys.StartupCampaignRepo;
import com.skillmint.repositorys.StartupRepo;

@Service
public class StartupCampaignService {

	private final StartupCampaignRepo campaignRepo;
	
	private final StartupRepo repo;

	public StartupCampaignService(StartupCampaignRepo campaignRepo,StartupRepo repo) {
		this.campaignRepo = campaignRepo;
		this.repo=repo;
	}
	
	public List<StartupCampaign> getAllCampaigns() {
        return campaignRepo.findAll();
    }
	
	public List<StartupCampaign> getAllCampaignsByStartUp(long id) {
        return campaignRepo.findAllByStartup_Id(id);
    }

    public StartupCampaign getCampaignById(Long id) {
        return campaignRepo.findById(id).orElse(null);
    }

    public StartupCampaign addCampaign(long id,StartupCampaign campaign) {
    	
    	Startup s=repo.findById(id).get();
    	
    	campaign.setStartup(s);
    	
        return campaignRepo.save(campaign);
    }

    public StartupCampaign updateCampaign(Long id, StartupCampaign updated) {
    	StartupCampaign existing = campaignRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setStatus(updated.getStatus());
            existing.setReach(updated.getReach());
            existing.setDescription(updated.getDescription());
            existing.setTasks(updated.getTasks());
            return campaignRepo.save(existing);
        }
        return null;
    }

    public void deleteCampaign(Long id) {
    	campaignRepo.deleteById(id);
    }
	
	
}
