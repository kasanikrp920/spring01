package com.trimind.restdemo1.entities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

    private static Connection con;

    private DBconnection(){}

    static {
        try{
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "root");

        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public static Connection getCon(){
        return  con;
    }

}
