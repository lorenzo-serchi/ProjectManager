package com.github.lorenzosmc.common.dto.workgroup.inbound;

import jakarta.validation.constraints.NotNull;

public class WorkgroupCreationDto {
	@NotNull(message = "publishingConsent cannot be null")
	private Boolean publishingConsent;

	@NotNull(message = "isVisible cannot be null")
	private Boolean isVisible;
	
	public Boolean getPublishingConsent() {
		return publishingConsent;
	}

	public void setPublishingConsent(Boolean publishingConsent) {
		this.publishingConsent = publishingConsent;
	}

	public Boolean isVisible() {
		return isVisible;
	}

	public void setVisible(Boolean visible) {
		this.isVisible = visible;
	}
}
