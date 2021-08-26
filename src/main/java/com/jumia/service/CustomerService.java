package com.jumia.service;

import java.util.logging.Level;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumia.dao.customer.ICustomerDao;
import com.jumia.entity.Customer;
import com.jumia.model.PaginatedSearchResult;
import com.jumia.utils.ApplicationLogger;

/**
 * @author Amr Elbassiouni
 *
 */
@Service
public class CustomerService {

	@Autowired
	private ApplicationLogger logger;

	@Autowired
	private ICustomerDao customerDao;

	@Autowired
	private com.jumia.utils.StringUtils stringUtils;

	/**
	 * Find customer by identifier
	 * @param identifier
	 * @return Customer
	 */
	@Transactional
	public Customer findByIdentifier(String identifier) {
		logger.log(Level.INFO, "Executing findByIdentifier");
		return customerDao.findByIdentifier(identifier);
	}

	/**
	 * Validate customer phone number using phone number identifier
	 * @param identifier
	 * @return Customer
	 */
	@Transactional
	public boolean validatePhoneNumberByIdentifier(String identifier) {
		logger.log(Level.INFO, "Executing validatePhoneNumberByIdentifier");
		Customer customer = findByIdentifier(identifier);
		if(customer.getValidPhoneNumber() != null) {
			logger.log(Level.INFO, "Customer phone number state found in the retrieved record");
			return customer.getValidPhoneNumber();
		} else {
			logger.log(Level.INFO, "Customer phone number state can not be found in the retrieved record");
			return validatePhoneNumber(customer.getPhoneNumber(), customer.getCountry().getPhoneNumberRegex());
		}
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
		return customerDao.search(customerName, phoneNumber, isValidPhoneNumber, countryName
				, countryCode, page, pageSize);
	}

	/**
	 * Validate phone number using phone number and phone number regex
	 * @param identifier
	 * @return Customer
	 */
	public boolean validatePhoneNumber(String phoneNumber, String phoneNumberRegex) {
		logger.log(Level.INFO, "Executing validatePhoneNumber for phoneNumber: " 
				+ phoneNumber + " , phoneNumberRegex: " + phoneNumberRegex);
		return stringUtils.validateStringAgainstRegex(phoneNumber, phoneNumberRegex);
	}
}