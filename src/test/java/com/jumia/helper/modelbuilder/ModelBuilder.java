package com.jumia.helper.modelbuilder;

public class ModelBuilder {

    public static CustomerBuilder getCustomerBuilder() {
        return new CustomerBuilder();
    }
    
    public static CountryBuilder getCountryBuilder() {
    	return new CountryBuilder();
    }
}