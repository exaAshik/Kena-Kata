package com.ashik.kenakata.Controller;


import com.ashik.kenakata.Dto.User.CustomerCreateDto;
import com.ashik.kenakata.Dto.User.CustomerDto;
import com.ashik.kenakata.Entity.Customer;
import com.ashik.kenakata.Mapper.UserMapper.CustomerMapper;
import com.ashik.kenakata.Service.User.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerCreateDto customerCreateDto) {

        Customer customer = CustomerMapper.INSTANCE.customerCreateDtoToCustomer(customerCreateDto);
        System.out.println(customer);
        customer.setPassword(customerCreateDto.getPassword());
        customer.setEmail(customerCreateDto.getEmail());

        CustomerDto customerDto = CustomerMapper.INSTANCE.customerToCustomerDto(
                customerService.saveCustomer(
                        customer
                )
        );
        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {

        CustomerDto customerDto = CustomerMapper.INSTANCE.customerToCustomerDto(
                customerService.findCustomerById(id)
        );
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerCreateDto customerCreateDto) {

        CustomerDto customerDto = CustomerMapper.INSTANCE.customerToCustomerDto(
                customerService.updateCustomer(id,
                        CustomerMapper.INSTANCE.customerCreateDtoToCustomer(customerCreateDto)
                )
        );        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
