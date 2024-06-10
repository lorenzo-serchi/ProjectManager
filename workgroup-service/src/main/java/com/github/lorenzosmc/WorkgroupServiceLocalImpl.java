package com.github.lorenzosmc;

import com.github.lorenzosmc.common.dto.workgroup.inbound.WorkgroupCreationDto;
import com.github.lorenzosmc.common.dto.workgroup.inbound.WorkgroupQueryDto;
import com.github.lorenzosmc.common.dto.workgroup.outbound.WorkgroupDto;
import com.github.lorenzosmc.common.event.WorkgroupCreatedEvent;
import com.github.lorenzosmc.common.service.WorkgroupService;
import com.github.lorenzosmc.dao.*;
import com.github.lorenzosmc.model.Context;
import com.github.lorenzosmc.model.Membership;
import com.github.lorenzosmc.model.Student;
import com.github.lorenzosmc.model.Workgroup;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkgroupServiceLocalImpl implements WorkgroupService {
	@Inject 
	WorkgroupDao workgroupDao;

	@Inject 
	MembershipDao membershipDao;
	
	@Inject
	WorkgroupMapper workgroupMapper;
	
	@Inject
	private ContextDaoImpl contextDao;
		
	@Inject
	private StudentDao studentDao;
		
	@Inject
	private Event<WorkgroupCreatedEvent> workgroupCreatedEvent;
	
	@Override
	public List<WorkgroupDto> findAll(WorkgroupQueryDto queryDto){
		//TODO input validation
		WorkgroupQuery query = workgroupMapper.translateQuery(queryDto);
		List<Workgroup> workgroupEntities = workgroupDao.findAll(query);
		
		List<WorkgroupDto> workgroupDtos = new ArrayList<WorkgroupDto>();
		for(Workgroup workgroupEntity : workgroupEntities)
			workgroupDtos.add(workgroupMapper.convertWorkgroup(workgroupEntity));
	
		return workgroupDtos;
	}

	@Override
	public WorkgroupDto findByUuid(UUID workgroupUuid) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkgroupDto create(WorkgroupCreationDto workgroupDto, UUID contextUuid, UUID creatorUuid) {
		//TODO input validation 
		// if WorkgroupCreationDto fields are unspecified -> default value that makes sense from app logic, or Response w/ error code if mandatory fields
		// semantic meaning of fields is checked by Domain Model.
		Context context = contextDao.findByUuid(contextUuid);
		Student student = studentDao.findByUuid(creatorUuid);
		
		Workgroup workgroup = ModelFactory.createWorkgroup(context, student);
		workgroupMapper.transferWorkgroupCreationDto(workgroupDto, workgroup);
		
		Membership membership = ModelFactory.createMembership(workgroup, student);
		membership.setLeader(true);
		membership.setPublishingConsent(workgroupDto.getPublishingConsent());
		
		membershipDao.save(membership);
				
		WorkgroupDto createdWorkgroupDto = workgroupMapper.convertWorkgroup(workgroup);
		//TODO [microservice] fire workgroup creation event (e.g. used by user/context service to update their representation)
		workgroupCreatedEvent.fire(new WorkgroupCreatedEvent(createdWorkgroupDto));
		
		return createdWorkgroupDto;
	}

	@Override
	public WorkgroupDto update(WorkgroupDto workgroupDto) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}
	

}
