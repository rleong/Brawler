package main;

import controller.Client;
import model.Player;
import model.Server;
import view.WindowPanel;

public class Main {

	public static void main (String args[]) {
		
		WindowPanel window = new WindowPanel("Brawler: By Russell Leong");
		Server server = new Server(window);
		Client client = new Client(window, server);
		
		client.setupWindow();
		
	}
	
}
