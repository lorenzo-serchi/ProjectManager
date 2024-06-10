package com.github.lorenzosmc.common.service;

import com.github.lorenzosmc.common.dto.workgroup.inbound.WorkgroupCreationDto;
import com.github.lorenzosmc.common.dto.workgroup.outbound.WorkgroupDto;
import com.github.lorenzosmc.common.dto.workgroup.inbound.WorkgroupQueryDto;

import java.util.List;
import java.util.UUID;

public interface WorkgroupService {
	public List<WorkgroupDto> findAll(WorkgroupQueryDto queryParams);
	public WorkgroupDto findByUuid(UUID workgroupUuid);
	public WorkgroupDto create(WorkgroupCreationDto workgroupDto, UUID contextUuid, UUID creatorUuid);
	public WorkgroupDto update(WorkgroupDto workgroupDto);
}
