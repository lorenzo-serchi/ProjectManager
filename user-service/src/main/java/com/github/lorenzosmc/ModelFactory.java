package com.github.lorenzosmc;

import com.github.lorenzosmc.common.model.AbstractModelFactory;
import com.github.lorenzosmc.model.User;

public class ModelFactory extends AbstractModelFactory {
	private ModelFactory() {};

	public static User createUser() {
		User user = new User(generateUuid());
		user.setVerified(false);
		
		return user;
	}
}
