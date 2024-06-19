package com.company.sockets;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TcpServer extends Thread {

	private static final String FILE_DIRECTORY = "//Users//macbook//Documents//Aviatie";
	private Socket socket;

	public TcpServer(Socket socket) {
		this.socket = socket;
	}
	
	public void receive() {
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            while (true) {
                try {
                    String fileName = dataInputStream.readUTF();
                    int fileSize = dataInputStream.readInt();

                    Path directory = Paths.get(FILE_DIRECTORY);
                    if (!Files.exists(directory)) {
                        Files.createDirectories(directory);
                    }

                    Path filePath = Paths.get(FILE_DIRECTORY + "//" + fileName);

                    try (FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile())) {
                        byte[] buffer = new byte[4096];
                        int totalBytesRead = 0;
                        while (totalBytesRead < fileSize) {
                            int bytesRead = dataInputStream.read(buffer, 0, Math.min(buffer.length, fileSize - totalBytesRead));
                            if (bytesRead == -1) break;
                            fileOutputStream.write(buffer, 0, bytesRead);
                            totalBytesRead += bytesRead;
                        }
                    }

                    System.out.println("File " + fileName + " received and saved");
                } catch (IOException e) {
                  
                    break;
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

		try (ServerSocket server = new ServerSocket(8888)) {
			System.out.println("Server started on port 8888");

			while (true) {
				Socket socket = server.accept();
				TcpServer serverInstance = new TcpServer(socket);
				serverInstance.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
