package com.ashik.kenakata.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "sellers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seller extends BaseEntity{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "company_registration_number", unique = true)
    private String companyRegistrationNumber;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SellerBankAccount> bankAccounts;

}
