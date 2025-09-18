package com.Meghana.app.com.Controller;

import com.Meghana.app.com.Dto.CustomerRequestDto;
import com.Meghana.app.com.Dto.MerchantResponseDto;
import com.Meghana.app.com.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class Customercontroller {

    @Autowired
    private CustomerService customerService;
    @PostMapping
    public ResponseEntity<MerchantResponseDto> createCustomer(@RequestBody CustomerRequestDto requestDto){
        return new ResponseEntity<>(customerService.registerCustomer(requestDto), HttpStatus.CREATED);
    }
}
