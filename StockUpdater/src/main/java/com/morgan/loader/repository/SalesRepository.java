package com.morgan.loader.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.morgan.loader.model.AdjustmentTypeConstant;
import com.morgan.loader.model.Adjustsment;
import com.morgan.loader.model.Sale;

/*
 * Class to load different sales
 * 
 * */
public class SalesRepository {

	private List<Sale> salesRepository = new ArrayList<>(0);
	
	private ArrayList<Adjustsment> adjustments = new ArrayList<>(0);



	/*
	 * This method will add a sale to the Repository
	 * 
	 * @Param Sale
	 * 
	 */
	public void addSale(Sale sale) {
		Assert.assertNotNull("Sale value is null, hence not adding", sale);
		this.salesRepository.add(sale);
		System.out.println("New sale added:"+sale);
	}

	/*
	 * This method will get all sales for a particular product
	 * 
	 * @Param Product name
	 * 
	 */
	public ArrayList<Sale> getSalesForProduct(String product) {
		ArrayList<Sale> sales = new ArrayList<Sale>(0);
		Assert.assertNotNull("Product Name is null ", product);
		for (Sale sale : this.salesRepository) {
			if (sale.getProduct().equalsIgnoreCase(product)) {
				sales.add(sale);
			}
		}
		if (sales.isEmpty()) {
			System.out.println("No sales found for product :" + product);
		}
		return sales;
	}
	
	/*
	 * This method will get total sale for a particular product
	 * 
	 * @Param Product name
	 * 
	 */
	public Sale getTotalSaleForProduct(String product) {
		int quant=0;
		double totalSales =0.0;
		Assert.assertNotNull("Product Name is null ", product);
		for (Sale sale : this.salesRepository) {
			if (sale.getProduct().equalsIgnoreCase(product)) {
				quant+= sale.getQuantity();
				totalSales+= sale.getTotal_sale_value();
			}
		}
		Sale totalSale = new Sale();
		totalSale.setProduct(product);
		totalSale.setQuantity(quant);
		totalSale.setTotal_sale_value(totalSales);
		
		return totalSale;
	}

	/*
	 * This method will update a products sale by a particulr value
	 * 
	 * @Param Product name
	 * 
	 * @Param Adjustment value
	 * 
	 * @Param Type of Adjustment
	 */
	public void adjustSale(String product, double adjustment, AdjustmentTypeConstant type) {
		for (Sale sale : this.salesRepository) {
			if (sale.getProduct().equalsIgnoreCase(product)) {
				double value = sale.getValue();
				double totalValue = sale.getTotal_sale_value();
				switch (type) {
				case ADD:
					value = value + adjustment;
					totalValue = sale.getQuantity() * value;
					break;
				case SUBTRACT:
					value = (new BigDecimal(value).subtract(new BigDecimal(adjustment))).setScale(2,RoundingMode.HALF_UP).doubleValue() ;
					totalValue = sale.getQuantity() * value;
					break;
				case MULTIPLY:
					value = value * adjustment;
					totalValue = sale.getQuantity() * value;
					break;
				default:
					break;

				}
				sale.setTotal_sale_value(totalValue);
				sale.setValue(value);
				System.out.println("Sale(s) Adjusted !!");
				Adjustsment adjust = new Adjustsment(product, adjustment, type);
				this.adjustments.add(adjust);
			}
		}
	}
	
	/**
	 * @return the adjustments
	 */
	public ArrayList<Adjustsment> getAdjustments() {
		return adjustments;
	}
	
	/**
	 * @param adjustments the adjustments to set
	 */
	public void setAdjustments(ArrayList<Adjustsment> adjustments) {
		this.adjustments = adjustments;
	}

	/**
	 * @return the salesRepository
	 */
	public List<Sale> getSalesRepository() {
		return salesRepository;
	}

	/**
	 * @param salesRepository
	 *            the salesRepository to set
	 */
	public void setSalesRepository(List<Sale> salesRepository) {
		this.salesRepository = salesRepository;
	}

}
