package eu.streaming.socket;

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

import eu.streaming.classes.StreamingPlatform;

public class TcpServer extends Thread{

	private static final String FILEPATH = "//Users//macbook//Documents//Aviatie";
	private static final int PORT = 9677;
	
	private Socket socket;
	public static TreeSet<StreamingPlatform> platforms = new TreeSet<StreamingPlatform>();
	
	public TcpServer(Socket socket)
	{
		this.socket = socket;
	}
	
	public void receive()
	{
		try(InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				ObjectInputStream ois = new ObjectInputStream(is))
		{
			String name = dis.readUTF();
			try(FileOutputStream fos = new FileOutputStream(FILEPATH + "//" + name);
					ObjectOutputStream oos = new ObjectOutputStream(fos))
			{
				oos.writeObject(ois.readObject());
				
				System.out.println("File received");
				
				//deserializing the file
				StreamingPlatform platform = StreamingPlatform.deserialize(FILEPATH + "//" + name);
				platforms.add(platform);
				for(StreamingPlatform p:platforms)
				{
					System.out.println(p);
					System.out.println("------------------------");
				}
			}
		}
		catch(Exception e)
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
			System.out.println("Server started on port " + PORT);
			ExecutorService threadPool = Executors.newCachedThreadPool();
			while(true)
			{
				Socket socket = server.accept();
				System.out.println("Connection accepted");
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
