package com.jumia.service;

import static com.jumia.helper.modelbuilder.ModelBuilder.getCustomerBuilder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jumia.entity.Customer;
import com.jumia.exception.ResourceNotFoundException;
import com.jumia.helper.BaseTest;
import com.jumia.model.PaginatedSearchResult;
import com.jumia.repository.ICustomerRepository;

/**
 * @author Amr Elbassiouni
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest extends BaseTest {

	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private ICustomerRepository customerRepository;
	
	@Before
	public void setup() throws JsonProcessingException {
        setupTestLogger();
    }
	
	@Test
	public void findByIdentifierFoundTest() {
		Customer customer = getCustomerBuilder().build();
		when(customerRepository.findByIdentifier(any())).thenReturn(customer);
		Customer foundCustomer = customerService.findByIdentifier(any());
		assertEquals(customer, foundCustomer);
	}

	@Test
	public void findByIdentifierNotFoundTest() {
		when(customerRepository.findByIdentifier(any())).thenReturn(null);
		try {
			customerService.findByIdentifier(any());
		} catch (Exception e) {
			assertEquals(e.getClass(), ResourceNotFoundException.class);
		}
	}
	
	@Test
	public void validatePhoneNumberByIdentifierWithoutDbFlagValidTest() {
		Customer customer = getCustomerBuilder().build();
		customer.setPhoneNumber("(237) 677046616");
		customer.getCountry().setPhoneNumberRegex("\\(237\\)\\ ?[2368]\\d{7,8}$");
		when(customerRepository.findByIdentifier(any())).thenReturn(customer);
		Boolean isPhoneNumberValid = customerService.validatePhoneNumberByIdentifier(any());
		assertTrue(isPhoneNumberValid);
	}

	@Test
	public void validatePhoneNumberByIdentifierWithoutDbFlagInvalidTest() {
		Customer customer = getCustomerBuilder().build();
		customer.setPhoneNumber("(212) 6546545369");
		customer.getCountry().setPhoneNumberRegex("\\(212\\)\\ ?[5-9]\\d{8}$");
		when(customerRepository.findByIdentifier(any())).thenReturn(customer);
		Boolean isPhoneNumberValid = customerService.validatePhoneNumberByIdentifier(any());
		assertFalse(isPhoneNumberValid);
	}

	@Test
	public void validatePhoneNumberByIdentifierWithDbFlagValidTest() {
		Customer customer = getCustomerBuilder().build();
		customer.setValidPhoneNumber(true);
		when(customerRepository.findByIdentifier(any())).thenReturn(customer);
		Boolean isPhoneNumberValid = customerService.validatePhoneNumberByIdentifier(any());
		assertTrue(isPhoneNumberValid);
	}

	@Test
	public void validatePhoneNumberByIdentifierWithDbFlagInvalidTest() {
		Customer customer = getCustomerBuilder().build();
		customer.setValidPhoneNumber(false);
		when(customerRepository.findByIdentifier(any())).thenReturn(customer);
		Boolean isPhoneNumberValid = customerService.validatePhoneNumberByIdentifier(any());
		assertFalse(isPhoneNumberValid);
	}
	
	@Test
	public void searchHasNextTrueTest() {
		int page = 1;
		int pageSize = 1;
		List<Customer> customers = new ArrayList<>();
		customers.add(getCustomerBuilder().build());
		customers.add(getCustomerBuilder().build());
		when(customerRepository.search(null, null, false, null
				, null, String.valueOf(pageSize + 1), String.valueOf((page - 1) * pageSize))).thenReturn(customers);
		PaginatedSearchResult<Customer> searchResult = customerService.search(null, null, false, null
				, null, page, pageSize);
		assertEquals(customers, searchResult.getData());
		assertEquals(true, searchResult.getHasNext());
	}
	
	@Test
	public void searchHasNextFalseTest() {
		int page = 1;
		int pageSize = 1;
		List<Customer> customers = new ArrayList<>();
		customers.add(getCustomerBuilder().build());
		when(customerRepository.search(null, null, false, null
				, null, String.valueOf(pageSize + 1), String.valueOf((page - 1) * pageSize))).thenReturn(customers);
		PaginatedSearchResult<Customer> searchResult = customerService.search(null, null, false, null
				, null, page, pageSize);
		assertEquals(customers, searchResult.getData());
		assertEquals(false, searchResult.getHasNext());
	}
	
	@Test
	public void validateValidPhoneNumberTest() {
		String phoneNumber = "(251) 914701723";
		String phoneNumberRegex = "\\(251\\)\\ ?[1-59]\\d{8}$";
		Boolean isPhoneNumberValid = customerService.validatePhoneNumber(phoneNumber, phoneNumberRegex);
		assertTrue(isPhoneNumberValid);
	}
	
	@Test
	public void validateInvalidPhoneNumberTest() {
		String phoneNumber = "(256) 7734127498";
		String phoneNumberRegex = "\\(256\\)\\ ?\\d{9}$";
		Boolean isPhoneNumberValid = customerService.validatePhoneNumber(phoneNumber, phoneNumberRegex);
		assertFalse(isPhoneNumberValid);
	}
}