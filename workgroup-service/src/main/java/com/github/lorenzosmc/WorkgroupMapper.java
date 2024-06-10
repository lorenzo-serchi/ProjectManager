package com.github.lorenzosmc;

import com.github.lorenzosmc.common.dto.workgroup.inbound.WorkgroupCreationDto;
import com.github.lorenzosmc.common.dto.workgroup.inbound.WorkgroupQueryDto;
import com.github.lorenzosmc.common.dto.workgroup.outbound.MembershipDto;
import com.github.lorenzosmc.common.dto.workgroup.outbound.WorkgroupDto;
import com.github.lorenzosmc.common.dto.workgroup.outbound.WorkgroupStatusDto;
import com.github.lorenzosmc.common.exception.MapperConversionException;
import com.github.lorenzosmc.dao.*;
import com.github.lorenzosmc.model.Membership;
import com.github.lorenzosmc.model.Workgroup;
import com.github.lorenzosmc.model.WorkgroupStatus;
import jakarta.inject.Inject;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkgroupMapper {
	@Inject
	WorkgroupDao workgroupDao;
	
	@Inject
	MembershipDao membershipDao;
	
	@Inject
	StudentDao studentDao;
	
	@Inject
	ContextDaoImpl contextDao;
	
	@Inject
	TaskDao taskDao;
	
	public WorkgroupDto convertWorkgroup(Workgroup workgroup) {
		if (workgroup == null)
			throw new MapperConversionException("The workgroup Entity is null");
		
		WorkgroupDto workgroupDto = new WorkgroupDto();
		
		workgroupDto.setUuid(workgroup.getUuid());
		workgroupDto.setCreationDate(workgroup.getCreationDate());
		workgroupDto.setCreatorUuid(workgroup.getCreator().getUuid());
		workgroupDto.setContextUuid(workgroup.getContext().getUuid());
		workgroupDto.setVerified(workgroup.isVerified());
		workgroupDto.setHidden(workgroup.isHidden());
		workgroupDto.setStatus(serializeWorkgroupStatus(workgroup.getStatus()));
		workgroupDto.setTaskUuid(workgroup.getTask().getUuid());
		workgroupDto.setParticipants(serializeMemberships(workgroup.getMemberships()));
		
		String[] test = {"ONE","TWO"};
		test(test);
		@NonNull Object o = null;
		
		
		return workgroupDto;
	}
	
	private static int countArgs(@NonNull String[] args) {
	    return args.length;
	}
	
	private static void test(@Nullable String[] args) {
	    countArgs(args);
	}
	
	public MembershipDto convertMembership(Membership membership) {
		if (membership == null)
			throw new MapperConversionException("The workgroupParticipation Entity is null");
		
		MembershipDto membershipDto = new MembershipDto();
		
		membershipDto.setUuid(membership.getUuid());
		membershipDto.setWorkgroupUuid(membership.getWorkgroup().getUuid());
		membershipDto.setMemberUuid(membership.getMember().getUuid());
		membershipDto.setLeader(membership.isLeader());
		membershipDto.setJoinDate(membership.getJoinDate());
		
		return membershipDto;
	}

	public void transferWorkgroupDto(WorkgroupDto workgroupDto, Workgroup workgroup) throws IllegalArgumentException{
		if (workgroupDto == null)
			throw new MapperConversionException("The workgroup DTO is null");

		if (workgroup == null)
			throw new MapperConversionException("The workgroup Entity is null");
		
		workgroup.setCreator(studentDao.findByUuid(workgroupDto.getCreatorUuid()));
		workgroup.setContext(contextDao.findByUuid(workgroupDto.getContextUuid()));
		workgroup.setVerified(workgroupDto.isVerified());
		workgroup.setHidden(workgroupDto.isHidden());
		workgroup.setStatus(deserializeWorkgroupStatus(workgroupDto.getStatus()));
		workgroup.setTask(taskDao.findByUuid(workgroupDto.getTaskUuid()));
		workgroup.setMemberships(deserializeMembeships(workgroupDto.getParticipants()));
	}
	
	public  void transferWorkgroupCreationDto(WorkgroupCreationDto workgroupDto, Workgroup workgroup) {
		if (workgroupDto == null)
			throw new MapperConversionException("The workgroup DTO is null");

		if (workgroup == null)
			throw new MapperConversionException("The workgroup Entity is null");

		workgroup.setHidden(workgroupDto.isVisible());
		workgroup.setPublishingConsent(workgroupDto.getPublishingConsent());
	}

	public WorkgroupQuery translateQuery(WorkgroupQueryDto queryDto) {
		WorkgroupQuery query = null;
		
		if(queryDto != null) {
			query = new WorkgroupQuery();
			
			query.setContext(contextDao.findByUuid(queryDto.getContextUuid()));
			query.setCreator(studentDao.findByUuid(queryDto.getCreatorUuid()));
			query.setCreatedBefore(queryDto.getCreatedBefore());
			query.setCreatedAfter(queryDto.getCreatedAfter());
			query.setMember(studentDao.findByUuid(queryDto.getMemberUuid()));
			query.setAssignedTask(taskDao.findByUuid(queryDto.getAssignedTaskUuid()));
			query.setAssignedBefore(queryDto.getAssignedBefore());
			query.setAssignedAfter(queryDto.getAssignedAfter());
			query.setVerified(queryDto.getVerified());
			query.setHidden(queryDto.getHidden());
			query.setPublishingConsent(queryDto.getPublishingConsent());
			query.setStatus(deserializeWorkgroupStatus(queryDto.getStatus()));
		}
		
		return query;
	}
	
	private WorkgroupStatusDto serializeWorkgroupStatus(WorkgroupStatus status) {
		WorkgroupStatusDto serializedWorkgroupStatus;
		
		switch(status) {
			case NOT_WORKING:
				serializedWorkgroupStatus = WorkgroupStatusDto.NOT_WORKING;
				break;
			case WORKING:
				serializedWorkgroupStatus = WorkgroupStatusDto.WORKING;
				break;
			case RETIRED:
				serializedWorkgroupStatus = WorkgroupStatusDto.RETIRED;
				break;
			default:
				serializedWorkgroupStatus = null;
		}
		
		return serializedWorkgroupStatus;
			
	}
	
	private WorkgroupStatus deserializeWorkgroupStatus(WorkgroupStatusDto status){
		WorkgroupStatus deserializedWorkgroupStatus;
		
		switch(status) {
			case NOT_WORKING:
				deserializedWorkgroupStatus = WorkgroupStatus.NOT_WORKING;
				break;
			case WORKING:
				deserializedWorkgroupStatus = WorkgroupStatus.WORKING;
				break;
			case RETIRED:
				deserializedWorkgroupStatus = WorkgroupStatus.RETIRED;
				break;
			default:
				deserializedWorkgroupStatus = null;
		}
		
		return deserializedWorkgroupStatus;
	}
	
	private List<MembershipDto> serializeMemberships(Set<Membership> memberships){
		List<MembershipDto> serializedMemberships = null;
		
		if(memberships != null) {
			serializedMemberships = new ArrayList<>();
			for(Membership membership : memberships) 
				serializedMemberships.add(convertMembership(membership));
		}
		
		return serializedMemberships;
	}
	
	private Set<Membership> deserializeMembeships(List<MembershipDto> membershipDtos){
		Set<Membership> deserializedMemberships = null;
		
		if(membershipDtos != null) {
			deserializedMemberships = new HashSet<>();
			
			for(MembershipDto membershipDto : membershipDtos)
				deserializedMemberships.add(membershipDao.findByUuid(membershipDto.getUuid()));
		}
		
		return deserializedMemberships;
	}	
}
