package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import view.WindowPanel;

public class Server {
	
	//Initial Values
	private String[] exampleNames = {"Cool Mom227","Purepker895","Elfinlocks","1337sp34kr",
			"Qutiedoll","PKMaster0036","Grindxplox","Ironmanbtw","Cow1337killr"};
	private Random rand = new Random();

	//Database
	private Player hostPlayer;
	private ArrayList<Player> otherPlayers;
	private int port;
	private WindowPanel window;
	
	//Server
	private ServerThread server;
	
	public Server(WindowPanel window) {
		this.window = window;
		hostPlayer = new Player(exampleNames[rand.nextInt(exampleNames.length)]);
		port = rand.nextInt(9999);
		//server = new ServerThread(port);
	}
	
	public void startServer() {
		server = new ServerThread(port);
		server.start();
	}
	
	public void stopServer() {
		if(server!=null) {
			server.interrupt();
		}
	}
	
	public void setPlayerName(String s) {
		hostPlayer.setName(s);
	}
	
	public String getPlayerName() {
		return hostPlayer.getName();
	}
	
	public UUID getPlayerID() {
		return hostPlayer.getID();
	}
	
	public void addPlayer(Player e) {
		otherPlayers.add(e);
	}
	
	public void removePlayer(int i) {
		otherPlayers.remove(i);
	}
	
	public int getNumPlayers() {
		return otherPlayers.size();
	}

}
