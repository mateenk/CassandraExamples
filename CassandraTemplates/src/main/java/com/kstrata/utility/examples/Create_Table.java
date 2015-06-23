package com.kstrata.utility.examples;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

public class Create_Table {

	public static void main(String args[]) {
		Cluster cluster = null;
		Session session = null;
		// Query
		String query = "CREATE TABLE if not exists emp(emp_id int PRIMARY KEY, "
				+ "emp_name text, "
				+ "emp_city text, "
				+ "emp_sal varint, "
				+ "emp_phone varint );";
		String query1 = "INSERT INTO emp (emp_id, emp_name, emp_city, emp_phone,  emp_sal)"
				+ " VALUES(1,'ram', 'Hyderabad', 9848022338, 50000);";
		String query2 = "SELECT * FROM emp";
		try {
			// Creating Cluster object
			cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
			// Creating Session object
			session = cluster.connect("tp");
			// Executing the query
			session.execute(query);
			session.execute(query1);
			ResultSet resultSet = session.execute(query2);
			System.out.println(resultSet.all());
			System.out.println("DONE!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			cluster.close();
		}
	}
}