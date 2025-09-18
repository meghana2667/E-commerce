package com.Meghana.app.com.Controller;

import com.Meghana.app.com.Dto.CartItemResponseDto;
import com.Meghana.app.com.Dto.MerchantResponseDto;
import com.Meghana.app.com.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @PostMapping("/customer/{custId}/product/{proId}")
    public ResponseEntity<MerchantResponseDto> addProductToCart(@PathVariable Long custId, @PathVariable  Long proId, @RequestParam Integer quantity){


        return new ResponseEntity<>(cartService.addProductToCart(custId,proId,quantity), HttpStatus.CREATED);

    }

    @GetMapping("/customer/{custId}")
    private ResponseEntity<List<CartItemResponseDto>> getAllItems(@PathVariable Long custId){
        return new ResponseEntity<>(cartService.getAllItemsInCart(custId),HttpStatus.OK);
    }

    @DeleteMapping("/customer/{custId}/cartItem/{itemId}")


    private ResponseEntity<Void> removeItemFromCart(@PathVariable Long custId,@PathVariable Long itemId){
        cartService.removeCartItem(custId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/clear/customer/{custId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long custId){
        cartService.clearCart(custId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
