package com.ashik.kenakata.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "admins")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User{

    @Column(name = "username")
    private String username;

    @Column(name = "last_login")
    private Instant lastLogin;

    @Column(name = "phone_number", length = 13)
    private String phoneNumber;
}
