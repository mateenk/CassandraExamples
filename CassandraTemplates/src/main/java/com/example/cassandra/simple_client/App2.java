package com.example.cassandra.simple_client;

import com.example.cassandra.BoundStatementsClient;

public class App2 extends App {
	public static void main(String[] args) {
		 BoundStatementsClient client = new BoundStatementsClient();
		   client.connect("127.0.0.1");
		   client.createSchema();
//		   client.loadData();
		   client.querySchema();
		   client.close();
	}
}
