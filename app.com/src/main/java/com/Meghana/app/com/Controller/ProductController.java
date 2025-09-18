package com.Meghana.app.com.Controller;

import com.Meghana.app.com.Dto.MerchantResponseDto;
import com.Meghana.app.com.Dto.ProductRequestDto;
import com.Meghana.app.com.Dto.ProductResponseDto;
import com.Meghana.app.com.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/merchant/{id}")

    public ResponseEntity<MerchantResponseDto> createProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestDto){
        return new ResponseEntity<>(productService.createProduct(id,requestDto), HttpStatus.CREATED);
    }

    @GetMapping

    public  ResponseEntity<List<ProductResponseDto>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }


    @GetMapping("/merchant/{id}")

    public  ResponseEntity<List<ProductResponseDto>> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductsOfMerchant(id),HttpStatus.OK);
    }

    @PutMapping("/{prodId}")
    public ResponseEntity<MerchantResponseDto> updateProduct(@PathVariable Long prodId, @RequestBody ProductRequestDto requestDto){
        return ResponseEntity.ok(productService.updateProduct(prodId,requestDto));
    }

    @DeleteMapping("/{proId}")

    public ResponseEntity<Void> deleteProductById(@PathVariable Long proId){
        productService.deleteProduct(proId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
