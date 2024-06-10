package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Appointment;
import com.github.lorenzosmc.model.AppointmentStatus;

import java.util.List;

public class AppointmentDaoImpl extends AbstractBaseDao<Appointment> implements AppointmentDao {

	public AppointmentDaoImpl() {
		super(Appointment.class);
	}

	@Override
	public List<Appointment> getAppointmentsByStatus(AppointmentStatus status) {
		// TODO Auto-generated method stub
		return null;
	}
}
