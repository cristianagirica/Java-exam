package eu.houseexpenses.classes;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static final String FILEPATH = "//Users//macbook//Documents//Facultate";
	public static final String NAME1 = "//Neighbourhood.txt";
	public static final String NAME2 = "//UnitPrices.txt";
	
	public static void main(String[] args) {
		
		UnitPrices u1 = new UnitPrices(4.7, 3.9, 1.2, 3.5, 0.98);
		
		House h1 = new House(22, "IF-Pantelimon", "eBlocAdmin", 11, 7, 20, 8, 25);
		h1.setPrices(u1);
		House h2 = new House(23, "IF-Pantelimon", "eBlocAdmin", 31, 12, 28, 14, 25);
		h2.setPrices(u1);
		ArrayList<House> list = new ArrayList<>();
		list.add(h1);
		list.add(h2);
		
		Neighbourhood n1 = new Neighbourhood(1, list);
		
		n1.serialize(FILEPATH + NAME1);
	
		u1.serialize(FILEPATH + NAME2);

		//Data base
		
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:database3.db");
			connection.setAutoCommit(false);
			
			createTable(connection);
			insert(connection, h1);
			insert(connection, h2);
			for(House h:select(connection))
			{
				System.out.println(h);
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createTable(Connection connection)
	{
		String drop = "DROP TABLE IF EXISTS HOUSES";
		String create = "CREATE TABLE HOUSES(id INT, location TEXT, admin TEXT, waterInConsumption REAL"
				+ ", waterOutConsumption REAL, recycledGarbage REAL, wasteGarbage REAL, sharedElectricity REAL, prices TEXT)";
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(drop);
			statement.executeUpdate(create);
			
			statement.close();
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void insert(Connection connection, House house)
	{
		String insert = "INSERT INTO HOUSES VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, house.getId());
			statement.setString(2, house.getLocation());
			statement.setString(3, house.getAdmin());
			statement.setDouble(4, house.getWaterInConsumption());
			statement.setDouble(5, house.getWaterOutConsumption());
			statement.setDouble(6, house.getRecycledGarbageWeight());
			statement.setDouble(7, house.getWasteGarbageWeight());
			statement.setDouble(8, house.getSharedElectricity());
			statement.setString(9, house.getPrices());
			
			statement.executeUpdate();
			statement.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<House> select(Connection connection)
	{
		ArrayList<House> houses = new ArrayList<House>();
		
		String select = "SELECT * FROM HOUSES";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery(select);
			while(rSet.next())
			{
				int id = rSet.getInt(1);
				String location = rSet.getString(2);
				String admin = rSet.getString(3);
				double wic = rSet.getDouble(4);
				double woc = rSet.getDouble(5);
				double rg = rSet.getDouble(6);
				double wg = rSet.getDouble(7);
				double se = rSet.getDouble(8);
				String prices = rSet.getString(9);
				
				String parts[] = prices.split(",");
				double wi = Double.parseDouble(parts[0]);
				double wo = Double.parseDouble(parts[1]);
				double r = Double.parseDouble(parts[2]);
				double w = Double.parseDouble(parts[3]);
				double e = Double.parseDouble(parts[4]);
				UnitPrices priceList = new UnitPrices(wi, wo, r, w, e);
				
				House house = new House(id, location, admin, wic, woc, rg, wg, se);
				house.setPrices(priceList);
				
				houses.add(house);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return houses;
	}

}
