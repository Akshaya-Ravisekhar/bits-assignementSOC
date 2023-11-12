package com.assignment.service;

import java.util.List;

import com.assignment.entity.Appointment;
import com.assignment.model.AppointmentDTO;

public interface UserAppointmentService {

	public List<AppointmentDTO> getAppointments(String userName);
	public String bookAppointment(String username, AppointmentDTO appointmentRequest);
	public String rescheduleAppointment(AppointmentDTO appointmentRequest);
	public String cancelAppointment(String username, AppointmentDTO appointmentRequest);
	
}
