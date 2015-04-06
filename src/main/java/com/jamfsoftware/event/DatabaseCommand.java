package com.jamfsoftware.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jamfsoftware.object.DatabaseObject;

public class DatabaseCommand {
	public boolean checkForServer(String databaseXML) {
		boolean exists = false;
			try {
				Connection conn = DatabaseObject.getConnection(databaseXML);
				if(!conn.equals(null)) {
					exists = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return exists;
	}
	
	public boolean checkForDatabase(String database, String databaseXML) {
		boolean exists = false;
		try {
			Connection conn = DatabaseObject.getConnectionDatabase(databaseXML);
			PreparedStatement ps = conn.prepareStatement("show databases");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String dbName = rs.getString("Database");
				if (dbName.equalsIgnoreCase(database)) {
					exists = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	public PreparedStatement prepareQuery(String query, String databaseXML) {
		PreparedStatement ps = null;
		try {
			Connection conn = DatabaseObject.getConnectionDatabase(databaseXML);
			ps = conn.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ps;
	}
	
}
