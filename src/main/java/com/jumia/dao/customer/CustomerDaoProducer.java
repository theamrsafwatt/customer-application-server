package com.jumia.dao.customer;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.jumia.dao.DBlist;

/**
 * @author Amr Elbassiouni
 *
 */
public class CustomerDaoProducer  {

	@Autowired
	private Instance<ICustomerDao> customerDao;

	private static final String DAO = "SQLITE";

	/**
	 * Produce and implementation for ICustomerDao
	 * @return ICustomerDao
	 */
	@Produces
	public ICustomerDao produce() {

		if(DAO.equals(DBlist.SQLITE.toString())) {
			return customerDao.select(SQLCustomerDao.class).get();
		}
		
		return null;
	}
}