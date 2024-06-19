package com.company.enums;

public enum InsuranceCompanies {

	AutoGuard(60.0, "Auto Guard Insurance"),
	DriveSafe(55.0, "Drive Safe Insurance"),
	RoadRunner(60.0, "Road Runner Insurance"),
	SureDrive(45.0, "Sure Drive Insurance"),
	CarProtect(65.0, "Car Protect Insurance");
	
	private double monthlyPrice;
	private String name;
	
	private InsuranceCompanies(double monthlyPrice, String name) {
        this.monthlyPrice = monthlyPrice;
        this.name = name;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }
    
    public String getName()
    {
    	return name;
    }
}


