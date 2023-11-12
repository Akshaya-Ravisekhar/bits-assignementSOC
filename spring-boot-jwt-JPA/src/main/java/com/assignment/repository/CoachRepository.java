package com.assignment.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entity.Coach;

@Repository
public interface CoachRepository extends CrudRepository<Coach, Long> {
    Optional<Coach> findByCoachName(String coachName);
}