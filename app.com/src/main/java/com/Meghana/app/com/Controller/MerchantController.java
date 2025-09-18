package com.Meghana.app.com.Controller;

import com.Meghana.app.com.Dto.MerchantRequestdto;
import com.Meghana.app.com.Dto.MerchantResDto;
import com.Meghana.app.com.Dto.MerchantResponseDto;
import com.Meghana.app.com.Entity.Merchant;
import com.Meghana.app.com.Service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
     @Autowired
     private MerchantService merchantSer;
     @PostMapping
     public ResponseEntity<MerchantResponseDto> createMerchantDetails(@RequestBody MerchantRequestdto requestdto){
        return new ResponseEntity<>(merchantSer.createMerchant(requestdto), HttpStatus.CREATED);
     }

     @GetMapping

    public ResponseEntity<List<MerchantResDto>> getMerchants(){
         return new ResponseEntity<>(merchantSer.getAllMerchants(),HttpStatus.OK);
     }

     @GetMapping("/{merId}")
     public ResponseEntity<MerchantResDto> getMerchantFromId(@PathVariable Long merId){
         return new ResponseEntity<>(merchantSer.getMerchantById(merId),HttpStatus.OK);
     }

     @PutMapping("/{merId}")

    public  ResponseEntity<MerchantResDto> updateMerchantById(@PathVariable Long merId,@RequestBody MerchantRequestdto requestdto){
         return new ResponseEntity<>(merchantSer.updateMerchant(merId,requestdto),HttpStatus.OK);
     }

     @DeleteMapping("/{merId}")
     public  ResponseEntity<Void> deleteMerchantById(@PathVariable Long merId){
         merchantSer.deleteMerchant(merId);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
}

