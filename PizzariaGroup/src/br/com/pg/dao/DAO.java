package br.com.pg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO {
	
	public Connection getConexao() throws SQLException{
		Connection connection = null;  
	    try {
	        String driverName = "org.gjt.mm.mysql.Driver";  
	        Class.forName(driverName);
	        String host = "localhost";
	        String port = "3306";
	        String dbname = "";
	        String url = "jdbc:mysql://" + host +  ":" + port + "/" + dbname;  
	        String user = "root";  
	        String pass = "";  
	        connection = DriverManager.getConnection(url, user, pass);
	    } catch (Exception e) {  
	    	System.out.println("Exception lan�ado - " + e.getMessage());  
	    }
	    return connection;
	}
	
	public Connection getConexao(String dbname) throws SQLException{
		Connection connection = null;  
	    try {  
	        // Load the JDBC driver  
	        String driverName = "org.gjt.mm.mysql.Driver";  
	        Class.forName(driverName);
	        String host = "localhost";
	        String port = "3306";
	        String url = "jdbc:mysql://" + host +  ":" + port + "/" + dbname;  
	        String user = "root";  
	        String pass = "";  
	        connection = DriverManager.getConnection(url, user, pass);  
	    } catch (Exception e) {  
	    	System.out.println("Exception lan�ado - " + e.getMessage());  
	    }
	    return connection;
	}
	
	public PreparedStatement querySQL(String sql, Connection con) throws SQLException{
		return con.prepareStatement(sql);
	}
	
	public PreparedStatement conectionQuery(String db, String user, String pass, String sql) throws SQLException{
		return querySQL(sql, this.getConexao());
	}
}