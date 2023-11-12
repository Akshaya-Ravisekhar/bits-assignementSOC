package com.assignment.entity;

import java.time.LocalDateTime;

import javax.persistence.*;


@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private LocalDateTime dateTimeSlot;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private DAOUser user;

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

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public DAOUser getUser() {
		return user;
	}

	public void setUser(DAOUser user) {
		this.user = user;
	}
	
	
	
}
