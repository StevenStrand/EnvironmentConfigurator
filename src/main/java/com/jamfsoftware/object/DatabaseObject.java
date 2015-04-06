package com.jamfsoftware.object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jamfsoftware.event.ReadXML;

public class DatabaseObject {
	public static Connection getConnection(String path) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Document doc = ReadXML.loadXMLDocument(path);
			NodeList nList = ReadXML.getNodeList(doc, "DataBase");
			Node nNode = ReadXML.getXMLNode(nList, 0);
			
			String dbms = ReadXML.getXMLElement(nNode, "DataBaseType", 0);
			String serverName = ReadXML.getXMLElement(nNode, "ServerName", 0);
			String portNumber = ReadXML.getXMLElement(nNode, "ServerPort", 0);
			
			Properties connectionProps = new Properties();
			connectionProps.put("user", ReadXML.getXMLElement(nNode, "DataBaseUser", 0));
			connectionProps.put("password", ReadXML.getXMLElement(nNode, "DataBasePassword", 0));
			
			conn = DriverManager.getConnection("jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/", connectionProps);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static Connection getConnectionDatabase(String path) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Document doc = ReadXML.loadXMLDocument(path);
			NodeList nList = ReadXML.getNodeList(doc, "DataBase");
			Node nNode = ReadXML.getXMLNode(nList, 0);
			
			String dbms = ReadXML.getXMLElement(nNode, "DataBaseType", 0);
			String serverName = ReadXML.getXMLElement(nNode, "ServerName", 0);
			String portNumber = ReadXML.getXMLElement(nNode, "ServerPort", 0);
			String dbName = ReadXML.getXMLElement(nNode, "DataBaseName", 0);
			
			Properties connectionProps = new Properties();
			connectionProps.put("user", ReadXML.getXMLElement(nNode, "DataBaseUser", 0));
			connectionProps.put("password", ReadXML.getXMLElement(nNode, "DataBasePassword", 0));
			
			conn = DriverManager.getConnection("jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
