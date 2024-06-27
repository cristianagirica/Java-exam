package eu.streaming.main;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eu.streaming.classes.Expenses;
import eu.streaming.classes.StreamingPlatform;
import eu.streaming.socket.TcpServer;

public class Main {

	public static void main(String[] args) {
		StreamingPlatform platform1 = new StreamingPlatform("Netflix", 28943786, "SafeServer");
		try {
			Expenses e1 = new Expenses("Netflix", "25/06/2024", 4700.4, 4579.5, 20800, 40895, 53000, 309055);
			Expenses e2 = new Expenses("Netflix", "26/06/2024", 4900.3, 43907.8, 30788, 73780, 67330, 870564);
			Expenses e3 = new Expenses("Netflix", "27/06/2024", 5739.9, 5689.4, 40337, 68637, 69004, 759003);
			
			platform1.addExpense(e1);
			platform1.addExpense(e2);
			platform1.addExpense(e3);
			
			platform1.serialize("//Users//macbook//Documents//Facultate//" + platform1.getName() + ".bin");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StreamingPlatform platform2 = new StreamingPlatform("Amazon Prime", 5940635, "SafeServer");
		
		try {
			Expenses e4 = new Expenses("Amazon Prime", "25/06/2024", 6473.7, 7364.7, 65436, 83764, 34662, 204773);
			Expenses e5 = new Expenses("Amazon Prime", "26/06/2024", 6354.6, 2534.5, 35473, 43546, 25634, 352636);
			Expenses e6 = new Expenses("Amazon Prime", "27/06/2024", 36443.6, 5364.7, 34743, 35462, 86754, 293734);
			
			platform2.addExpense(e4);
			platform2.addExpense(e5);
			platform2.addExpense(e6);
			
			platform2.serialize("//Users//macbook//Documents//Facultate//" + platform2.getName() + ".bin");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Data base
		
		Connection connection = null;
		
		
			try {
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:database2.db");
				connection.setAutoCommit(false);
				
				create(connection);
				insertValues(connection, platform1);
				insertValues(connection, platform2);
				ArrayList<StreamingPlatform> p2 = select(connection);
				for(StreamingPlatform s:p2)
				{
					System.out.println(s);
				}
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	public static void create(Connection connection)
	{
		String drop = "DROP TABLE IF EXISTS PLATFORMS";
		String create = "CREATE TABLE PLATFORMS(id INT, name TEXT, noSubscribers INT, "
				+ "insuranceCompany TEXT)";
		
		Statement statement;
		try 
		{
			statement = connection.createStatement();
			statement.executeUpdate(drop);
			statement.executeUpdate(create);
			statement.close();
			connection.commit();
					
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void insertValues(Connection connection, StreamingPlatform platform)
	{
		String insert = "INSERT INTO PLATFORMS VALUES(?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, platform.getId());
			statement.setString(2, platform.getName());
			statement.setInt(3, platform.getNoSubscribers());
			statement.setString(4, platform.getInsuranceCompany());
			
			statement.executeUpdate();
			statement.close();
			
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<StreamingPlatform> select(Connection connection)
	{
		ArrayList<StreamingPlatform> platforms = new ArrayList<StreamingPlatform>();
		
		String select = "SELECT * FROM PLATFORMS";
		
		Statement statement;
		try 
		{
			statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery(select);
			
			while(rSet.next())
			{
				int id = rSet.getInt(1);
				String name = rSet.getString(2);
				int subs = rSet.getInt(3);
				String ins = rSet.getString(4);
				
				StreamingPlatform p = new StreamingPlatform(id, name, subs, ins);
				platforms.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return platforms;
	}

}
