package com.company.expenses;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


import com.company.enums.*;

public class CarExpenses extends Company implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idCar = 1000;
	private String registrationPlate;
	private InsuranceCompanies insuranceCompany;
	private int distance; //distance made per month
	private double gasConsumption; //L/100km
	private PetrolStation petrolStation;
	private double gasCost; // per liter
	private double insuranceCost;
	private double parkingCost;
	private int noParks; //times of parking per month
	
	public CarExpenses(String companyName, String registrationPlate, 
			InsuranceCompanies insuranceCompany, int distance, 
			double gasConsumption, PetrolStation petrolStation, int noParks)
	{
		super(companyName);
		this.idCar++;
		this.registrationPlate = registrationPlate;
		this.insuranceCompany = insuranceCompany;
		this.distance = distance;
		this.gasConsumption = gasConsumption;
		this.petrolStation = petrolStation;
		this.gasCost = this.petrolStation.getPrice();
		this.insuranceCost = this.insuranceCompany.getMonthlyPrice();
		this.parkingCost = 5;
		this.noParks = noParks;
		
	}
	
	public int getIdCar() {
		return idCar;
	}


	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}


	public String getRegistrationPlate() {
		return registrationPlate;
	}


	public void setRegistrationPlate(String registrationPlate) {
		this.registrationPlate = registrationPlate;
	}


	public InsuranceCompanies getInsuranceCompany() {
		return insuranceCompany;
	}


	public void setInsuranceCompany(InsuranceCompanies insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public double getGasConsumption() {
		return gasConsumption;
	}


	public void setGasConsumption(double gasConsumption) {
		this.gasConsumption = gasConsumption;
	}


	public PetrolStation getPetrolStation() {
		return petrolStation;
	}


	public void setPetrolStation(PetrolStation petrolStation) {
		this.petrolStation = petrolStation;
	}


	public double getGasCost() {
		return gasCost;
	}


	public void setGasCost(double gasCost) {
		this.gasCost = gasCost;
	}


	public double getInsuranceCost() {
		return insuranceCost;
	}


	public void setInsuranceCost(double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}


	public double getParkingCost() {
		return parkingCost;
	}


	public void setParkingCost(double parkingCost) {
		this.parkingCost = parkingCost;
	}


	public int getNoParks() {
		return noParks;
	}


	public void setNoParks(int noParks) {
		this.noParks = noParks;
	}


	@Override
	public double getMonthlyGasCost() {	
		
		return distance / gasConsumption * gasCost;
	}
	
	@Override
	public double getMonthlyInsuranceCost() {
		
		return insuranceCost;
	}
	
	@Override
	public double getMonthlyParkingCost() {
		
		return parkingCost * noParks;
	}
	
	public void serialize(String filePath)
	{
		try(FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos))
		{
			oos.writeObject(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static CarExpenses deserialize(String filePath)
	{
		CarExpenses car = null;
		try(FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream ois = new ObjectInputStream(fis))
		{
			car = (CarExpenses)ois.readObject();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return car;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder("");
		builder.append(this.getCompanyName());
		builder.append(" ");
		builder.append(this.getGasCost());
		return builder.toString();
	}
}
