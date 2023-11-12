package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.AppointmentDTO;
import com.assignment.service.UserAppointmentService;


@RequestMapping({ "/wecare" })
@RestController
public class WeCareApplicationController {
	
	@Autowired
    private UserAppointmentService appointmentService;

	@GetMapping("/hello")
    public String helloWrorld() {
        return "hello";
    }
	
	@GetMapping("/getAppointments/{username}")
    public ResponseEntity<List<AppointmentDTO>> getAppointments(@PathVariable String username) {
        List<AppointmentDTO> appointments = appointmentService.getAppointments(username);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping("/bookAppointment/{username}")
    public ResponseEntity<String> bookAppointment(
            @PathVariable String username,
            @RequestBody AppointmentDTO appointmentRequest) {
        String response = appointmentService.bookAppointment(username, appointmentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/rescheduleAppointment")
    public ResponseEntity<String> rescheduleAppointment(
            @RequestBody AppointmentDTO appointmentRequest) {
        String response = appointmentService.rescheduleAppointment(appointmentRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/cancelAppointment/{username}")
    public ResponseEntity<String> cancelAppointment(
            @PathVariable String username,
            @RequestBody AppointmentDTO appointmentRequest) {
        String response = appointmentService.cancelAppointment(username, appointmentRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}