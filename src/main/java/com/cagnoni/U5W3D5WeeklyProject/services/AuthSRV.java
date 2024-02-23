package com.cagnoni.U5W3D5WeeklyProject.services;

import com.cagnoni.U5W3D5WeeklyProject.entities.User;
import com.cagnoni.U5W3D5WeeklyProject.exceptions.BadRequestException;
import com.cagnoni.U5W3D5WeeklyProject.payloads.UserDTO;
import com.cagnoni.U5W3D5WeeklyProject.payloads.UserLoginDTO;
import com.cagnoni.U5W3D5WeeklyProject.repositories.UserDAO;
import com.cagnoni.U5W3D5WeeklyProject.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthSRV {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserSRV userSRV;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UserDAO userDAO;


    public String authUserAndGenerateToken(UserLoginDTO payload) {
        User user = userSRV.findUserByEmail(payload.email());
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new RuntimeException("Error logging the user in: incorrect credentials");
        }

    }

    public User saveUser(UserDTO payload) {
        userDAO.findByEmail(payload.getEmail()).ifPresent(user -> {
            throw new BadRequestException("Email " + user.getEmail() + " already in use!");
        });

        User newUser = new User(payload.getEmail(), bcrypt.encode(payload.getPassword()), payload.getName(), payload.getSurname());

        return userDAO.save(newUser);
    }
}
