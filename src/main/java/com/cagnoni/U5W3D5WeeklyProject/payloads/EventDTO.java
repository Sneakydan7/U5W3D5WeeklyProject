package com.cagnoni.U5W3D5WeeklyProject.payloads;

import java.time.LocalDate;

public record EventDTO(String eventTitle, String description, LocalDate date, String location, Integer maxCapacity) {
}
