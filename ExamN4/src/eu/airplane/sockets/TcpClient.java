package eu.airplane.sockets;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TcpClient extends Thread{

	private final static int PORT = 9998;
	private final static String FILEPATH = "//Users//macbook//Documents//Aviatie//Airplanes.bin";
	
	public TcpClient()
	{
	}
	
	public void send()
	{		
		try(Socket socket = new Socket("localhost", PORT);
				OutputStream outputStream = socket.getOutputStream();
				DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
				ObjectOutputStream oStream = new ObjectOutputStream(outputStream);
				FileInputStream foStream = new FileInputStream(FILEPATH);
				ObjectInputStream objectInputStream = new ObjectInputStream(foStream))
		{
			Path path = Paths.get(FILEPATH);
			dataOutputStream.writeUTF(path.getFileName().toString());
			List<Object> list = new ArrayList<>();
			while (true) {
                try {
                    list.add(objectInputStream.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
			dataOutputStream.writeInt(list.size());
			for(Object o : list)
			{
				oStream.writeObject(o);
			}

			System.out.println("File sent");
		}
		catch(Exception e)
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
		
		try
		{
				TcpClient c = new TcpClient();
				c.start();		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}


	}

}
