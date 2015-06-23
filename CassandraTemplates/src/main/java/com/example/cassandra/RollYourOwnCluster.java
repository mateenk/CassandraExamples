package com.example.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;

public class RollYourOwnCluster extends SimpleClient {

	public RollYourOwnCluster() {
		   Cluster cluster = Cluster.builder()
		         .addContactPoints("127.0.0.1", "127.0.0.2")
		         .withRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE)
		         .withReconnectionPolicy(new ConstantReconnectionPolicy(100L))
		         .build();
		   Session session = cluster.connect();
		   ResultSet results = session.execute("SELECT * FROM simplex.playlists " +
			        "WHERE id = 2cc9ccb7-6221-4ccb-8387-f22b6a1b354d;");
			System.out.println(String.format("%-30s\t%-20s\t%-20s\n%s", "title", "album", "artist",
				       "-------------------------------+-----------------------+--------------------"));
				for (Row row : results) {
				    System.out.println(String.format("%-30s\t%-20s\t%-20s", row.getString("title"),
				    row.getString("album"),  row.getString("artist")));
				}
				System.out.println();
		   session.close();
		   cluster.close();
		}
	
	public static void main(String[] args) {
		new RollYourOwnCluster();
	}

}
