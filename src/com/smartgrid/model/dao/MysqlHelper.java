package com.smartgrid.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlHelper {
	private static String DRIVERCLASSNAME = "com.mysql.jdbc.Driver";
	private static String CONNECTION_URL = "jdbc:mysql://localhost:3306/smartgrid";
	private static String USER = "lactec";
	private static String PASSWORD = "lactec";
		
	// Atributos para uso da classe que herda
	protected Statement stmt = null;
	protected PreparedStatement prstmt = null;
	protected Connection conn = null;
	protected ResultSet rs = null;

	// Metodos
	protected Connection openConnection() throws Exception {
		Connection c = null;
		try {
			Class.forName(DRIVERCLASSNAME);
			c = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return c;
	}

	protected void closeConnection() {
		try {
			if (this.rs != null) {
				this.rs.close();
			}
			if (this.stmt != null) {
				this.stmt.close();
			}
			if (this.conn != null) {
				conn.close();
			}
			if (this.prstmt != null){
				prstmt.close();
			}
			System.out.println("Closed database successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
