package com.cagnoni.U5W3D5WeeklyProject.controllers;

import com.cagnoni.U5W3D5WeeklyProject.entities.User;
import com.cagnoni.U5W3D5WeeklyProject.payloads.UserDTO;
import com.cagnoni.U5W3D5WeeklyProject.services.AuthSRV;
import com.cagnoni.U5W3D5WeeklyProject.services.UserSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserCTRL {
    @Autowired
    private UserSRV userSRV;
    @Autowired
    private AuthSRV authSRV;

    @GetMapping
    @PreAuthorize("hasRole('EVENT_MANAGER')")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy) {
        return this.userSRV.getUsers(page, size, orderBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('EVENT_MANAGER')")
    public User getUserById(@PathVariable UUID id) {
        return this.userSRV.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('EVENT_MANAGER')")
    public User saveUser(@RequestBody UserDTO newUser) {
        return this.authSRV.saveUser(newUser);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EVENT_MANAGER')")
    public User updateUserById(@PathVariable UUID id, @RequestBody @Validated UserDTO updatedUser, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new RuntimeException();
        } else {
            return this.userSRV.updateUserById(updatedUser, id);
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('EVENT_MANAGER')")
    public void deleteUserById(@PathVariable UUID id) {
        this.userSRV.deleteUser(id);
    }


    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public User getMeAndUpdate(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody UserDTO updatedUser) {
        return this.userSRV.updateUserById(updatedUser, currentAuthenticatedUser.getId());
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getMeAndDelete(@AuthenticationPrincipal User currentAuthenticatedUser) {
        this.userSRV.getUserById(currentAuthenticatedUser.getId());
    }

}
