package eu.houseexpenses.classes;

public class House {

	private int id;
	private String location;
	private String admin;
	private double waterInConsumption;
	private double waterOutConsumption;
	private double recycledGarbageWeight;
	private double wasteGarbageWeight;
	private double sharedElectricity;
	private UnitPrices prices;
	
	public House()
	{
		
	}

	public House(int id, String location, String admin, double waterInConsumption, double waterOutConsumption,
			double recycledGarbageWeight, double wasteGarbageWeight, double sharedElectricity) {
		this.id = id;
		this.location = location;
		this.admin = admin;
		this.waterInConsumption = waterInConsumption;
		this.waterOutConsumption = waterOutConsumption;
		this.recycledGarbageWeight = recycledGarbageWeight;
		this.wasteGarbageWeight = wasteGarbageWeight;
		this.sharedElectricity = sharedElectricity;
	}

	
	public String getPrices() {
		String list = prices.getWaterIn() + "," + prices.getWaterOut() + "," + prices.getRecycledGarbage() + "," + prices.getWasteGarbage() + "," + prices.getSharedElectricity();
		return list;
	}

	public void setPrices(UnitPrices prices) {
		this.prices = prices;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public double getWaterInConsumption() {
		return waterInConsumption;
	}

	public void setWaterInConsumption(double waterInConsumption) {
		this.waterInConsumption = waterInConsumption;
	}

	public double getWaterOutConsumption() {
		return waterOutConsumption;
	}

	public void setWaterOutConsumption(double waterOutConsumption) {
		this.waterOutConsumption = waterOutConsumption;
	}

	public double getRecycledGarbageWeight() {
		return recycledGarbageWeight;
	}

	public void setRecycledGarbageWeight(double recycledGarbageWeight) {
		this.recycledGarbageWeight = recycledGarbageWeight;
	}

	public double getWasteGarbageWeight() {
		return wasteGarbageWeight;
	}

	public void setWasteGarbageWeight(double wasteGarbageWeight) {
		this.wasteGarbageWeight = wasteGarbageWeight;
	}

	public double getSharedElectricity() {
		return sharedElectricity;
	}

	public void setSharedElectricity(double sharedElectricity) {
		this.sharedElectricity = sharedElectricity;
	}

	public double getTotalMonthlyExpense()
	{
		double tot = 0;
		tot = this.waterInConsumption * this.prices.getWaterIn() + this.waterOutConsumption * this.prices.getWaterOut()
		+ this.recycledGarbageWeight * this.prices.getRecycledGarbage() + this.wasteGarbageWeight * this.prices.getWasteGarbage()
		+ this.sharedElectricity * this.prices.getSharedElectricity();
		
		return tot;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("");
		builder.append("Id: ");
		builder.append(this.id);
		builder.append(", Location: ");
		builder.append(this.location);
		builder.append(", Admin: ");
		builder.append(this.admin);
		builder.append(", Total expenses: ");
		builder.append(this.getTotalMonthlyExpense());
		builder.append("\n");
		return builder.toString();
	}
	
	
	
}
