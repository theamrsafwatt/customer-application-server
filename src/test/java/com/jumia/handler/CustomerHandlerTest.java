package com.jumia.handler;

import static com.jumia.helper.modelbuilder.ModelBuilder.getCustomerBuilder;
import static org.junit.Assert.assertEquals;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jumia.entity.Customer;
import com.jumia.exception.ResourceNotFoundException;
import com.jumia.helper.BaseTest;
import com.jumia.model.FailureResponse;
import com.jumia.model.PaginatedSearchResult;
import com.jumia.model.Response;
import com.jumia.model.SuccessResponse;
import com.jumia.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerHandlerTest extends BaseTest {

	@Autowired
	private CustomerHandler customerHandler;
	
	@MockBean
	private CustomerService customerService;
	
	@Before
	public void setup() throws JsonProcessingException {
        setupTestLogger();
    }	
	
	@Test
	public void findByIdentifierResponseOk() {
		Customer customer = getCustomerBuilder().build();
		when(customerService.findByIdentifier(any())).thenReturn(customer);
		ResponseEntity<Object> response = customerHandler.findByIdentifier(any());
		assertEquals(customer, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void findByIdentifierResponseNotFound() {
		when(customerService.findByIdentifier(any())).thenThrow(new ResourceNotFoundException());
		ResponseEntity<Object> response = customerHandler.findByIdentifier(any());
		assertEquals(FailureResponse.NOT_FOUND.getStatus(), ((Response) response.getBody()).getStatus());
		assertEquals(FailureResponse.NOT_FOUND.getDescription(), ((Response) response.getBody()).getDescription());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void findByIdentifierResponseRuntimeException() {
		when(customerService.findByIdentifier(any())).thenThrow(new RuntimeException());
		ResponseEntity<Object> response = customerHandler.findByIdentifier(any());
		assertEquals(FailureResponse.ERROR.getStatus(), ((Response) response.getBody()).getStatus());
		assertEquals(FailureResponse.ERROR.getDescription(), ((Response) response.getBody()).getDescription());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void validatePhoneNumberByIdentifierValid() {
		when(customerService.validatePhoneNumberByIdentifier(any())).thenReturn(true);
		ResponseEntity<Object> response = customerHandler.validatePhoneNumberByIdentifier(any());
		assertEquals(SuccessResponse.VALID_CUSTOMER_PHONE_NUMBER.getStatus(), ((Response) response.getBody()).getStatus());
		assertEquals(SuccessResponse.VALID_CUSTOMER_PHONE_NUMBER.getDescription(), ((Response) response.getBody()).getDescription());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void validatePhoneNumberByIdentifierInvalid() {
		when(customerService.validatePhoneNumberByIdentifier(any())).thenReturn(false);
		ResponseEntity<Object> response = customerHandler.validatePhoneNumberByIdentifier(any());
		assertEquals(FailureResponse.INVALID_CUSTOMER_PHONE_NUMBER.getStatus(), ((Response) response.getBody()).getStatus());
		assertEquals(FailureResponse.INVALID_CUSTOMER_PHONE_NUMBER.getDescription(), ((Response) response.getBody()).getDescription());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void validatePhoneNumberByIdentifierNotFound() {
		when(customerService.validatePhoneNumberByIdentifier(any())).thenThrow(new ResourceNotFoundException());
		ResponseEntity<Object> response = customerHandler.validatePhoneNumberByIdentifier(any());
		assertEquals(FailureResponse.NOT_FOUND.getStatus(), ((Response) response.getBody()).getStatus());
		assertEquals(FailureResponse.NOT_FOUND.getDescription(), ((Response) response.getBody()).getDescription());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void validatePhoneNumberByIdentifierRuntimeException() {
		when(customerService.validatePhoneNumberByIdentifier(any())).thenThrow(new RuntimeException());
		ResponseEntity<Object> response = customerHandler.validatePhoneNumberByIdentifier(any());
		assertEquals(FailureResponse.ERROR.getStatus(), ((Response) response.getBody()).getStatus());
		assertEquals(FailureResponse.ERROR.getDescription(), ((Response) response.getBody()).getDescription());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void search() {
		int page = 1;
		int pageSize = 1;
		List<Customer> customers = new ArrayList<>();
		customers.add(getCustomerBuilder().build());
		customers.add(getCustomerBuilder().build());
		PaginatedSearchResult<Customer> result = new PaginatedSearchResult<>(customers, true);
		when(customerService.search("", "", false, "", "", page, pageSize)).thenReturn(result);
		ResponseEntity<Object> response = customerHandler.search("", "", false, "", "", page, pageSize);
		assertEquals(true, ((PaginatedSearchResult<Customer>) response.getBody()).getHasNext());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void searchError() {
		int page = 1;
		int pageSize = 1;
		List<Customer> customers = new ArrayList<>();
		customers.add(getCustomerBuilder().build());
		customers.add(getCustomerBuilder().build());
		when(customerService.search("", "", false, "", "", page, pageSize)).thenThrow(new RuntimeException());
		ResponseEntity<Object> response = customerHandler.search("", "", false, "", "", page, pageSize);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
}