package com.Meghana.app.com.Repository;

import com.Meghana.app.com.Entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems,Long> {

    Optional<CartItems> findByCart_IdAndProduct_Id( Long cartId, Long prodId);
    Optional<CartItems> findByIdAndCart_Id(Long id,Long cartId);
    List<CartItems> findByCart_Id(Long cartId);

}
