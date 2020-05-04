package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutage;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
	List<PowerOutage> po= 	model.cercaMassimoPersone(new Nerc(3,""), 2, 100);
System.out.println("YEahhhhhhhhhhhhhhhhh");
	System.out.println(po);
	

	
	
	}

}
