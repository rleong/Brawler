package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
	
	private ServerSocket serverSocket;
	private int port;
	
	public ServerThread(int port) {
		this.port = port;
	}

	public void run(){
		
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server started on port: " + port);
			
			while (!this.isInterrupted()) {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					//System.out.println("Interrupted! " +  e);
					break;
				}				
				Socket clientSocket = serverSocket.accept();
				new ServerSocketThread(clientSocket).start();
			}
			
			serverSocket.close();
			System.out.println("Server closed on port: " + port);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		
	}
	
}
