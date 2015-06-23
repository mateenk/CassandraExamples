package com.kstrata.utility.examples;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class UtilityConnection {
	private static Cluster cluster;
	private static Session session;
	
	static{
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect("kstrata");
	}
	
	public static Cluster getCluster() {
		return cluster;
	}
	public static Session getSession() {
		return session;
	}
	
	public static void close(){
		session.close();
		cluster.close();
	}

}
