package com.example.uberprojectauthservice.helpers;

import com.example.uberprojectauthservice.models.Passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Why we need this class ?
// Because spring security works on UserDetails polymorphic type for auth

public class AuthPassengerDetails extends Passenger implements UserDetails {

    private String username;

    private String password;


    public AuthPassengerDetails(Passenger passenger) {
        this.username=passenger.getEmail();
        this.password=passenger.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // Below set of method are not much of a concern
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
