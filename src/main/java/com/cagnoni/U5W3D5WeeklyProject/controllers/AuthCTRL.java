package com.cagnoni.U5W3D5WeeklyProject.controllers;

import com.cagnoni.U5W3D5WeeklyProject.entities.User;
import com.cagnoni.U5W3D5WeeklyProject.payloads.LoginResponseDTO;
import com.cagnoni.U5W3D5WeeklyProject.payloads.UserDTO;
import com.cagnoni.U5W3D5WeeklyProject.payloads.UserLoginDTO;
import com.cagnoni.U5W3D5WeeklyProject.services.AuthSRV;
import com.cagnoni.U5W3D5WeeklyProject.services.UserSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCTRL {
    @Autowired
    private UserSRV userSRV;
    @Autowired
    private AuthSRV authSRV;

    @PostMapping("/login")
    private LoginResponseDTO login(@RequestBody UserLoginDTO payload) {
        return new LoginResponseDTO(authSRV.authUserAndGenerateToken(payload));

    }

    @PostMapping("/register")
    private User register(@RequestBody UserDTO payload) {
        return this.authSRV.saveUser(payload);
    }

}
