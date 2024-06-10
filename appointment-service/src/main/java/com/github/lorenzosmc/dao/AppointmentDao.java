package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.common.dao.BaseDao;
import com.github.lorenzosmc.model.Appointment;
import com.github.lorenzosmc.model.AppointmentStatus;

import java.util.List;

public interface AppointmentDao extends BaseDao<Appointment> {
	public List<Appointment> getAppointmentsByStatus(AppointmentStatus status);
	//TODO continue... depends on what Service Layer needs.
}
