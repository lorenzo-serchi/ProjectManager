package com.github.lorenzosmc.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class UpdateCategory {
	@Enumerated(EnumType.STRING)
	private UpdateType type;

	@Enumerated(EnumType.STRING)
	private UpdateReason reason;

	// TODO don't allow combinations that don't make sense
	
	@Override
	public String toString() {
		return "type: " + type.toString() + ", reason: " + reason.toString();
	}
	
	@Override
	public int hashCode() {
	    int result = 17;
	    result = 31 * result + ((type == null) ? 0 : type.hashCode());
	    result = 31 * result + ((reason == null) ? 0 : reason.hashCode());
	    return result;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof UpdateCategory))
			return false;

		UpdateCategory other = (UpdateCategory) obj;
		return (type == other.type) && (reason == other.reason);
	}

	public UpdateType getType() {
		// FIXME defensive-copy
		return type;
	}

	public void setType(UpdateType type) {
		// FIXME defensive-copy
		this.type = type;
	}

	public UpdateReason getReason() {
		// FIXME defensive-copy
		return reason;
	}

	public void setReason(UpdateReason reason) {
		// FIXME defensive-copy
		this.reason = reason;
	}

}
