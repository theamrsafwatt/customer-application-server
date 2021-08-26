package com.jumia;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jumia.controller.CustomerController;
import com.jumia.controller.HealthController;

@SpringBootTest
class CustomerApplicationTests {

	@Autowired
	private CustomerController customerController;
	
	@Autowired
	private HealthController healthController;
	
	@Test
	void contextLoads() {
		assertThat(customerController).isNotNull();
		assertThat(healthController).isNotNull();
	}
}