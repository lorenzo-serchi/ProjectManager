package com.github.lorenzosmc.common.dto.workgroup.outbound;

import java.time.Instant;
import java.util.UUID;

public class MembershipDto {
	private UUID uuid;

	private UUID workgroupUuid;

	private UUID memberUuid;

	private Boolean isLeader;

	private Instant joinDate;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getWorkgroupUuid() {
		return workgroupUuid;
	}

	public void setWorkgroupUuid(UUID workgroupUuid) {
		this.workgroupUuid = workgroupUuid;
	}

	public UUID getMemberUuid() {
		return memberUuid;
	}

	public void setMemberUuid(UUID memberUuid) {
		this.memberUuid = memberUuid;
	}

	public Boolean isLeader() {
		return isLeader;
	}

	public void setLeader(Boolean leader) {
		this.isLeader = leader;
	}

	public Instant getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Instant joinDate) {
		this.joinDate = joinDate;
	}

}
