package com.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entity.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}