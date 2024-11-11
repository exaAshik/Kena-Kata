package com.ashik.kenakata.Dto.User;

import com.ashik.kenakata.Entity.Product;
import com.ashik.kenakata.Entity.SellerBankAccount;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class SellerDto {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String phone;

    private String address;

    private String companyName;

    private String logoUrl;

    private String companyRegistrationNumber;

    private List<SellerBankAccount> bankAccounts;

}
