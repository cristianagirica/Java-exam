package eu.airplane.classes;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

	private static final String FILEPATH = "//Users//macbook//Documents//Aviatie";
	private static final String FILENAME = "//Airplanes.bin";
	public static void main(String[] args) {

		AirplaneExpenses a1 = new AirplaneExpenses(1, "25/06/2024", "AirSafe", 200.0, 80.3, 4500, 5000, 4000);
		AirplaneExpenses a2 = new AirplaneExpenses(2, "25/06/2024", "AirSafe", 270.0, 65.9, 8900, 6000, 6000);
		AirplaneExpenses a3 = new AirplaneExpenses(3, "25/06/2024", "HighSkyInsurance", 450.8, 76.0, 6770, 6000, 3777);
		
		ArrayList<AirplaneExpenses> list = new ArrayList<AirplaneExpenses>();
		list.add(a1);
		list.add(a2);
		list.add(a3);
		
		AirplaneExpenses.serialize(list, FILEPATH + FILENAME);
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
			connection.setAutoCommit(false);
			createTable(connection);
			insertValues(connection, a1);
			insertValues(connection, a2);
			insertValues(connection, a3);
			
			ArrayList<AirplaneExpenses> airplanes = selectData(connection);
			for(AirplaneExpenses a : airplanes)
			{
				System.out.println(a);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public static void createTable(Connection connection)
	{
		String drop = "DROP TABLE IF EXISTS AIRPLANES";
		String create = "CREATE TABLE AIRPLANES(id INT PRIMARY KEY, " +
		"date TEXT, insuranceCompany TEXT, insuranceCost REAL, keroseneConsumption REAL, " +
				"distance REAL, keroseneCost REAL, crewCost REAL)";
		
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(drop);
			statement.executeUpdate(create);
			statement.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void insertValues(Connection connection, AirplaneExpenses airplane)
	{
		String insert = "INSERT INTO AIRPLANES VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStatement;
		try {
			
			preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setInt(1, airplane.getId());
			preparedStatement.setString(2, airplane.getDate());
			preparedStatement.setString(3, airplane.getInsuranceCompany());
			preparedStatement.setDouble(4, airplane.getInsuranceCost());
			preparedStatement.setDouble(5, airplane.getKeroseneConsumption());
			preparedStatement.setDouble(6, airplane.getDistance());
			preparedStatement.setDouble(7, airplane.getKeroseneCost());
			preparedStatement.setDouble(8, airplane.getCrewCost());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<AirplaneExpenses> selectData(Connection connection)
	{
		String select = "SELECT * FROM AIRPLANES";
		ArrayList<AirplaneExpenses> airplanes = new ArrayList<AirplaneExpenses>();
		
		Statement statement;
		try 
		{
			statement =connection.createStatement();
			ResultSet rs = statement.executeQuery(select);
			while(rs.next())
			{
				int id = rs.getInt(1);
				String date = rs.getString(2);
				String compI = rs.getString(3);
			    double costI = rs.getDouble(4);
			    double keros = rs.getDouble(5);
			    double dist = rs.getDouble(6);
			    double kerosC = rs.getDouble(7);
			    double crew = rs.getDouble(8);
			    AirplaneExpenses airplaneExpense = new AirplaneExpenses(id, date, compI, costI, keros, dist, kerosC, crew);
			    airplanes.add(airplaneExpense);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return airplanes;
	}
}
