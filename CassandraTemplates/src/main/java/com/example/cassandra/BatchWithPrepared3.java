package com.example.cassandra;

import java.util.Date;
import java.util.UUID;

public class BatchWithPrepared3 extends BatchWithPrepared{
	public void loadData() {
        long timestamp = new Date().getTime();
        getSession().execute(
                "BEGIN BATCH USING TIMESTAMP " + timestamp +
                "   INSERT INTO simplex.songs (id, title, album, artist) VALUES (" + 
                        UUID.randomUUID() + 
                        ", 'Poulaillers'' Song', 'Jamais content', 'Alain Souchon'); " +
                "   INSERT INTO simplex.songs (id, title, album, artist) VALUES (" + 
                        UUID.randomUUID() + 
                        ", 'Bonnie and Clyde', 'Bonnie and Clyde', 'Serge Gainsbourg'); " +
                "   INSERT INTO simplex.songs (id, title, album, artist) VALUES (" + 
                        UUID.randomUUID() + 
                        ", 'Ahmed Lighthouse Keeper', 'A Clockwork Orange', 'Erika Eigen'); " +
                "APPLY BATCH"
                );
    }
	 public static void main(String[] args) {
	    	BatchWithPrepared client = new BatchWithPrepared3();
			client.connect("127.0.0.1");
			client.loadData();
			client.querySchema();
			client.close();
		}
}
