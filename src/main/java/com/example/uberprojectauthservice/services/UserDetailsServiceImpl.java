package com.example.uberprojectauthservice.services;

import com.example.uberprojectauthservice.helpers.AuthPassengerDetails;
import com.example.uberprojectauthservice.models.Passenger;
import com.example.uberprojectauthservice.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private PassengerRepository passengerRepository;
    public UserDetailsServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Passenger> passenger=this.passengerRepository.findByEmail(username);
        if(passenger.isPresent()){
            return new AuthPassengerDetails(passenger.get());
        }
        throw new UsernameNotFoundException("Cannot find the Passenger by the given Email");
    }
}
