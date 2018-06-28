package main;

import controller.Client;
import model.Player;
import view.WindowPanel;

public class Main {

	public static void main (String args[]) {
		
		Client client = new Client();
		Player hostPlayer = new Player();
		WindowPanel window = new WindowPanel("Brawler: By Russell Leong", hostPlayer);
		window.setUp();
	}
	
}
