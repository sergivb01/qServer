package me.sergivb01.qserver.queue;

import me.sergivb01.qserver.Cache;

public class Comparator implements java.util.Comparator<String>{

	@Override
	public int compare(String x, String y){
		int prioX = Cache.priorities.get(x);
		int prioY = Cache.priorities.get(y);

		if(prioX < prioY){
			return -1;
		}
		if(prioX > prioY){
			return 1;
		}

		return 0;
	}
}

