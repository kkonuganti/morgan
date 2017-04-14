package com.morgan.loader.implementation;

import java.util.HashSet;

import com.morgan.loader.model.Adjustsment;
import com.morgan.loader.model.Sale;
import com.morgan.loader.repository.SalesRepository;

public class SaleLogger {
	
	private SalesRepository salesRepository = new  SalesRepository();
	
	//product index is used to store unix product names for faster access from repo.
	private HashSet<String> productIndex = new HashSet<>(0);
	

/*This Method will log total sale prices for each product
 * 
 * 
 * */
	public void logSales(){
		if(!productIndex.isEmpty() || !salesRepository.getSalesRepository().isEmpty()){
			for(String product:productIndex){
				Sale totalSale = salesRepository.getTotalSaleForProduct(product);
				System.out.println("Total Sale Log for"+totalSale);
			}
		}
	}
	
	/*This Method will log total adjustments for each sale type
	 * 
	 * 
	 * */
	public void logAdjustments(){
		for(Adjustsment adjust: salesRepository.getAdjustments()){
			System.out.println(adjust);
		}
	}

	

	/**
	 * @param salesRepository the salesRepository to set
	 */
	public void setSalesRepository(SalesRepository salesRepository) {
		this.salesRepository = salesRepository;
	}
	
	/**
	 * @param productIndex the productIndex to set
	 */
	public void setProductIndex(HashSet<String> productIndex) {
		this.productIndex = productIndex;
	}


}
