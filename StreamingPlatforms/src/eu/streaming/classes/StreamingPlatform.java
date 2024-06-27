package eu.streaming.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class StreamingPlatform implements Serializable, Comparable<StreamingPlatform>{

	private static final long serialVersionUID = -5902375999259233672L;
	private int id = 0;
	private String name;
	private int noSubscribers;
	private String insuranceCompany;
	private ArrayList<Expenses> expenses; // expenses per day
	
	public StreamingPlatform() {}
	
	public StreamingPlatform(int id, String name, int noSubscribers, String insuranceCompany)
	{
		this.id = id;;
		this.name = name;
		this.noSubscribers = noSubscribers;
		this.insuranceCompany = insuranceCompany;
		expenses = new ArrayList<Expenses>();
	}
	public StreamingPlatform( String name, int noSubscribers, String insuranceCompany)
	{
		id++;
		this.name = name;
		this.noSubscribers = noSubscribers;
		this.insuranceCompany = insuranceCompany;
		expenses = new ArrayList<Expenses>();
	}

	public int getId()
	{
		return this.id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoSubscribers() {
		return noSubscribers;
	}

	public void setNoSubscribers(int noSubscribers) {
		this.noSubscribers = noSubscribers;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public ArrayList<Expenses> getExpenses() {
		return expenses;
	}
	
	public void addExpense(Expenses expense) throws Exception {
	    for(Expenses e : this.expenses) {
	        if(e.getDate().equals(expense.getDate())) {
	            throw new Exception("There already is a record of this day's expenses");
	        }
	        if(!(e.getPlatform().equals(this.getName()))) {
	            throw new Exception("This is not an expense record for this platform");
	        }
	    }
	    this.expenses.add(expense);  
	}
	
	public void serialize(String fileName)
	{
		try (FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos))
		{
			
			oos.writeObject(this);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static StreamingPlatform deserialize(String fileName)
	{
		StreamingPlatform platform = new StreamingPlatform();
		try(FileInputStream fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis))
		{
			platform = (StreamingPlatform)ois.readObject();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return platform;
	}
	
	public double getTotalCost()
	{
		double tot = 0;
		for(Expenses e:expenses)
		{
			tot += e.getTotalCost();
		}
		return tot;
	}

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder("");
		for(Expenses e:expenses)
		{
			builder.append(e.toString());
		}
		
		String result = "Platform: " + this.getName() + "\nSubscribers: " + noSubscribers + "\nExpenses: \n" + builder.toString();
		return result;
	}

	@Override
	public int compareTo(StreamingPlatform o) {
		if(this.getTotalCost() < o.getTotalCost())
		{
			return 1;
		}
		else if(this.getTotalCost() > o.getTotalCost())
		{
			return -1;
		}
		else 
		{
			return 0;
		}		
	}
	
	
}
