package com.Meghana.app.com.Repository;

import com.Meghana.app.com.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByPhoneNumber(Long phone);
}
