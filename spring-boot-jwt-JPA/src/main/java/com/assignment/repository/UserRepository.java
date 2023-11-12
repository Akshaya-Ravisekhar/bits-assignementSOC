package com.assignment.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entity.DAOUser;
import com.assignment.entity.UserAppointment;

@Repository
public interface UserRepository extends CrudRepository<DAOUser, Long>{

	 Optional<DAOUser> findByUsername(String username);

}
