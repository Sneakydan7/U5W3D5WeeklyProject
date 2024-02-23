package com.cagnoni.U5W3D5WeeklyProject.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Device with  " + id + " was not found");
    }
}
