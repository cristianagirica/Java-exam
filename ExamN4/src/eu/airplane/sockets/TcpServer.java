package eu.airplane.sockets;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eu.airplane.classes.AirplaneExpenses;

public class TcpServer extends Thread{

	private final static int PORT = 9998;
	private final static String FILEPATH = "//Users//macbook//Documents//Facultate";
	private Socket socket;
	
	public TcpServer(Socket socket)
	{
		this.socket = socket;
	}
	
	public void receive()
	{
		try(InputStream iStream = socket.getInputStream();
				DataInputStream dataInputStream = new DataInputStream(iStream);
				ObjectInputStream objectInputStream = new ObjectInputStream(iStream))
		{
		    String name = dataInputStream.readUTF();
		    try(FileOutputStream fileOutputStream = new FileOutputStream(FILEPATH + "//" + name);
		    		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
		    {
		    	int size = dataInputStream.readInt();
		    	for(int i = 0; i < size; i++)
		    	{
		    		objectOutputStream.writeObject(objectInputStream.readObject());
		    	}
		    	
		    	//deserialize
		    	TreeSet<AirplaneExpenses> airplanes = AirplaneExpenses.deserialize(FILEPATH + "//" + name);
		    	for(AirplaneExpenses a : airplanes)
		    	{
		    		System.out.println(a);
		    	}
		    }
		    catch (Exception e)
		    {
		    	e.printStackTrace();
		    }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		super.run();
		receive();
	}
	public static void main(String[] args) {
		
		try(ServerSocket server = new ServerSocket(PORT))
		{
			System.out.println("Server started on port 9998");
			ExecutorService threadPool = Executors.newCachedThreadPool();
			while(true)
			{
				Socket socket = server.accept();
				System.out.println("Connection received");
				TcpServer s = new TcpServer(socket);
				threadPool.execute(s);
			}
		}
        catch(Exception e)
		{
        	e.printStackTrace();
		}
	}

}
