package com.morgan.loader.model;

public class Adjustsment {
	
	private String product;
	private double adjustment;
	private AdjustmentTypeConstant type;
	
	public Adjustsment(String product,double adjustment,AdjustmentTypeConstant type) {
		this.adjustment=adjustment;
		this.product=product;
		this.type=type;
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
	 * @return the adjustment
	 */
	public double getAdjustment() {
		return adjustment;
	}

	/**
	 * @param adjustment the adjustment to set
	 */
	public void setAdjustment(double adjustment) {
		this.adjustment = adjustment;
	}

	/**
	 * @return the type
	 */
	public AdjustmentTypeConstant getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(AdjustmentTypeConstant type) {
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Adjustsment [product=" + product + ", adjustment=" + adjustment + ", type=" + type + "]";
	}


}
