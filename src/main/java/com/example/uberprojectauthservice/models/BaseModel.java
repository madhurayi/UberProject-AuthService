package com.example.uberprojectauthservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseModel {

    @Id //this annotation makes the id property a primary key of our table
    //@GeneratedValue This annotation is used to specify the primary key generation strategy to use
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity means auto_increment
    protected Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP) //this annotation tells spring about the formats of Date object to be stored i.e., Date / Time / Timestamp
    @CreatedDate //this annotation tells spring that only handle it for object creation
    protected Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate //this annotation tells spring that only handle it for object update
    protected Date updatedAt;
}
