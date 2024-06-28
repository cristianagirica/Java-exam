package eu.houseexpenses.sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


import eu.houseexpenses.classes.House;
import eu.houseexpenses.classes.UnitPrices;

public class TcpServer extends Thread{

	private static String DELIMITER = "---";
	private static final int PORT = 8799;
	private static final String PATH = "//Users//macbook//Documents//Aviatie";
	private Socket socket;
	public static List<House> houses = new ArrayList<House>();
	UnitPrices prices = null;
	
	public TcpServer(Socket socket)
	{
		this.socket = socket;
	}
	
	public void receive()
	{
		try(InputStream istream = socket.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(istream);
				BufferedReader reader = new BufferedReader(inputStreamReader);)
		{
			//file 1
			String name = reader.readLine();
			
			try(FileOutputStream fileOutputStream = new FileOutputStream(PATH + "//" + name);
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
					BufferedWriter writer = new BufferedWriter(outputStreamWriter))
			{
				
				String line;
				while(!(line = reader.readLine()).equals(DELIMITER))
				{
					writer.write(line);
					writer.write("\n");
					String parts[] = line.split(",");
					int id = Integer.parseInt(parts[0]);
					String location = parts[1];
					String admin = parts[2];
					double wic = Double.parseDouble(parts[3]);
					double woc = Double.parseDouble(parts[4]);
					double rw = Double.parseDouble(parts[5]);
					double gw = Double.parseDouble(parts[6]);
					double se = Double.parseDouble(parts[7]);
					
					House house = new House(id, location, admin, wic, woc, rw, gw, se);
					houses.add(house);
				}
				System.out.println("File " + name + " received");
				
				for(House h:houses)
				{
					h.setPrices(prices);
				}
	
			}
			
			//file 2
			String name2 = reader.readLine();
			
			try(FileOutputStream fileOutputStream2 = new FileOutputStream(PATH + "//" + name2);
					OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(fileOutputStream2);
					BufferedWriter writer2 = new BufferedWriter(outputStreamWriter2))
			{
				String line2;
				while((line2 = reader.readLine()) != null)
				{
					writer2.write(line2);
					writer2.write("\n");
					
					String parts2[] = line2.split(",");
					double wi = Double.parseDouble(parts2[0]);
					double wo = Double.parseDouble(parts2[1]);
					double rg = Double.parseDouble(parts2[2]);
					double wg = Double.parseDouble(parts2[3]);
					double se = Double.parseDouble(parts2[4]);
					
					prices = new UnitPrices(wi, wo, rg, wg, se);
					
				}
				
				System.out.println("File " + name2 + " received");
				
				bubbleSort(houses, prices);
				
				for(House h: houses)
				{
					System.out.println(h.toString());
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		super.run();
		receive();
	}
	
	public static void main(String[] args) {
		
		try(ServerSocket serverSocket = new ServerSocket(PORT))
		{
			System.out.println("Server started on port " + PORT);
			while(true)
			{
				Socket socket = serverSocket.accept();
				System.out.println("Connection received");
				TcpServer server = new TcpServer(socket);
				server.start();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void bubbleSort(List<House> list, UnitPrices prices)
	{
		int n = list.size();
		
		House tmp = null;
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 1; j < n-i; j++)
			{
				if(list.get(j-1).getTotalMonthlyExpense() < list.get(j).getTotalMonthlyExpense())
				{
					tmp = list.get(j-1);
					list.set(j-1, list.get(j));
					list.set(j, tmp);
				}
			}
		}
	}

}
