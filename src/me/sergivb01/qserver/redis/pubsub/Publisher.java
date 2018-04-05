package me.sergivb01.qserver.redis.pubsub;

import lombok.Getter;
import me.sergivb01.qserver.utils.Config;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Publisher{
	@Getter
	private JedisPool pool;

	public Publisher(){
		if(Config.REDIS_AUTH){
			this.pool = new JedisPool(new JedisPoolConfig(), Config.REDIS_HOST, Config.REDIS_PORT, 3000);
		}else{
			this.pool = new JedisPool(new JedisPoolConfig(), Config.REDIS_HOST, Config.REDIS_PORT, 3000);
		}
	}

	public void write(final String message){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			if(Config.REDIS_AUTH){
				jedis.auth(Config.REDIS_PASSWORD);
			}
			jedis.publish(Config.REDIS_CHANNEL, message);
			pool.returnResource(jedis);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}


}
