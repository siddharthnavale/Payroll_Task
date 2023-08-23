package com.recruitment_portal.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitment_portal.entities.InviteEntity;
import com.recruitment_portal.repo.InviteReposiotry;
import com.recruitment_portal.service.InviteServiceInterface;

@Service
public class InviteServiceInterfaceImpl implements InviteServiceInterface{
	
	@Autowired
	private InviteReposiotry inviteReposiotry;
	
	@Override
	public void add(UUID uuid, int userId) {
		InviteEntity inviteEntity = new InviteEntity();
		inviteEntity.setUserId(userId);
		inviteEntity.setCode(uuid);

		this.inviteReposiotry.save(inviteEntity);
		
	}

}
