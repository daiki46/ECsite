package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	
	private static final String USER = "maintenance_user";
	private static final String PASS = "pass";
	private static final String URL = "jdbc:mysql://localhost:3306/pc_shop_db";
	
	public static Connection connection() {
		try {
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("DB接続に失敗");
		}
		return null;
	}
}
