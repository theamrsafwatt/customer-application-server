package com.jumia.helper.modelbuilder;

/**
 * @author Amr Elbassiouni
 *
 */
public class ModelBuilder {

    public static CustomerBuilder getCustomerBuilder() {
        return new CustomerBuilder();
    }
    
    public static CountryBuilder getCountryBuilder() {
    	return new CountryBuilder();
    }
}