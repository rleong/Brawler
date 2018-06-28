package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ServerSocketThread extends Thread{
	
	private Socket socket;
	private boolean connected = true;
	
	public ServerSocketThread(Socket socket){
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			socket.setSoTimeout(30 * 1000);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while (bufferedReader.read() != -1) {
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
