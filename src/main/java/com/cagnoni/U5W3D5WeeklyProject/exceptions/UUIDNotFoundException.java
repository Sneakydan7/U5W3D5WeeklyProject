package com.cagnoni.U5W3D5WeeklyProject.exceptions;

import java.util.UUID;

public class UUIDNotFoundException extends RuntimeException {
    public UUIDNotFoundException(UUID id) {
        super("User with  " + id + " was not found");
    }
}