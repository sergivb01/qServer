package me.sergivb01.qserver.utils;

import java.util.ArrayList;
import java.util.List;

public class Config{
	public static List<String> serverNames = new ArrayList<>();
	public static String REDIS_HOST = "192.168.1.42";
	public static int REDIS_PORT = 6379;
	public static boolean REDIS_AUTH = false;
	public static String REDIS_CHANNEL = "queue";
	public static String REDIS_PASSWORD = "c95668e7c556e6c096595310f33c95dd";

	static{
		serverNames.add("hcf");
		serverNames.add("kits");
		serverNames.add("lite");
	}

}
