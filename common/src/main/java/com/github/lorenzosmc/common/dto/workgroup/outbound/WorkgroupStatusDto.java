package com.github.lorenzosmc.common.dto.workgroup.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WorkgroupStatusDto {
	@JsonProperty("notWorking")
	NOT_WORKING,
	@JsonProperty("working")
	WORKING,
	@JsonProperty("retired")
	RETIRED
}
