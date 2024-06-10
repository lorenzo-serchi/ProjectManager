package com.github.lorenzosmc;

import com.github.lorenzosmc.common.model.AbstractModelFactory;
import com.github.lorenzosmc.model.*;

import java.util.UUID;

public class ModelFactory extends AbstractModelFactory {
	private ModelFactory() {};
	
	public static Appointment createAppointment(UUID uuid) {
		return new Appointment(uuid);
	}
	
	public static Context createContext(UUID uuid) {
		return new Context(uuid);
	}

	public static Membership createMembership(Workgroup workgroup, Student member) {
		if(workgroup == null)
			throw new IllegalArgumentException("workgroup cannot be null");

		if(member == null)
			throw new IllegalArgumentException("member cannot be null");
		
		Membership membership = new Membership(generateUuid());
		membership.setLeader(false);
		membership.setPublishingConsent(true);
		membership.setMember(member);
		membership.setWorkgroup(workgroup);
		
		return membership;
	}
	
	public static Student createStudent(UUID uuid) {
		return new Student(uuid);
	}
	
	public static Task createTask(UUID uuid) {
		return new Task(uuid);
	}
	
	public static Workgroup createWorkgroup(Context context, Student creator) {
		if(context == null)
			throw new IllegalArgumentException("context cannot be null");
		
		if(creator == null)
			throw new IllegalArgumentException("creator cannot be null");

		Workgroup workgroup = new Workgroup(generateUuid());
		workgroup.setVerified(false);
		workgroup.setHidden(false);
		workgroup.setPublishingConsent(true);
		workgroup.setProgress(0);
		workgroup.setContext(context);
		workgroup.setCreator(creator);

		//FIXME move to model
		context.addWorkgroup(workgroup);
		
		//FIXME move to model
		creator.addCreatedWorkgroup(workgroup);
		
		return workgroup;
	}

	

}
