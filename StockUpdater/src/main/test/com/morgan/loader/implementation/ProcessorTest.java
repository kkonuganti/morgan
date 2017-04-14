/**
 * 
 */
package com.morgan.loader.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.morgan.loader.repository.SalesRepository;


/**
 * @author kartheek.konuganti
 *
 */
public class ProcessorTest {

	/**
	 * @throws java.lang.Exception
	 */
	private Processor processor = new Processor();
	
	private SalesRepository repo=new SalesRepository();
	
	private SaleLogger logger = new SaleLogger();

	private String[] testStrings = { "apples at 10p", "apples at 100p", "apples at 10", "apples at p10", "Add 10p apples",
			"subtract 100p apples", "multiply 1000p apples", "divide 1000p apples", "divide 1000 100p", "add 1000 100p",
			"20 sales of apples at 10p each", "20 sales of apples at 10 each", "bb sales of apples at 10p each" };

	// add at 100p is valid and its is not in scope.

	@Before
	public void setup(){
		processor.setRepo(repo);
		logger.setSalesRepository(repo);
	}

	/**
	 * Test method for
	 * {@link com.morgan.loader.implementation.Processor#process(java.lang.String)}.
	 */
	
	@Test 
	public void testLogTotalForPRoducts(){
		
		for(String s:testStrings){
			processor.process(s);
		}
		Assert.assertTrue(repo.getSalesRepository().size()==3);
		logger.setProductIndex(processor.getProductIndex());
		logger.logSales();
	}

}
