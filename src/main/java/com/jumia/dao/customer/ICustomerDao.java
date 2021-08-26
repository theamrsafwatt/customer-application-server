package com.jumia.dao.customer;

import com.jumia.entity.Customer;
import com.jumia.model.PaginatedSearchResult;

/**
 * @author Amr Elbassiouni
 *
 */
public interface ICustomerDao {

	/**
	 * Find customer by identifier
	 * @param identifier
	 * @return Customer
	 */
	public Customer findByIdentifier(String identifier);

	/**
	 * Search customers documents
	 * @param customerName
	 * @param phoneNumber
	 * @param isValidPhoneNumber
	 * @param countryName
	 * @param countryCode
	 * @param page
	 * @param pageSize
	 * @return PaginatedSearchResult<Customer>
	 */
	public PaginatedSearchResult<Customer> search(String customerName, String phoneNumber, Boolean isValidPhoneNumber, String countryName
			, String countryCode, int page,  int pageSize);
}