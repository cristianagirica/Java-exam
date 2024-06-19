package com.company.sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TcpClient {

	public static void main(String[] args) {
	
		String path1 = "//Users//macbook//Documents//Car1.expenses";
		String path2 = "//Users//macbook//Documents//Car2.expenses";
		try(Socket socket = new Socket("localhost", 8888))
		{
			OutputStream outputStream = socket.getOutputStream();
			sendFile(outputStream, path1);
			System.out.println("File 1 sent");
			sendFile(outputStream, path2);
			System.out.println("File 2 sent");
		}
        catch (Exception e)
		{
        	e.printStackTrace();
		}
	}
	
	public static void sendFile(OutputStream outputStream, String path) throws IOException 
	{
		
		DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		Path filePath = Paths.get(path);
		
		dataOutputStream.writeUTF(filePath.getFileName().toString());
		dataOutputStream.writeInt((int)Files.size(filePath));
		
		byte[] fileBytes = Files.readAllBytes(filePath);
		dataOutputStream.write(fileBytes);
	}

}
