package com.assignment.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.assignment.entity.Coach;
import com.assignment.entity.UserAppointment;

public class AppointmentDTO {
    private Long appointmentId;
    private LocalDateTime dateTimeSlot;
    private String coachName;
    private String username;
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public LocalDateTime getDateTimeSlot() {
		return dateTimeSlot;
	}
	public void setDateTimeSlot(LocalDateTime dateTimeSlot) {
		this.dateTimeSlot = dateTimeSlot;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    
    

}
