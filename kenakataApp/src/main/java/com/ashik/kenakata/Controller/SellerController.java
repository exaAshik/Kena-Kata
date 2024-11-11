package com.ashik.kenakata.Controller;

import com.ashik.kenakata.Dto.User.SellerDto;
import com.ashik.kenakata.Entity.Seller;
import com.ashik.kenakata.Exception.ResourceNotFoundException;
import com.ashik.kenakata.Mapper.UserMapper.SellerMapper;
import com.ashik.kenakata.Service.User.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<?> createSeller(@RequestBody SellerDto sellerDto) {

        Seller seller = SellerMapper.INSTANCE.sellerDtoToSeller(sellerDto);
        seller.setPassword(sellerDto.getPassword());
        seller.setEmail(sellerDto.getEmail());
        SellerDto sellerDto1 = SellerMapper.INSTANCE.sellerToSellerDto(
                sellerService.saveSeller(seller)
        );

        return new ResponseEntity<>(sellerDto1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSellerById(@PathVariable Long id) {
        try {
            SellerDto sellerDto = SellerMapper.INSTANCE.sellerToSellerDto(
                    sellerService.findSellerById(id)
            );
            return new ResponseEntity<>(sellerDto, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSellers() {
        try {
            List<Seller> sellers = sellerService.findAllSellers();
            return new ResponseEntity<>(sellers, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody Seller seller) {
        try {
            Seller updatedSeller = sellerService.updateSeller(id, seller);
            return new ResponseEntity<>(updatedSeller, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        try {
            sellerService.deleteSeller(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
