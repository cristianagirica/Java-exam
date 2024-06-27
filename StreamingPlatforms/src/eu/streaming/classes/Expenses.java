package eu.streaming.classes;

import java.io.Serializable;

public class Expenses implements Serializable{

	private static final long serialVersionUID = -5902375999259233672L;
	private String platform;
	private String date; // dd/mm/yyyy
	private double inRequests; //MB
	private double outResponses; // MB
	private double serversCost; //EUR
	private double insuranceCost; //EUR
	private double maintenanceCost; //EUR
	private double incomeFromSubscribers; //EUR
	
	public Expenses() {}

	public Expenses(String platform, String date, double inRequests, double outResponses, double serversCost, double insuranceCost,
			double maintenanceCost, double incomeFromSubscribers) throws Exception {
		this.platform = platform;
		this.setDate(date);
		this.inRequests = inRequests;
		this.outResponses = outResponses;
		this.serversCost = serversCost;
		this.insuranceCost = insuranceCost;
		this.maintenanceCost = maintenanceCost;
		this.incomeFromSubscribers = incomeFromSubscribers;
	}

	
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) throws Exception {
		if(date.length() != 10 ||
				date.charAt(2) != '/' ||
				date.charAt(5) != '/')
		{
			throw new Exception("Invalid date format");
		}
		this.date = date;
	}

	public double getInRequests() {
		return inRequests;
	}

	public void setInRequests(double inRequests) {
		this.inRequests = inRequests;
	}

	public double getOutResponses() {
		return outResponses;
	}

	public void setOutResponses(double outResponses) {
		this.outResponses = outResponses;
	}

	public double getServersCost() {
		return serversCost;
	}

	public void setServersCost(double serversCost) {
		this.serversCost = serversCost;
	}

	public double getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public double getMaintenanceCost() {
		return maintenanceCost;
	}

	public void setMaintenanceCost(double maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}

	public double getIncomeFromSubscribers() {
		return incomeFromSubscribers;
	}

	public void setIncomeFromSubscribers(double incomeFromSubscribers) {
		this.incomeFromSubscribers = incomeFromSubscribers;
	}
	
	public double getTotalCost()
	{
		return this.insuranceCost + this.serversCost + this.maintenanceCost;
	}

	@Override
	public String toString() {
		
		return "Date: " + this.getDate() + "\nTotal cost: " + this.getTotalCost() + "\nTotal Income: " + this.incomeFromSubscribers + "\n";
	}
	
	
	
}
