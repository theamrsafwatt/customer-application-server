package com.jumia.helper.modelbuilder;

import java.util.Random;

import com.jumia.entity.Country;

public class CountryBuilder {

    private final Country country = new Country();

    public Country build() {
        return country;
    }
    
    public CountryBuilder(){
    	Country country = new Country();
    	country.setId((new Random()).nextInt());
    	country.setName("Test Country");
    	country.setAbbreviation("TC");
    }
}
