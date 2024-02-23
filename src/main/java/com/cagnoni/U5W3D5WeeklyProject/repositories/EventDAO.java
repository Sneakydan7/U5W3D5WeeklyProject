package com.cagnoni.U5W3D5WeeklyProject.repositories;

import com.cagnoni.U5W3D5WeeklyProject.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDAO extends JpaRepository<Event, Long> {
}
