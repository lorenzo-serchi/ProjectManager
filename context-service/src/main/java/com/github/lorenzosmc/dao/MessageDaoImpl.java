package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Message;

public class MessageDaoImpl extends AbstractBaseDao<Message> implements MessageDao {

	public MessageDaoImpl() {
		super(Message.class);
	}
}
