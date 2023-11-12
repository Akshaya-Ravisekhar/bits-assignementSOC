package com.assignment.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.entity.Appointment;
import com.assignment.entity.Coach;
import com.assignment.entity.DAOUser;
import com.assignment.entity.UserAppointment;
import com.assignment.model.AppointmentDTO;
import com.assignment.repository.AppointmentRepository;
import com.assignment.repository.CoachRepository;
import com.assignment.repository.UserAppointmentRepository;
import com.assignment.repository.UserRepository;

@Service(value = "userService")
@Transactional
public class UserAppointmentServiceImpl implements UserAppointmentService{

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private UserAppointmentRepository userAppointmentRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public List<AppointmentDTO> getAppointments(String username) {
    	UserAppointment user = getUserApp(username);
        List<Appointment> appointments = user.getAppointments().stream()
                .filter(appointment -> appointment.getDateTimeSlot().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
        List<AppointmentDTO> appointmentDTOs = new ArrayList<>();

        for (Appointment appointment : appointments) {
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            appointmentDTO.setAppointmentId(appointment.getAppointmentId());
            appointmentDTO.setDateTimeSlot(appointment.getDateTimeSlot());
            appointmentDTO.setCoachName(appointment.getCoach().getCoachName());
            appointmentDTO.setUsername(appointment.getUser().getUsername());
            appointmentDTOs.add(appointmentDTO);
        }

        return appointmentDTOs;
    }
    
    public String bookAppointment(String username, AppointmentDTO appointmentRequest) {
    	DAOUser user = getUser(username);
        Coach coach = getCoach(appointmentRequest.getCoachName());
        UserAppointment userAppointment = userAppointmentRepository.findByUsername(username)
                .orElseGet(() -> {
                    UserAppointment newUserAppointment = new UserAppointment();
                    newUserAppointment.setUsername(username);
                    return newUserAppointment;
                });

        Appointment appointment = new Appointment();
        appointment.setDateTimeSlot(appointmentRequest.getDateTimeSlot());
        appointment.setCoach(coach);
        appointment.setUser(user);
        appointmentRepository.save(appointment);
        userAppointmentRepository.save(userAppointment);
        return "Appointment booked successfully!";
    }
    
    public String rescheduleAppointment(AppointmentDTO appointmentRequest) {
        Appointment existingAppointment = getAppointment(appointmentRequest.getAppointmentId());
        existingAppointment.setDateTimeSlot(appointmentRequest.getDateTimeSlot());

        Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
        return "Appointment rescheduled successfully!";
    }

    public String cancelAppointment(String username, AppointmentDTO appointmentRequest) {
        
        UserAppointment user = getUserApp(username);
        Appointment appointmentToDelete = getAppointment(appointmentRequest.getAppointmentId());

        // Ensure the appointment belongs to the specified user
        if (!user.getAppointments().contains(appointmentToDelete)) {
            throw new IllegalArgumentException("The specified appointment does not belong to the user: " + username);
        }

        // Remove the appointment from the user's list of appointments
        user.getAppointments().remove(appointmentToDelete);
        userAppointmentRepository.save(user);

        // Delete the appointment from the appointment table
        appointmentRepository.delete(appointmentToDelete);

        return "Appointment cancelled successfully";
    }

    private UserAppointment getUserApp(String username) {
        return userAppointmentRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found for username: " + username));
    }
    
    private DAOUser getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found for username: " + username));
    }
    
    private Coach getCoach(String coachName) {
        return coachRepository.findByCoachName(coachName)
                .orElseThrow(() -> new NoSuchElementException("Coach not found for coachName: " + coachName));
    }

    private Appointment getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NoSuchElementException("Appointment not found for appointmentId: " + appointmentId));
    }

  
}
