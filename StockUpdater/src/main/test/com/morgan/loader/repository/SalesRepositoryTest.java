/**
 * 
 */
package com.morgan.loader.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.morgan.loader.model.AdjustmentTypeConstant;
import com.morgan.loader.model.Adjustsment;
import com.morgan.loader.model.Sale;

/**
 * @author kartheek.konuganti
 *
 */
public class SalesRepositoryTest {

	/**
	 * @throws java.lang.Exception
	 */

	private Sale sale, sale2, sale3;
	
	private SalesRepository repo = new SalesRepository(); 

	@Before
	public void setUp() throws Exception {
		sale = new Sale("apple", 1, 0.10);
		sale2 = new Sale("mangos", 4, 1.00);
		sale3 = new Sale("grapes", 10, .50);
		repo.addSale(sale);
		repo.addSale(sale2);
		repo.addSale(sale3);
	}
	
	@Test
	public void testGetProductTotalSale() {
		Sale sale4 = new Sale("apple", 10, 2);
		repo.addSale(sale4);
		
		Sale sale=repo.getTotalSaleForProduct("apple");
		//after adjustment
		assertTrue(sale.getQuantity()==11);
	}

	@Test
	public void testAdjustSaleAdd() {
		double totalValue = sale.getTotal_sale_value();
		//before adjustment
		assertTrue(sale.getTotal_sale_value()==0.1);
		repo.adjustSale("apple", 0.5, AdjustmentTypeConstant.ADD);
		
		//after adjustment
		assertTrue(sale.getTotal_sale_value()==0.6);
		assertFalse(sale.getTotal_sale_value()==totalValue);
	}
	
	@Test
	public void testAdjustSaleSubtract() {
		double totalValue = sale2.getTotal_sale_value();
		//before adjustment
		assertTrue(sale2.getTotal_sale_value()==4.0);
		repo.adjustSale("mangos", 0.5, AdjustmentTypeConstant.SUBTRACT);
		
		//after adjustment
		assertTrue(sale2.getTotal_sale_value()==2);
		assertFalse(sale2.getTotal_sale_value()==totalValue);
	}
	
	@Test
	public void testAdjustSaleSubtractNeg() {
		double totalValue = sale2.getTotal_sale_value();
		//before adjustment
		assertTrue(sale2.getTotal_sale_value()==4.0);
		repo.adjustSale("mangos", 1.2, AdjustmentTypeConstant.SUBTRACT);
		
		//after adjustment
		assertTrue(sale2.getTotal_sale_value()==-0.8);
		assertFalse(sale2.getTotal_sale_value()==totalValue);
	}
	
	@Test
	public void testAdjustSaleSubtractNeg2() {
		double totalValue = sale2.getTotal_sale_value();
		//before adjustment
		assertTrue(sale2.getTotal_sale_value()==4.0);
		repo.adjustSale("mangos", 2.5, AdjustmentTypeConstant.SUBTRACT);
		
		//after adjustment
		assertTrue(sale2.getTotal_sale_value()==-6.0);
		assertFalse(sale2.getTotal_sale_value()==totalValue);
		
		ArrayList<Adjustsment> adjustments = new ArrayList<>();
		
		adjustments=repo.getAdjustments();
		//after adjustment
		for(Adjustsment ad:adjustments){
			System.out.println(ad);
		}
		
	}
	
	@Test
	public void testAdjustSaleMultiply() {
		double totalValue = sale3.getTotal_sale_value();
		//before adjustment
		assertTrue(sale3.getTotal_sale_value()==5.0);
		repo.adjustSale("grapes", 0.5, AdjustmentTypeConstant.MULTIPLY);
		
		//after adjustment
		assertTrue(sale3.getTotal_sale_value()==2.5);
		assertFalse(sale3.getTotal_sale_value()==totalValue);
	}
	
	@Test
	public void testGetProductDetails() {
		Sale sale4 = new Sale("apple", 10, 1.5);
		repo.addSale(sale4);
		ArrayList<Sale> sales = new ArrayList<Sale>();
		
		sales=(ArrayList<Sale>) repo.getSalesForProduct("apple");
		//after adjustment
		assertTrue(sales.size()==2);
	}

	
	@Test
	public void testGetProductDetailsNoSales() {
		ArrayList<Sale> sales = new ArrayList<Sale>();
		
		sales=(ArrayList<Sale>) repo.getSalesForProduct("apples");
		//after adjustment
		assertTrue(sales.size()==0);
	}
	

	

}
