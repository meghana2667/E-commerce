package com.Meghana.app.com.Service;

import com.Meghana.app.com.Dto.CustomerRequestDto;
import com.Meghana.app.com.Dto.MerchantResponseDto;
import com.Meghana.app.com.Entity.Customer;
import com.Meghana.app.com.Exception.ResourceAlreadyExists;
import com.Meghana.app.com.Repository.CustomerRepository;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private CartService cartService;


    public MerchantResponseDto registerCustomer(CustomerRequestDto  requestDto){
        Optional<Customer> existingNumber = customerRepo.findByPhoneNumber(requestDto.getPhoneNumber());

        if (existingNumber.isPresent()){
            throw new ResourceAlreadyExists("Customer with mobile number "+requestDto.getPhoneNumber()+" already exists");
        }
        Customer customer = new Customer();
        customer.setName(requestDto.getName());
        customer.setAddress(requestDto.getAddress());
        customer.setPhoneNumber(requestDto.getPhoneNumber());

        Customer savedCustomer = customerRepo.save(customer);
        cartService.createCart(savedCustomer);
        return new MerchantResponseDto("Customer Successfully created with cart");

    }
}
