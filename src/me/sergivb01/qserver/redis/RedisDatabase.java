package me.sergivb01.qserver.redis;

import lombok.Getter;
import me.sergivb01.qserver.redis.pubsub.Publisher;
import me.sergivb01.qserver.redis.pubsub.Subscriber;

public class RedisDatabase{
	@Getter
	public static Publisher publisher;
	@Getter
	public static Subscriber subscriber;

	public RedisDatabase(){
		init();
	}

	private void init(){
		publisher = new Publisher();
		subscriber = new Subscriber();
	}


}
