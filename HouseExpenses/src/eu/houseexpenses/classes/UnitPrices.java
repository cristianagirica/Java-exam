package eu.houseexpenses.classes;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class UnitPrices {

	private double waterIn; //unit price
	private double waterOut;
	private double recycledGarbage;
	private double wasteGarbage; 
	private double sharedElectricity;
	
	public UnitPrices() {}

	public UnitPrices(double waterIn, double waterOut, double recycledGarbage, double wasteGarbage,
			double sharedElectricity) {
		this.waterIn = waterIn;
		this.waterOut = waterOut;
		this.recycledGarbage = recycledGarbage;
		this.wasteGarbage = wasteGarbage;
		this.sharedElectricity = sharedElectricity;
	}

	public double getWaterIn() {
		return waterIn;
	}

	public void setWaterIn(double waterIn) {
		this.waterIn = waterIn;
	}

	public double getWaterOut() {
		return waterOut;
	}

	public void setWaterOut(double waterOut) {
		this.waterOut = waterOut;
	}

	public double getRecycledGarbage() {
		return recycledGarbage;
	}

	public void setRecycledGarbage(double recycledGarbage) {
		this.recycledGarbage = recycledGarbage;
	}

	public double getWasteGarbage() {
		return wasteGarbage;
	}

	public void setWasteGarbage(double wasteGarbage) {
		this.wasteGarbage = wasteGarbage;
	}

	public double getSharedElectricity() {
		return sharedElectricity;
	}

	public void setSharedElectricity(double sharedElectricity) {
		this.sharedElectricity = sharedElectricity;
	}
	
	public void serialize(String filePath)
	{
		try(FileOutputStream fileOutputStream = new FileOutputStream(filePath);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
				BufferedWriter bWriter = new BufferedWriter(outputStreamWriter))
		{
			bWriter.write(Double.toString(getWaterIn()));
			bWriter.write(",");
			bWriter.write(Double.toString(getWaterOut()));
			bWriter.write(",");
			bWriter.write(Double.toString(recycledGarbage));
			bWriter.write(",");
			bWriter.write(Double.toString(wasteGarbage));
			bWriter.write(",");
			bWriter.write(Double.toString(sharedElectricity));
		}
		catch (Exception e) {
			
		}
	}
}
