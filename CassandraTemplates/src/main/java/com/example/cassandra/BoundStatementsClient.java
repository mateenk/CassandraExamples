package com.example.cassandra;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;

public class BoundStatementsClient extends SimpleClient {

	@Override
	public void loadData() {
		PreparedStatement statement = getSession().prepare(
				"INSERT INTO simplex.songs "
						+ "(id, title, album, artist, tags) "
						+ "VALUES (?, ?, ?, ?, ?);");
		BoundStatement boundStatement = new BoundStatement(statement);
		Set<String> tags = new HashSet<String>();
		tags.add("jazz");
		tags.add("2013");
		getSession().execute(boundStatement.bind(
		      UUID.fromString("756716f7-2e54-4715-9f00-91dcbea6cf50"),
		      "La Petite Tonkinoise'",
		      "Bye Bye Blackbird'",
		      "Joséphine Baker",
		      tags ) );
		statement = getSession().prepare(
			      "INSERT INTO simplex.playlists " +
			      "(id, song_id, title, album, artist) " +
			      "VALUES (?, ?, ?, ?, ?);");
		boundStatement = new BoundStatement(statement);
		getSession().execute(boundStatement.bind(
		      UUID.fromString("2cc9ccb7-6221-4ccb-8387-f22b6a1b354d"),
		      UUID.fromString("756716f7-2e54-4715-9f00-91dcbea6cf50"),
		      "La Petite Tonkinoise",
		      "Bye Bye Blackbird",
		      "Joséphine Baker") );
	}
}
