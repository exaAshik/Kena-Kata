package com.ashik.kenakata.Dto.User;

import com.ashik.kenakata.Entity.Seller;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class SellerBankAccountDto {

    private String accountHolderName;

    private String bankName;

    private String accountNumber;

    private String routingNumber;

    private String branchName;

}
