package org.example.connection;

import org.neo4j.driver.*;

public class driver implements AutoCloseable{
    private final Driver driver;


    // conection to neo4j database
    public static void connection(String uri, String username, String password){
        driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }
}
