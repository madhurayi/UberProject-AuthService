package com.example.uberprojectauthservice.dto;

import com.example.uberprojectauthservice.models.Passenger;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {

    private String id;

    private String email;

    private String password; //encrypted password

    private String name;

    private Date createdAt;

    public static PassengerDto fromPassenger(Passenger passenger) {
        PassengerDto passengerDto = new PassengerDto().builder()
                .id(passenger.getId().toString())
                .email(passenger.getEmail())
                .password(passenger.getPassword())
                .name(passenger.getName())
                .createdAt(passenger.getCreatedAt())
                .build();
        return passengerDto;
    }
}
