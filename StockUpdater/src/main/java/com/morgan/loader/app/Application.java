package com.morgan.loader.app;

import java.io.BufferedInputStream;
import java.util.Scanner;

import com.morgan.loader.implementation.Processor;
import com.morgan.loader.implementation.SaleLogger;
import com.morgan.loader.repository.SalesRepository;


/**
* The Stock Loader program loads price details
* 
*
* @author  KK
* @version 1.0
* @since    
*/
public class Application {

	//@param : String Arguments
	//@description : This is the main method from which the program is intialised
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Stock Loader APP");
		System.out.println("---------------------------------------------");
		System.out.println("To Terminate the program at any time please press Ctrl + Z");
		System.out.println("NOTE : Any data loaded will be lost when the app is closed. \n");
		System.out.println("Please Enter the Messages \n");
        
        Processor processor = new Processor();
        SalesRepository repo = new SalesRepository();
        SaleLogger logger = new SaleLogger();
        
        processor.setRepo(repo);
        logger.setSalesRepository(repo);
        
        int count =0;
        int mainCount=0;
        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        while (stdin.hasNext()) {
        	if(mainCount==4){
        		System.out.println("Not Accepting any more sales \n");
        		logger.logAdjustments();
        		System.out.println("\n Terminating the system \n");
        		return;
        	}else{
        		count++;
            	mainCount++;
                processor.process(stdin.nextLine());
                
                
                if(count==2){
                	count=0;
                	logger.setProductIndex(processor.getProductIndex());
                	logger.logSales();
                }
                
        		
        	}
        	
        }

	}

}
