package me.sergivb01.qserver.redis.pubsub;

import lombok.Getter;
import me.sergivb01.qserver.utils.Config;
import me.sergivb01.qserver.utils.Parser;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.Arrays;


public class Subscriber{
	@Getter
	private JedisPubSub jedisPubSub;
	private Jedis jedis;

	public Subscriber(){
		this.jedis = new Jedis(Config.REDIS_HOST, Config.REDIS_PORT, 3000);
		if(Config.REDIS_AUTH){
			this.jedis.auth(Config.REDIS_PASSWORD);
		}
		this.init();
	}

	private void init(){
		jedisPubSub = this.get();
		new Thread(() -> jedis.subscribe(jedisPubSub, Config.REDIS_CHANNEL)).start();
	}

	private JedisPubSub get(){
		return new JedisPubSub(){
			@Override
			public void onMessage(final String channel, final String message){
				final String[] args = message.split(";");
				final String command = args[0].toLowerCase();


				if(command.equalsIgnoreCase("payload")){
					Parser.parse(args[1]);
					return;
				}

				System.out.println("Recived unknown redis message! " + Arrays.toString(args));

			}

			public void onPMessage(final String s, final String s1, final String s2){
			}

			public void onSubscribe(final String s, final int i){
			}

			public void onUnsubscribe(final String s, final int i){
			}

			public void onPUnsubscribe(final String s, final int i){
			}

			public void onPSubscribe(final String s, final int i){
			}
		};

	}


}