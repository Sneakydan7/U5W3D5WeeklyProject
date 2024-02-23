package com.cagnoni.U5W3D5WeeklyProject.services;

import com.cagnoni.U5W3D5WeeklyProject.entities.Event;
import com.cagnoni.U5W3D5WeeklyProject.entities.User;
import com.cagnoni.U5W3D5WeeklyProject.exceptions.UUIDNotFoundException;
import com.cagnoni.U5W3D5WeeklyProject.payloads.ReservationDTO;
import com.cagnoni.U5W3D5WeeklyProject.payloads.UserDTO;
import com.cagnoni.U5W3D5WeeklyProject.repositories.EventDAO;
import com.cagnoni.U5W3D5WeeklyProject.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserSRV {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private EventSRV eventSRV;

    public Page<User> getUsers(int pageNum, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNum, size, Sort.by(orderBy));
        return userDAO.findAll(pageable);

    }

    public User getUserById(UUID id) {
        return userDAO.findById(id).orElseThrow(() -> new UUIDNotFoundException(id));
    }

    public User updateUserById(UserDTO updatedUser, UUID id) {

        User found = getUserById(id);
        found.setName(updatedUser.getName());
        found.setSurname(updatedUser.getSurname());
        found.setEmail(updatedUser.getEmail());
        userDAO.save(found);
        return found;
    }

    public void deleteUser(UUID id) {
        User found = getUserById(id);
        userDAO.delete(found);
    }

    public User findUserByEmail(String email) {
        return userDAO.findByEmail(email).orElseThrow(() -> new RuntimeException("User with this email not found"));

    }

    public User reserveEventForUser(ReservationDTO payload, Long eventId) {
        User foundUser = userDAO.findByEmail(payload.email()).orElseThrow(() -> new RuntimeException("User not found"));
        Event foundEvent = eventSRV.getEventById(eventId);
        Set<Event> existingEvents = foundUser.getEvents();
        if (existingEvents == null) {
            existingEvents = new HashSet<>();
        }
        existingEvents.add(foundEvent);
        foundUser.setEvents(existingEvents);
        userDAO.save(foundUser);
        return foundUser;
    }

}
