package com.morgan.loader.implementation;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.morgan.loader.model.AdjustmentTypeConstant;
import com.morgan.loader.model.Sale;
import com.morgan.loader.repository.SalesRepository;

/*
 * 
 * @author:kk
 * This class is used to validate a message
 * 
 * */
public class Processor {

	private static final String PRICE_REGEX = "([0-9]+[p])";

	private static final String regex_space = "\\s";

	private static final String regex_digit = "\\d+";

	private Pattern pattern;

	private SalesRepository repo;
	
	private HashSet<String> productIndex = new HashSet<>(0);



	/*
	 * This method is used to validate a message and persist based on the
	 * message type
	 * 
	 * @param: Message String, Accepted formats : apple at 10p, 20 sales of
	 * apples at 10p each, Add 20p apples
	 * 
	 */
	public void process(String message) {
		if (message == null || message.equals("")) {
			System.err.println("No message found !! \n");
			return;
		}

		String[] parts = message.split(regex_space);
		pattern = Pattern.compile(PRICE_REGEX);
		boolean isProcessed = false;
		// for message type 1 and 3
		// validate and persist type 1 and type 3
		if (parts.length == 3) {
			// type 1
			Matcher matcher = pattern.matcher(parts[2]);
			// type 3
			Matcher matcher2 = pattern.matcher(parts[1]);
			if (matcher.matches() && parts[1].equalsIgnoreCase("at")) {
				processMessageType1(parts);
				isProcessed=true;
			} else if (matcher2.matches()) {
				processMessageType3(parts);
				isProcessed=true;
			}
			// validate and persist type 2
		} else if (parts.length == 7) {

			Matcher matcher2 = pattern.matcher(parts[5]);
			if (matcher2.matches()) {
				processMessageType2(parts);
				isProcessed=true;
			}

		}
		if(!isProcessed){
			System.err.println("NOT VALID !! \n");
		}

		
	}

	/**
	 * @param parts
	 */
	private void processMessageType1(String[] parts) {
		// valid type 1
		double value = Double.parseDouble(parts[2].substring(0, parts[2].length() - 1)) / 100;
		// persist new single sale
		Sale sale = new Sale(parts[0], 1, value);
		productIndex.add(parts[0]);
		repo.addSale(sale);
	}

	/**
	 * @param parts
	 */
	private void processMessageType3(String[] parts) {
		double value = Double.parseDouble(parts[1].substring(0, parts[1].length() - 1)) / 100;

		for (AdjustmentTypeConstant s : AdjustmentTypeConstant.values()) {
			if (s.getType().equalsIgnoreCase(parts[0])) {

				// make adjustment
				repo.adjustSale(parts[2], value, s);
			}
		}
	}

	/**
	 * @param parts
	 */
	private void processMessageType2(String[] parts) {
		double value = Double.parseDouble(parts[5].substring(0, parts[5].length() - 1)) / 100;
		pattern = Pattern.compile(regex_digit);
		Matcher matcher3 = pattern.matcher(parts[0]);
		if (matcher3.matches() && parts[1].equalsIgnoreCase("sales")) {
			int numberOfSales = Integer.parseInt(parts[0]);
			Sale sale = new Sale(parts[3], numberOfSales, value);
			repo.addSale(sale);
			productIndex.add(parts[3]);
		}
	}

	/**
	 * @param repo
	 *            the repo to set
	 */
	public void setRepo(SalesRepository repo) {
		this.repo = repo;
	}
	
	/**
	 * @return the productIndex
	 */
	public HashSet<String> getProductIndex() {
		return productIndex;
	}

}
