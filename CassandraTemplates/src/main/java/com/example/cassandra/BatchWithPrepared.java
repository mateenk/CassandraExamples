package com.example.cassandra;

import java.util.UUID;

import com.datastax.driver.core.PreparedStatement;

public class BatchWithPrepared extends BatchClient {
    public void loadData() {
        PreparedStatement insertPreparedStatement = getSession().prepare(
               "BEGIN BATCH USING TIMESTAMP "+
                "   INSERT INTO simplex.songs (id, title, album, artist) " +
                        "VALUES (?, ?, ?, ?); " +
                "   INSERT INTO simplex.songs (id, title, album, artist) " +
                        "VALUES (?, ?, ?, ?); " +
                "   INSERT INTO simplex.songs (id, title, album, artist) " +
                        "VALUES (?, ?, ?, ?); " +
                "APPLY BATCH"
             );

        getSession().execute(
                insertPreparedStatement.bind(
                        UUID.randomUUID(), "Seaside Rendezvous", "A Night at the Opera", "Queen",
                        UUID.randomUUID(), "Entre Nous", "Permanent Waves", "Rush",
                        UUID.randomUUID(), "Frank Sinatra", "Fashion Nugget", "Cake"
                        ));
    }
    
    public static void main(String[] args) {
    	BatchWithPrepared client = new BatchWithPrepared();
		client.connect("127.0.0.1");
		client.loadData();
		client.querySchema();
		client.close();
	}
}
