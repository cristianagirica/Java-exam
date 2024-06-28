package eu.houseexpenses.classes;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Neighbourhood {

	private int id;
	private ArrayList<House> houses;
	
	public Neighbourhood() {}

	public Neighbourhood(int id, ArrayList<House> houses) {
		super();
		this.id = id;
		this.houses = houses;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<House> getHouses() {
		return houses;
	}

	public void setHouses(ArrayList<House> houses) {
		this.houses = houses;
	}

	public void serialize(String filePath)
	{
		try(FileOutputStream fileOutputStream = new FileOutputStream(filePath);
				OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
				BufferedWriter bWriter = new BufferedWriter(osw))
		{
			for(House h:houses)
			{
				bWriter.write(Integer.toString(h.getId()));
				bWriter.write(",");
				bWriter.write(h.getLocation());
				bWriter.write(",");
				bWriter.write(h.getAdmin());
				bWriter.write(",");
				bWriter.write(Double.toString(h.getWaterInConsumption()));
				bWriter.write(",");
				bWriter.write(Double.toString(h.getWaterOutConsumption()));
				bWriter.write(",");
				bWriter.write(Double.toString(h.getRecycledGarbageWeight()));
				bWriter.write(",");
				bWriter.write(Double.toString(h.getWasteGarbageWeight()));
				bWriter.write(",");
				bWriter.write(Double.toString(h.getSharedElectricity()));
				bWriter.write("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
