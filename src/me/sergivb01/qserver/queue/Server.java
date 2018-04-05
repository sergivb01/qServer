package me.sergivb01.qserver.queue;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.util.PriorityQueue;

@Getter public class Server{
	private PriorityQueue<String> qPlayers = new PriorityQueue<>(new Comparator());
	private String name;
	private int players;
	private int max;
	@Setter private boolean running;
	private boolean online;
	private boolean whitelist;

	public Server(String name){
		this.name = name;
		this.players = -1;
		this.max = 0;
		this.running = true;
		this.online = true; //TODO: Change this to false by default
		this.whitelist = false;
	}

	public void updateData(Document document){
		//TODO: Update details
	}


}
