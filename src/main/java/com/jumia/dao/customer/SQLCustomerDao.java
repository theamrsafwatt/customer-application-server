package com.jumia.dao.customer;

import java.util.List;

import java.util.logging.Level;

import javax.enterprise.inject.Typed;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jumia.entity.Customer;
import com.jumia.exception.ResourceNotFoundException;
import com.jumia.model.PaginatedSearchResult;
import com.jumia.repository.ICustomerRepository;
import com.jumia.utils.ApplicationLogger;
import com.jumia.utils.SqlUtils;

/**
 * @author Amr Elbassiouni
 *
 */
@Component
@Typed({ Object.class, SQLCustomerDao.class })
public class SQLCustomerDao implements ICustomerDao {

	@Autowired
	private ApplicationLogger logger;
	
	@Autowired
	protected ICustomerRepository customerRepository;
	
	@Autowired
	private SqlUtils sqlUtils;
	
	/**
	 * Find customer by identifier
	 * @param identifier
	 * @return Customer
	 */
	@Transactional
	public Customer findByIdentifier(String identifier) {
		logger.log(Level.INFO, "Executing findByIdentifier");
		Customer customer;
		customer = customerRepository.findByIdentifier(identifier);
		if (customer == null) {
			throw new ResourceNotFoundException();
		}
		return customer;
	}

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
	@Transactional
	public PaginatedSearchResult<Customer> search(String customerName, String phoneNumber, Boolean isValidPhoneNumber, String countryName
			, String countryCode, int page,  int pageSize) {
		logger.log(Level.INFO, "Executing search");
		List<Customer> customers = customerRepository.search(sqlUtils.formMatchQueryString(customerName)
				, sqlUtils.formMatchQueryString(phoneNumber), isValidPhoneNumber, sqlUtils.formMatchQueryString(countryName)
				, sqlUtils.formMatchQueryString(countryCode), String.valueOf(pageSize + 1), String.valueOf((page - 1) * pageSize));
		boolean hasNext = pageSize < customers.size();
		if(customers.size() > pageSize) {
			customers.remove(customers.size() - 1);
		}
		return new PaginatedSearchResult<>(customers, hasNext);
	}
}