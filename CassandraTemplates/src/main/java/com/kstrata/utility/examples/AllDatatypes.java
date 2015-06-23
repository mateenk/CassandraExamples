//added this line at https://github.com/mateenk/CassandraExamples/edit/master/CassandraTemplates/src/main/java/com/kstrata/utility/examples/AllDatatypes.java
package com.kstrata.utility.examples;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

public class AllDatatypes {

	String allDatatypeTableCreateQry = ""
			+ "create table if not exists emp("
			+ "	id int primary key,"
			+ "asciiCol ascii,"
			+ "bigintCol bigint,"
			+ "blobCol blob,"
			+ "booleanCol boolean,"
//			+ "counterCol counter,"
			+ "decimalCol decimal,"
			+ "doubleCol double,"
			+ "floatCol float,"
			+ "inetCol inet,"
			+ "intCol int,"
			+ "listCol list<text>,"
			+ "mapCol map<int,text>,"
			+ "setCol set<text>,"
			+ "textCol text,"
			+ "timestampCol timestamp,"
//			+ "timeuuidCol timeuuid,"
			+ "uuidCol uuid,"
			+ "varcharCol varchar,"
			+ "varintCol varint"
			+ ");";
	
	String insertDataQry ="insert into emp"
			+ "(id,asciiCol,bigintCol,blobCol,booleanCol,decimalCol,doubleCol,floatCol,inetCol,intCol,listCol,mapCol,setCol,textCol,timestampCol,uuidCol,varcharCol,varintCol)"
			+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public void insertData(){
		try {
			PreparedStatement statement = UtilityConnection.getSession().prepare(insertDataQry);
			BoundStatement boundStatement = new BoundStatement(statement);
			InetAddress address = InetAddress.getByName("www.kstrata.com");
			List<String> list = new ArrayList<String>();
			list.add("mateen");list.add("ahmed");list.add("khan");
			Map<Integer, String> map = new LinkedHashMap<Integer, String>();
			map.put(1,"Air");map.put(2,"Water");map.put(3,"Food");
			Set<String> set = new HashSet<String>();
			set.add("samsung");set.add("nokia");set.add("lenevo");set.add("apple");
			UUID uuid = UUID.randomUUID();
			BigInteger bigInteger = new BigInteger("10000000000000000");
			boundStatement.bind(1,"Mateen",2l,ByteBuffer.wrap("blob data".getBytes()),
					true,new BigDecimal(10),1000.22,21.2f,address,1234567,list,map,set,
					"Hyderabad",new Date(),uuid,"varchar",bigInteger);
			UtilityConnection.getSession().execute(boundStatement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	String readDataQry="select * from emp;";
	String allDatatypeTableDropQry = "drop table if exists emp;";
	String keySpaceCreateQry = "CREATE KEYSPACE if not exists kstrata WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};";
	String keySpaceDropQry = "drop keyspace kstrata;";
	
	
	public boolean createTable(){
		boolean result=false;
		try {
//			createKeySpace();
			UtilityConnection.getSession().execute(allDatatypeTableCreateQry);
			result=true;
			System.out.println("table created");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean createKeySpace(){
		boolean result=false;
		try{
			UtilityConnection.getSession().execute(keySpaceCreateQry);
			result=true;
			System.out.println("key space created");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public boolean dropTable(){
		boolean result=false;
		try {
			UtilityConnection.getSession().execute(allDatatypeTableDropQry);
			result=true;
			System.out.println("table dropped");
		} catch (Exception e) {
				e.printStackTrace();
		}
		return result;
	}
	public boolean dropKeySpace(){
		boolean result=false;
		try {
			UtilityConnection.getSession().execute(keySpaceDropQry);
			result=true;
			System.out.println("keyspace dropped");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void readData(){
		try {
			ResultSet resultSet = UtilityConnection.getSession().execute(readDataQry);
			for (Row row : resultSet) {
				System.out.println(row.getInt(0));
				System.out.println(row.getString(1));
				System.out.println(row.getLong(2));
				ByteBuffer buf = row.getBytes(3);
				while(buf.hasRemaining()){
				      System.out.print((char) buf.get()); // read 1 byte at a time
			    }
				System.out.println();
				System.out.println(row.getBool(4));
				System.out.println(row.getDecimal(5));
				System.out.println(row.getDouble(6));
				System.out.println(row.getFloat(7));
				System.out.println(row.getInet(8));
				System.out.println(row.getInt(9));
				System.out.println(row.getList(10,String.class));
				System.out.println(row.getMap(11,Integer.class,String.class));
				System.out.println(row.getSet(12,String.class));
				System.out.println(row.getString(13));
				System.out.println(row.getDate(14));
				System.out.println(row.getUUID(15));
				System.out.println(row.getString(16));
				System.out.println(row.getVarint(17));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
