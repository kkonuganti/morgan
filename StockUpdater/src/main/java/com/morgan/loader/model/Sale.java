package com.morgan.loader.model;


/*
 * This Class defines a sale
 * @Fields : Product, Quantity, Value
 * */
public class Sale {
	
	private String product;
	private int quantity;
	private double value;
	private double total_sale_value;
	
	
	public Sale(String product, int quantity,double value){
		this.product=product;
		this.quantity=quantity;
		this.value=value;
		this.total_sale_value = quantity*value;
	}
	
	public Sale() {
		
	}
	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}
	/**
	 * @return the Sum of the sale for quantity
	 */
	public double getTotal_sale_value() {
		return total_sale_value;
	}
	/**
	 * @param total_sale_value the total sum to set
	 */
	public void setTotal_sale_value(double total_sale_value) {
		this.total_sale_value = total_sale_value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sale [Product=" + product + ", Quantity=" + quantity + ", Value=" + value + ", Total Sale Value="
				+ total_sale_value + "]";
	}
	

}
