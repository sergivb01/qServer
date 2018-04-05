package me.sergivb01.qserver;

import me.sergivb01.qserver.queue.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cache{
	public static List<Server> servers = new ArrayList<>();
	public static HashMap<String, Integer> priorities = new HashMap<>();

	public static void addPlayer(String player, String serverStr, int priority){
		if(getPlayerServer(player) != null){
			//TODO: Send error message
			System.out.println("[Queue] Error while trying to add player " + player + " to queue "
					+ serverStr + "! It is already in a queue!");
			return;
		}

		Server server = getServerByName(serverStr);

		if(server != null){
			priorities.put(player, priority);
			server.getQPlayers().add(player);
			//TODO: Send success message
			System.out.print("[Queue] Player " + player + " has been successfully added to " + serverStr + "!");
			return;
		}

		//TODO: Send error message (server does not exist)
		System.out.println("[Queue] Error while trying to add player " + player + " to queue "
				+ serverStr + "! Server does not exist!");
	}

	public static void removePlayer(String player){
		Server server = getPlayerServer(player);
		if(server == null){
			//TODO: Send error message
			System.out.print("[Queue] Error while trying to remove player " + player + " from  a queue. It is not in  a queue!");
			return;
		}

		server.getQPlayers().remove(player);
		priorities.remove(player);
		System.out.println("[Queue] Player " + player + " has been removed from " + server.getName() + " queue!");
		//TODO: Send left message
	}

	public static Server getPlayerServer(String player){
		for(Server server : servers){
			if(server.getQPlayers().contains(player)){
				return server;
			}
		}
		return null;
	}

	public static Server getServerByName(String server){
		for(Server srv : servers){
			if(srv.getName().equalsIgnoreCase(server)){
				return srv;
			}
		}
		return null;
	}


	public static void run(){
		servers.stream().filter(srv -> srv.isRunning() && srv.isOnline() && !srv.isWhitelist() && (srv.getQPlayers().size() != 0)).forEach(server -> {
			String player = server.getQPlayers().remove();
			priorities.remove(player);
			System.out.println("[Send] Player " + player + " has been sent to " + server.getName() + "!");

			//TODO: Send player
		});
	}

	public static void createServer(String server){
		if(getServerByName(server) == null){
			servers.add(new Server(server));
			return;
		}
		System.out.println("Error while trying to create server " + server + "! It already exits.");
	}


}
