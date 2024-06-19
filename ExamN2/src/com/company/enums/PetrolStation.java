package com.company.enums;

public enum PetrolStation {

	PetroGas(6.2),
	EcoFuel(6.15),
	OMV(6.30);
	
	private double gasPrice;
	
	private PetrolStation(double gasPrice) {
		this.gasPrice = gasPrice;
	}
	
	public double getPrice()
	{
		return gasPrice;
	}
}
