package com.smartgrid.model.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class SqliteHelper {
	protected String DBPATH = "/sqlite/smartgrid.sqlite";
	private Connection c = null;
	
	// atributos utilizados pela classe que herda esta classe
	protected Connection connection = null;
	protected Statement stmt = null;	
	protected ResultSet rs = null;
	
	Connection openConnection() {

		// Testa se a database existe
		File file = new File(DBPATH);
		if (!file.exists()) {
			System.out.println( DBPATH + " nao existe!!!");
		} else {
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager
						.getConnection("jdbc:sqlite:" + DBPATH);
				c.setAutoCommit(false);
				System.out.println("Opened database successfully");
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": "
						+ e.getMessage());
			}
		}
		return c;
	}

	void closeConnection() {
		try {
			if (this.rs != null) {
				this.rs.close();
			}		
			if (this.stmt != null) {
				this.stmt.close();
			}
			if (this.connection != null) {
				connection.close();
			}
			if (this.c != null) {
				c.close();
			}
			System.out.println("Closed database successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
