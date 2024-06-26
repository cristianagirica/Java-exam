package eu.airplane.classes;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.TreeSet;

public class AirplaneExpenses extends Airplane implements Serializable, Comparable<AirplaneExpenses>{

	private static final long serialVersionUID = 1L;
	private String date;
	private String insuranceCompany;
	private double insuranceCost;
	private double keroseneConsumption;
	private double distance;
	private double keroseneCost;
	private double crewCost;
	
	public AirplaneExpenses() {}
	
	public AirplaneExpenses(int id, String date, String insuranceCompany, double insuranceCost,
			double keroseneConsumption, double distance, double keroseneCost, double crewCost)
	{
		super(id);
		try 
		{
			this.setDate(date);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		this.insuranceCompany = insuranceCompany;
		this.insuranceCost = insuranceCost;
		this.keroseneConsumption = keroseneConsumption;
		this.distance = distance;
		this.keroseneCost = keroseneCost;
		this.crewCost = crewCost;
	}
	
	public void setDate(String date) throws Exception
	{
		if(date.length() == 10 &&
				date.charAt(2) == '/' &&
				date.charAt(5) == '/')
		{
			this.date = date;
		}
		else 
		{
			throw new Exception("Invalid date format");
		}
			
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public double getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public double getKeroseneConsumption() {
		return keroseneConsumption;
	}

	public void setKeroseneConsumption(double keroseneConsumption) {
		this.keroseneConsumption = keroseneConsumption;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getKeroseneCost() {
		return keroseneCost;
	}

	public void setKeroseneCost(double keroseneCost) {
		this.keroseneCost = keroseneCost;
	}

	public double getCrewCost() {
		return crewCost;
	}

	public void setCrewCost(double crewCost) {
		this.crewCost = crewCost;
	}

	public String getDate() {
		return date;
	}
	
	
	public static void serialize(List<AirplaneExpenses> list, String file)
	{
		try(FileOutputStream fileOutputStream = new FileOutputStream(file);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
		{
			for(AirplaneExpenses a : list)
			{
				objectOutputStream.writeObject(a);
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static TreeSet<AirplaneExpenses> deserialize(String file)
	{
		TreeSet<AirplaneExpenses> set = new TreeSet<AirplaneExpenses>();
		try(FileInputStream fileInputStream = new FileInputStream(file);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
		{
			while(true)
			{
				try 
				{
					AirplaneExpenses a = (AirplaneExpenses) objectInputStream.readObject();
					set.add(a);
					
				} catch (Exception e) {
					if(e instanceof EOFException)
					{
						break;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return set;
	}

	public double getTotalCost()
	{
		return this.insuranceCost + this.crewCost + this.keroseneCost;
	}
	
	@Override
	public int compareTo(AirplaneExpenses o) {
		if(this.getTotalCost() > o.getTotalCost())
		{
			return -1;
		}
		else if (this.getTotalCost() == o.getTotalCost())
		{
			return 0;
		}
		else
		return 1;
	}

	@Override
	public String toString() {
		return "Id: " + this.getId() + ", Cost: " + this.getTotalCost() + ", Insurance Company: " + this.insuranceCompany;
	}
	
	
}
