package com.cagnoni.U5W3D5WeeklyProject.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    @Email(message = "Email is required")
    String email;
    @NotEmpty(message = "Password is required")
    @Size(min = 10, max = 40, message = "Password length must be between 10 and 40 characters")
    String password;
    @NotEmpty(message = "Name is required")
    @Size(min = 1, max = 40, message = "Characters must be between 1 and 40")
    String name;
    @NotEmpty(message = "Surname is required")
    @Size(min = 1, max = 40, message = "Characters must be between 1 and 40")
    String surname;


}
