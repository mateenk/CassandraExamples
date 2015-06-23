package com.example.cassandra;

import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.PreparedStatement;

public class BatchWithPrepared2 extends BatchWithPrepared{
	public void loadData() {
		BatchStatement batch = new BatchStatement();
        PreparedStatement insertSongPreparedStatement = getSession().prepare(
                "INSERT INTO simplex.songs (id, title, album, artist) VALUES (?, ?, ?, ?);");
        batch.add(insertSongPreparedStatement.bind(
                UUID.randomUUID(), "Mateen Die Mösch", "In Gold", "Willi Ostermann"));
        batch.add(insertSongPreparedStatement.bind(
                UUID.randomUUID(), "Memo From Turner", "Performance", "Mick Jagger"));
        batch.add(insertSongPreparedStatement.bind(
                UUID.randomUUID(), "La Petite Tonkinoise", "Bye Bye Blackbird", "Joséphine Baker"));
        getSession().execute(batch);
    }
	 public static void main(String[] args) {
	    	BatchWithPrepared client = new BatchWithPrepared2();
			client.connect("127.0.0.1");
			client.loadData();
			client.querySchema();
			client.close();
		}
}
