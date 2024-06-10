package com.github.lorenzosmc.common.model;

import java.util.UUID;

public abstract class AbstractModelFactory {
	protected static UUID generateUuid() {
		return UUID.randomUUID();
	}
}
