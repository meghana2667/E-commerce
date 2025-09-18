package com.Meghana.app.com.Service;

import com.Meghana.app.com.Dto.CartItemResponseDto;
import com.Meghana.app.com.Dto.MerchantResponseDto;
import com.Meghana.app.com.Entity.Cart;
import com.Meghana.app.com.Entity.CartItems;
import com.Meghana.app.com.Entity.Customer;
import com.Meghana.app.com.Entity.Product;
import com.Meghana.app.com.Exception.ResourceNotFoundEx;
import com.Meghana.app.com.Repository.CartItemRepository;
import com.Meghana.app.com.Repository.CartRepository;
import com.Meghana.app.com.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CartItemRepository cartItemRepo;


    public CartItemResponseDto mapToDto(CartItems cartItems){
        CartItemResponseDto response=new CartItemResponseDto();
        response.setId(cartItems.getId());
        response.setTotalPrice(cartItems.getTotalPrice());
        response.setQuantity(cartItems.getQuantity());
        response.setProductName(cartItems.getProduct().getProductName());

        return response;

    }

    public void  createCart(Customer customer){
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartRepo.save(cart);
    }


    public MerchantResponseDto addProductToCart(Long customerId,Long productId,Integer quantity){
        Product product = productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundEx("Product with id "+productId+" does not exists"));

        Cart cart = cartRepo.findByCustomer_Id(customerId).orElseThrow(()->new RuntimeException("Customer with id "+customerId+" does not have cart"));
        CartItems cartItems = cartItemRepo.findByCart_IdAndProduct_Id(cart.getId(), productId)
                .orElse(new CartItems(null, cart, product, product.getPrice()*quantity, quantity));


        if (cartItems.getId()!=null){
            cartItems.setQuantity(cartItems.getQuantity()+quantity);
            cartItems.setTotalPrice(cartItems.getQuantity()*product.getPrice());
        }


        CartItems saveCartItem = cartItemRepo.save(cartItems);

        return new MerchantResponseDto("Product is successfully added to cart item with id "+saveCartItem.getId());

    }

    public List<CartItemResponseDto> getAllItemsInCart(Long customerId){
        Cart cart = cartRepo.findByCustomer_Id(customerId)
                .orElseThrow(()->new ResourceNotFoundEx("Customer does not have cart"));


        return cart.getCartItems().stream()
                .map(this::mapToDto)
                .toList();




    }

    public void removeCartItem(Long customerId,Long cartId){
        Cart cart = cartRepo.findByCustomer_Id(customerId).orElseThrow(()->new ResourceNotFoundEx("Customer does not have cart"));
        CartItems cartItems = cartItemRepo.findByIdAndCart_Id(cartId,cart.getId()).orElseThrow(()->new RuntimeException("item not found in cart"));

        cartItemRepo.delete(cartItems);
    }

    public void clearCart(Long customerId){
        Cart cart = cartRepo.findByCustomer_Id(customerId).orElseThrow(()->new ResourceNotFoundEx("Customer does not have cart"));
        List<CartItems> cartItems=cartItemRepo.findByCart_Id(cart.getId());

        cartItemRepo.deleteAll(cartItems);
    }


}
