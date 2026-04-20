package com.crud.config;
import java.sql.*;



public class ConnectionDB {
	Connection cn;
	public ConnectionDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/crud_java" , "root" ,"");
			System.out.println("Conneion établie !");
		} catch (Exception e) {
			System.out.println("Connection non établie");
		}
	}
	public Connection laConnection() {
		return cn;
	}
}
