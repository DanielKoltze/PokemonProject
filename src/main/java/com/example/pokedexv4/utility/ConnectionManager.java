package com.example.pokedexv4.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private static Connection connection = null;

    //vi laver en singleton, så der altid er en instans
    public static Connection setConnection() {

            /*
            En anden måde at gøre det på er ved at lave en autowired environment
            Vi bruger autowired for at lave en dependency injection
            @Autowired
            private Environment environment;

            String pmPassword = environment.getProperty("pm_password");
            String pmUrl = environment.getProperty("pn_url");
            String pmUsername = environment.getProperty("pm_username");
             */


            if(connection == null){
                String pmPassword = System.getenv("pm_password");
                String pmUrl = System.getenv("pm_url");
                String pmUsername = System.getenv("pm_username");
                try {
                connection = DriverManager.getConnection(pmUrl, pmUsername, pmPassword);
            }catch (Exception e) {
                    System.out.println("Databasen er ikke connected");
                }
        }
        return connection;
    }
}
