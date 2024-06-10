package com.github.lorenzosmc.common.model;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {
	@TableGenerator(
            name = "table_generator",
            table = "sequence_table",
            pkColumnName = "entity",
            valueColumnName = "next_id")
	@Id
	@GeneratedValue(
			strategy = GenerationType.TABLE,
			generator = "table_generator")
	private Long id;
	
	@Column(updatable = false, nullable = false, unique = true)
	private UUID uuid;

	protected BaseEntity(){}

	public BaseEntity(UUID uuid) {
		if(uuid == null)
			throw new IllegalArgumentException("uuid cannot be null");
		this.uuid = uuid;
	}

	public Long getId() {
		return id;
	}
	
	public UUID getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
	    int result = 17;
//	    result = 31 * result + ((id == null) ? 0 : id.hashCode()); //breaks contract between equals() and hashCode()
	    result = 31 * result + ((uuid == null) ? 0 : uuid.hashCode());
	    return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		// redundant
//		if(obj == null)
//			return false;
		if(!(obj instanceof BaseEntity))
			return false;

		return uuid.equals(((BaseEntity) obj).getUuid());		
	}

}
