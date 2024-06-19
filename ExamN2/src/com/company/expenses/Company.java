package com.company.expenses;

import java.io.Serializable;



public abstract class Company implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String companyName;
	private int noCars = 0;
	
	public Company(String name)
	{
		this.companyName = name;
		this.noCars++;
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getNoCars() {
		return noCars;
	}
	
	public abstract double getMonthlyGasCost();
	
	public abstract double getMonthlyInsuranceCost();
	
	public abstract double getMonthlyParkingCost();
}
