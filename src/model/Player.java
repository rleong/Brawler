package model;

import java.util.UUID;

public class Player {

	private String name;
	private UUID ID = UUID.randomUUID();
	
	public Player(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public UUID getID() {
		return ID;
	}
	
}
