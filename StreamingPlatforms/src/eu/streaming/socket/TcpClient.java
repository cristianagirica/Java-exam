package eu.streaming.socket;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TcpClient extends Thread{

	private static final int PORT = 9677;

	private String filePath;
	
	public TcpClient() {}
	
	public void setFilePath(String path)
	{
		this.filePath = path;
	}
	
	public void send()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the file path you wish to send: ");
		String pathString = scanner.nextLine();
		scanner.close();
		this.setFilePath(pathString);
		try(Socket socket = new Socket("localhost", PORT);
				OutputStream os = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				ObjectOutputStream oos = new ObjectOutputStream(os);
				FileInputStream fis = new FileInputStream(this.filePath);
				ObjectInputStream ois = new ObjectInputStream(fis))
		{
			System.out.println("Connection received");
			Path path = Paths.get(this.filePath);
			dos.writeUTF(path.getFileName().toString());
			
			Object o = ois.readObject();
			
			oos.writeObject(o);
			
			System.out.println("File sent");
			
		}
		catch (Exception e)
		{
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
