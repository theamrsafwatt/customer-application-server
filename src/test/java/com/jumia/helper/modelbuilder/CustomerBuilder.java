package com.jumia.helper.modelbuilder;

import static com.jumia.helper.modelbuilder.ModelBuilder.getCountryBuilder;

import java.util.Random;
import java.util.UUID;

import com.jumia.entity.Country;
import com.jumia.entity.Customer;

public class CustomerBuilder {
	
    private final Customer customer = new Customer();

    public Customer build() {
        return customer;
    }
    
    public CustomerBuilder(){
    	Country country = getCountryBuilder().build();
    	customer.setId((new Random()).nextInt());
    	customer.setIdentifier(UUID.randomUUID().toString());
    	customer.setName("Test Customer");
    	customer.setCountry(country);
    }
}