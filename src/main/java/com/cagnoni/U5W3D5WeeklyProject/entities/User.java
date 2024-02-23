package com.cagnoni.U5W3D5WeeklyProject.entities;

import com.cagnoni.U5W3D5WeeklyProject.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties({"password", "credentialsNonExpired", "accountNonExpired", "accountNonLocked", "enabled"})
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;
    private String email;
    private String password;
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ToString.Exclude

    @ManyToMany
    @JoinTable(name = "users_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "events_id"))
    private Set<Event> events = new LinkedHashSet<>();

    public User(String email, String password, String name, String surname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = Role.EVENT_MANAGER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
