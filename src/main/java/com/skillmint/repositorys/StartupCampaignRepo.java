package com.skillmint.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillmint.entitys.StartupCampaign;

@Repository
public interface StartupCampaignRepo extends JpaRepository<StartupCampaign, Long> {

	List<StartupCampaign> findAllByStartup_Id(long id);

}
