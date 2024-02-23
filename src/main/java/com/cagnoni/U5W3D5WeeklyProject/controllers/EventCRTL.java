package com.cagnoni.U5W3D5WeeklyProject.controllers;

import com.cagnoni.U5W3D5WeeklyProject.entities.Event;
import com.cagnoni.U5W3D5WeeklyProject.payloads.EventDTO;
import com.cagnoni.U5W3D5WeeklyProject.services.EventSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventCRTL {
    @Autowired
    private EventSRV eventSRV;

    @GetMapping
    public Page<Event> getDevices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy) {
        return this.eventSRV.getDevices(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return this.eventSRV.getEventById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event saveEvent(@RequestBody EventDTO newDevice) {
        return this.eventSRV.saveEvent(newDevice);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEventById(@PathVariable Long id) {
        this.eventSRV.deleteEvent(id);
    }


}
