package com.example.cassandra.simple_client;

import com.example.cassandra.SimpleClient;

/**
 *
 * Hello world!
 */
public class App 
{
    public static void main( String[] args )
    {
        SimpleClient client = new SimpleClient();
        client.connect("127.0.0.1");
        client.createSchema();
        client.loadData();
        client.querySchema();
        client.close();
    }
}
