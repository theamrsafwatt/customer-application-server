package com.jumia.dao.customer;

import com.jumia.entity.Customer;
import com.jumia.model.PaginatedSearchResult;

public interface ICustomerDao {

	public Customer findByIdentifier(String identifier);

	public PaginatedSearchResult<Customer> search(String customerName, String phoneNumber, Boolean isValidPhoneNumber, String countryName
			, String countryCode, int page,  int pageSize);
}