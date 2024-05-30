package com.example.uberprojectauthservice.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity //Entity annotation defines that a class can be mapped to a table
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Booking extends BaseModel{

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Enumerated(value= EnumType.STRING)
    BookingStatus bookingStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;

    private Long totalDistance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;
}
