package eu.streaming.main;

import eu.streaming.classes.StreamingPlatform;
import eu.streaming.socket.TcpServer;

public class Main2 {

	public static void main(String[] args) {
		

		for(StreamingPlatform platform : TcpServer.platforms)
		{
			System.out.println(platform);
		}
		

	}

}
