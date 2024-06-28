package eu.houseexpenses.sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient extends Thread{

	private static String DELIMITER = "---";
	private static final int PORT = 8799;
	private static final String FILEPATH = "//Users//macbook//Documents//Facultate//";
	public static final String NAME1 = "Neighbourhood.txt";
	public static final String NAME2 = "UnitPrices.txt";
	
	public TcpClient()
	{
		
	}
	
	public void send()
	{
		try(Socket socket = new Socket("localhost", PORT);
				OutputStream oStream = socket.getOutputStream();
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(oStream);
				BufferedWriter writer = new BufferedWriter(outputStreamWriter))
		{
			//file1
			try(FileInputStream fileInputStream = new FileInputStream(FILEPATH + NAME1);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
				BufferedReader reader = new BufferedReader(inputStreamReader);)
			{
				writer.write(NAME1);
				writer.write("\n");
				
				String line;
				while((line = reader.readLine()) != null)
				{
					writer.write(line);
					writer.write("\n");
				}
				writer.write(DELIMITER + "\n");
			}
			
			
			
			//file 2
			try(FileInputStream fileInputStream2 = new FileInputStream(FILEPATH + NAME2);
					InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream2);
					BufferedReader reader2 = new BufferedReader(inputStreamReader2))
			{
				writer.write(NAME2);
				writer.write("\n");
				String line;
				while((line = reader2.readLine()) != null)
				{
					writer.write(line);
					writer.write("\n");
				}
			}
					
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		super.run();
		send();
	}
	
	public static void main(String[] args) {
		
		TcpClient client = new TcpClient();
		client.start();

	}

}
