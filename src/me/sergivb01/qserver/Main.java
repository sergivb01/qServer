package me.sergivb01.qserver;

import me.sergivb01.qserver.queue.Server;
import me.sergivb01.qserver.redis.RedisDatabase;
import me.sergivb01.qserver.utils.Config;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main{

	public static void main(String[] r){
		new RedisDatabase();

		Config.serverNames.forEach(Cache::createServer);

		new Timer().scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run(){
				Cache.run();
			}
		}, 1000, 1000);

		Scanner s = new Scanner(System.in);
		String str = "";

		while(!str.equalsIgnoreCase("end")){
			str = s.nextLine();
			String[] args = str.split(" ");

			switch(args[0].toLowerCase()){
				case "add":{
					String srv = args[1];
					String player = args[2];
					Integer prio = Integer.parseInt(args[3]);

					Cache.addPlayer(player, srv, prio);
					break;
				}

				case "remove": {
					String player = args[1];
					Cache.removePlayer(player);
					break;
				}

				case "check": {
					String player = args[1];
					Server server = Cache.getPlayerServer(player);
					System.out.println("Player " + player + " is currently queued for " + (server != null ? server.getName() : "nothing") + " with priority = " + Cache.priorities.get(player));
					break;
				}

				case "status": {
					Cache.servers.forEach(server -> {
						System.out.println("\n=======================================");
						System.out.println("Name: " + server.getName());
						System.out.println("Online: " + server.isOnline());
						System.out.println("Whitelist: " + server.isWhitelist());
						System.out.println("Running: " + server.isRunning());
						System.out.println("Online players: " + server.getPlayers() + "/" + server.getMax());
						System.out.println("Player list: " + server.getQPlayers().toString());
					});
					break;
				}

				case "toggle": {
					String serverStr = args[1];
					Server server = Cache.getServerByName(serverStr);
					if(server != null){
						boolean newRunning = !server.isRunning();
						server.setRunning(newRunning);
						System.out.println("Server " + serverStr + " is now " + (newRunning ? "running" : "paused") + "!");
						continue;
					}
					System.out.println("Server " + serverStr + " does not exist!");

					break;
				}

			}

		}

	}


}
