package com.crud.config;
import java.sql.*;
import java.awt.*;


public class ConnectionDB {
	Connection cn;
	public ConnectionDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver")
			cn = DriverManager.getConnection("jdbc:")
			System.out.println("Conneion établie !");
		} catch (Exception e) {
			System.out.println("Connection non établie");
		}
	}
}
