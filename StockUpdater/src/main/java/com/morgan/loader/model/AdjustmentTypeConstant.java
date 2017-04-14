/**
 * 
 */
package com.morgan.loader.model;

/**
 * @author kartheek.konuganti
 *
 */
public enum AdjustmentTypeConstant {
	
	ADD("add"),
	SUBTRACT("subtract"),
	MULTIPLY("multiply");
	
	private String type;
	
	AdjustmentTypeConstant(String type) {
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
}
