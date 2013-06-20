package harry.bel.objects;

import java.sql.*;

import harry.bel.interfaces.Database;

public class MySQLConnector implements Database {
	//Report for variables in this class
	
	private Connection connector = null;
	
	//Report for constructor of this class
	
	public MySQLConnector() {
		this.getConnection();
	}
	
	public MySQLConnector(String databaseName, String userName, String userPass) {
		this.getConnection(databaseName, userName, userPass);
	}

	@Override
	public boolean getConnection() {
		//Report variable for result
		
		boolean result = false;
		//Connect to database
		
		try {
			Class.forName(DRIVER).newInstance();
			
			this.connector = DriverManager.getConnection(URL + defaultDatabase, defaultUser, defaultPass);
			
			if((this.connector != null) && (this.connector.isValid(10))) {
				result = true;
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean getConnection(String databaseName, String userName, String userPass) {
		//Report boolean variable for result
		
		boolean result = false;
		
		try {
			Class.forName(DRIVER).newInstance();
			
			this.connector = DriverManager.getConnection(URL + databaseName, userName, userPass);
			
			if((this.connector != null) && (this.connector.isValid(10))) {
				result = true;
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	//Declare for getter and setter of connection
	
	public void setConnector(Connection connector) {
		this.connector = connector;
	}
	
	public Connection getConnector() {
		return this.connector;
	}
}
