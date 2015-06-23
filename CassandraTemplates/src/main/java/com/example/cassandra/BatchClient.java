package com.example.cassandra;

import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

public class BatchClient extends SimpleClient {
	public void loadData() {
		getSession()
				.execute(
						"BEGIN BATCH USING TIMESTAMP "
								+ "   INSERT INTO simplex.songs (id, title, album, artist) VALUES ("
								+ UUID.randomUUID()
								+ ", 'Poulaillers'' Song', 'Jamais content', 'Alain Souchon'); "
								+ "   INSERT INTO simplex.songs (id, title, album, artist) VALUES ("
								+ UUID.randomUUID()
								+ ", 'Bonnie and Clyde', 'Bonnie and Clyde', 'Serge Gainsbourg'); "
								+ "   INSERT INTO simplex.songs (id, title, album, artist) VALUES ("
								+ UUID.randomUUID()
								+ ", 'Lighthouse Keeper', 'A Clockwork Orange', 'Erika Eigen'); "
								+ "APPLY BATCH");
	}

	public void querySchema(){
		ResultSet results = getSession().execute("SELECT * FROM simplex.songs ");
		System.out.println(String.format("%-30s\t%-20s\t%-20s\n%s", "title", "album", "artist",
			       "-------------------------------+-----------------------+--------------------"));
			for (Row row : results) {
			    System.out.println(String.format("%-30s\t%-20s\t%-20s", row.getString("title"),
			    row.getString("album"),  row.getString("artist")));
			}
			System.out.println();
	}
	public static void main(String[] args) {
		BatchClient client = new BatchClient();
		client.connect("127.0.0.1");
		client.loadData();
		client.querySchema();
		client.close();
	}
}