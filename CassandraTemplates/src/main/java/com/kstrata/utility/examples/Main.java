package com.kstrata.utility.examples;

public class Main {
	public static void main(String[] args) {
		AllDatatypes obj = new AllDatatypes();
		obj.dropTable();
		obj.createTable();
		obj.insertData();
		obj.readData();
		UtilityConnection.close();
//		
//		String a = "create table if not exists emp("
//				+ "	id int primary key,"
//				+ "asciiCol ascii,"
//				+ "bigintCol bigint,"
//				+ "blobCol blob,"
//				+ "booleanCol boolean,"
////				+ "counterCol counter,"
//				+ "decimalCol decimal,"
//				+ "doubleCol double,"
//				+ "floatCol float"
//				+ ");";
//		System.out.println(a);
	}
}
