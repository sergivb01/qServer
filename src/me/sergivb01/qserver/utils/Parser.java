package me.sergivb01.qserver.utils;

import me.sergivb01.qserver.Cache;
import org.bson.Document;

public class Parser{

	public static void parse(String str){
		Document document = Document.parse(str);
		String server = (String) document.getOrDefault("server", "none");
		String player = (String) document.getOrDefault("player", "none");
		String action = document.getString("action");

		switch(action.toLowerCase()){
			case "add":{
				int prio = document.getInteger("priority");
				Cache.addPlayer(player, server, prio);
				break;
			}
			case "remove":{
				Cache.removePlayer(player);
				break;
			}
			case "data":{
				if(Cache.getServerByName(server) != null){
					Cache.getServerByName(server).updateData(Document.parse(document.getString("data")));
				}
				break;
			}

		}


	}


}
