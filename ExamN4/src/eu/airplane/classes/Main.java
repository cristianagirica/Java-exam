package eu.airplane.classes;

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
	}

}
