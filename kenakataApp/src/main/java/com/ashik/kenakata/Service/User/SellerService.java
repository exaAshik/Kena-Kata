package com.ashik.kenakata.Service.User;

import com.ashik.kenakata.Entity.Seller;

import java.util.List;

public interface SellerService {

    Seller saveSeller(Seller seller);

    Seller findSellerById(Long id);

    List<Seller> findAllSellers();

    Seller updateSeller(Long id, Seller seller);

    void deleteSeller(Long id);

}
