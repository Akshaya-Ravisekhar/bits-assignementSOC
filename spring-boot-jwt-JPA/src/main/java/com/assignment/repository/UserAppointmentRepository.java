package com.assignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entity.Appointment;
import com.assignment.entity.UserAppointment;

@Repository
public interface UserAppointmentRepository extends CrudRepository<UserAppointment, Long> {
    Optional<UserAppointment> findByUsername(String username);
}