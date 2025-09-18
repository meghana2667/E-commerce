package com.Meghana.app.com.Service;

import com.Meghana.app.com.Dto.MerchantResponseDto;
import com.Meghana.app.com.Dto.ProductRequestDto;
import com.Meghana.app.com.Dto.ProductResponseDto;
import com.Meghana.app.com.Entity.Merchant;
import com.Meghana.app.com.Entity.Product;
import com.Meghana.app.com.Exception.ResourceAlreadyExists;
import com.Meghana.app.com.Exception.ResourceNotFoundEx;
import com.Meghana.app.com.Repository.MerchantRepository;
import com.Meghana.app.com.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private MerchantRepository merchantRepo;
    private ProductResponseDto mapToDto(Product product){
        ProductResponseDto responseObj = new ProductResponseDto();
        responseObj.setProductId(product.getId());
        responseObj.setProductName(product.getProductName());
        responseObj.setStock(product.getStock());
        responseObj.setPrice(product.getPrice());
        responseObj.setDescription(product.getDescription());
        responseObj.setCompanyName(product.getMerchant().getCompanyName());

        return responseObj;
    }


    public MerchantResponseDto createProduct(Long id, ProductRequestDto requestDto){
        Merchant merchant = merchantRepo.findById(id).orElseThrow(()-> new ResourceNotFoundEx("Merchant with id "+id+" not found"));

        Optional<Product> existingProduct = productRepo.findByProductName(requestDto.getName());

        if (existingProduct.isPresent()){
            throw new ResourceAlreadyExists("Product with name "+requestDto.getName()+" already exists");
        }

        Product product = new Product();
        product.setDescription(requestDto.getDescription());
        product.setStock(requestDto.getStock());
        product.setPrice(requestDto.getPrice());
        product.setMerchant(merchant);
        product.setProductName(requestDto.getName());


        Product saveProduct = productRepo.save(product);
        return new MerchantResponseDto("Product is successfully saved with product id "+saveProduct.getId());
    }

    public List<ProductResponseDto> getAllProducts(){
        return productRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public List<ProductResponseDto> getProductsOfMerchant(Long id){
        Merchant merchant = merchantRepo.findById(id).orElseThrow(()-> new ResourceNotFoundEx("Merchant with id "+id +" does not exits"));
        return productRepo.findByMerchant_Id(id)
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    public MerchantResponseDto updateProduct(Long id,ProductRequestDto requestDto){
        Product existingProduct = productRepo.findById(id).orElseThrow(()->new ResourceNotFoundEx("Product with id "+id+ " does exists"));
        existingProduct.setProductName(requestDto.getName());
        existingProduct.setStock(requestDto.getStock());
        existingProduct.setDescription(requestDto.getDescription());
        existingProduct.setPrice(requestDto.getPrice());

        productRepo.save(existingProduct);
        return new MerchantResponseDto("Product successfully updated");
    }

    public void  deleteProduct(Long id){
        Product existingProduct = productRepo.findById(id).orElseThrow(()->new ResourceNotFoundEx("Product with id "+id+ " does not exists"));

         productRepo.delete(existingProduct);
    }
}
