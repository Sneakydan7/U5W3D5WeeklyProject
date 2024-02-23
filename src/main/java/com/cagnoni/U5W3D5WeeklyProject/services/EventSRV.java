package com.cagnoni.U5W3D5WeeklyProject.services;

import com.cagnoni.U5W3D5WeeklyProject.entities.Event;
import com.cagnoni.U5W3D5WeeklyProject.exceptions.NotFoundException;
import com.cagnoni.U5W3D5WeeklyProject.payloads.EventDTO;
import com.cagnoni.U5W3D5WeeklyProject.repositories.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventSRV {

    @Autowired
    private EventDAO eventDAO;

    public Page<Event> getDevices(int pageNum, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNum, size, Sort.by(orderBy));
        return eventDAO.findAll(pageable);
    }

    public Event getEventById(Long id) {
        return eventDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Event saveEvent(EventDTO newEvent) {
        return eventDAO.save(new Event(
                newEvent.eventTitle(),
                newEvent.description(),
                newEvent.date(),
                newEvent.location(),
                newEvent.maxCapacity()));
    }

    public Event updateEventById(EventDTO updatedEvent, Long id) {
        Event found = getEventById(id);
        found.setEventTitle(updatedEvent.eventTitle());
        found.setDescription(updatedEvent.description());
        found.setDate(updatedEvent.date());
        found.setLocation(updatedEvent.location());
        found.setMaxCapacity(updatedEvent.maxCapacity());
        eventDAO.save(found);
        return found;
    }

    public void deleteEvent(Long id) {
        Event found = getEventById(id);
        eventDAO.delete(found);
    }

}
