package harry.bel.objects;

import java.sql.*;

import harry.bel.interfaces.UserInterfaces;


public class User implements UserInterfaces {
	//Create some variables in this functions
	
	private String[] nameProperties = new String[11];
	private String[] valueProperties = new String[11];
	private boolean isAdmin = false;
	private Statement statement;
	private ResultSet results;
	private PreparedStatement prepareStatement;
	private Connection connector;
	//Declare for constructors of this class
	
	public User(Connection connector) {
		this.initializeMembers();
		this.connector = connector;
	}
	//Declare for function to auto initialize members of this class
	
	private void initializeMembers() {
		//Create each name of property
		
		this.nameProperties[USER_ID] = "Id";
		this.nameProperties[USER_NAME] = "Name";
		this.nameProperties[USER_PASS] = "Password";
		this.nameProperties[USER_TIME_BEGIN] = "Time_Begin";
		this.nameProperties[USER_EMAIL] = "Email";
		this.nameProperties[USER_ADDRESS] = "Address";
		this.nameProperties[USER_TELEPHONE] = "Telephone";
		this.nameProperties[USER_GENDER] = "Gender";
		this.nameProperties[USER_BIRTHDAY] = "Birthday";
		this.nameProperties[USER_JOB] = "Job";
		this.nameProperties[ADMIN_ADD_INFS] = "Additional_Information";
		
		for(int i = 0; i < this.valueProperties.length; i ++) {
			this.valueProperties[i] = new String("");
		}
	}
	
	private String convertToString(int index) {
		String result = null;
		
		switch(index) {
			case USER_ID: result = "Id";
				break;
				
			case USER_NAME: result = "Name";
				break;
			
			case USER_PASS: result = "Password";
				break;
			
			case USER_TIME_BEGIN: result = "Time_Begin";
				break;
			
			case USER_EMAIL: result = "Email";
				break;
			
			case USER_ADDRESS: result = "Address";
				break;
			
			case USER_TELEPHONE: result = "Telephone";
				break;
			
			case USER_GENDER: result = "Gender";
				break;
			
			case USER_BIRTHDAY: result = "Birthday";
				break;
			
			case USER_JOB: result = "Job";
				break;
				
			case ADMIN_ADD_INFS: result = "Additional_Information";
				break;
		}
		
		return result;
	}
	//Declare check user when log in
	
	@Override
	public boolean checkUser(String userName, String userPassword) {
		//Create some statement
		
		String stuff = "select *, count(Id) from User where Name='" + userName + "' and Password='" + userPassword + "'";
		boolean result = false;
		
		try {
			this.prepareStatement = this.connector.prepareStatement(stuff);
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next() && this.results.getString("count(Id)").equals("1")) {
				stuff = "select count(Id) from Librarian where UserId='" + this.results.getString(this.nameProperties[User.USER_ID]) + "'";
				this.prepareStatement = this.connector.prepareStatement(stuff);
				this.results = this.prepareStatement.executeQuery();
				
				if(this.results.next() && this.results.getString("count(Id)").equals("1")) {
					this.isAdmin = true;
				}
				else {
					this.isAdmin = false;
				}
	
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean updateInformations(String[] newValues) {
		//Declare some variables in this function, newValue must have length equal to valueProperties
		
		boolean result = false;
		//Create update to User table
		
		try {
		this.statement = this.connector.createStatement();
		String stuff = "update User set ";
		int lastIndex = 0;
		//Loop to define last nonzero position in newValues array
		
		for(int i = newValues.length - 2; i > 0; i --) {
			if(!newValues[i].equals("") && newValues[i] != null) {
				lastIndex = i;
				break;
			}
		}
		
		//Create statement to update information of user
		
		for(int i = 1; i < newValues.length - 1; i ++) {
			if(!newValues[i].equals("")) {
				if(i != lastIndex) {
					stuff += this.convertToString(i) + "='" + newValues[i] + "', ";
				}
				else {
					stuff += this.convertToString(i) + "='" + newValues[i] + "' ";
				}
			}
		}
		
		stuff += "where Id='" + this.valueProperties[USER_ID] + "'";
		//Execute update to members of User
		
		this.statement.executeUpdate(stuff);
		//Check and update for Librarian or Admin
		
		if(isAdmin && (!newValues[ADMIN_ADD_INFS].equals(""))) {
			stuff = "update Librarian set " + this.convertToString(ADMIN_ADD_INFS) +"='" + newValues[newValues.length - 1] + "' where UserId='" + this.valueProperties[USER_ID] + "'";
			this.statement.executeUpdate(stuff);
		}
		
		this.statement.close();
		
		result = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.getInformations(this.valueProperties[USER_ID]);
		
		return result;
	}

	@Override
	public boolean getFlagAdmin() {
		return this.isAdmin;
	}

	@Override
	public void setNameProperties(String[] nameProperties) {
		this.nameProperties = nameProperties;
	}

	@Override
	public String[] getNameProperties() {
		return this.nameProperties;
	}

	@Override
	public void setValueProperties(String[] valueProperties) {
		this.valueProperties = valueProperties;
	}

	@Override
	public String[] getValueProperties() {
		return this.valueProperties;
	}

	@Override
	public void getInformations(String userName, String userPassword) {
		try {
			//Set some variables here
			
			String stuff = "select * from User where Name='" + userName + "' and Password='" + userPassword + "'";
			this.prepareStatement = this.connector.prepareStatement(stuff);
			//Execute select query
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				for(int i = 0; i < this.valueProperties.length - 1; i ++) {
					this.valueProperties[i] = this.results.getString(this.convertToString(i));
				}
			}
			
			if(this.isAdmin) {
				stuff = "select " + this.nameProperties[ADMIN_ADD_INFS] + " from Librarian where UserId='" + this.valueProperties[USER_ID] + "'";
				this.prepareStatement = this.connector.prepareStatement(stuff);
				this.results = this.prepareStatement.executeQuery();
				
				if(this.results.next()) {
					this.valueProperties[ADMIN_ADD_INFS] = this.results.getString("Additional_Information");
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertUser(String[] informations) {
		String stuff = "insert into User(";
		
		for(int i = 1; i < this.nameProperties.length - 1; i ++) {
			if(i != USER_TIME_BEGIN) {
				stuff += this.convertToString(i);
			
				if(i != (this.nameProperties.length - 2)) {
					stuff += ", ";
				} 
				else {
					stuff += ") ";
				}
			}
		}
		
		stuff += "values(";
		
		for(int i = 1; i < this.nameProperties.length - 1; i ++) {
			if(i != USER_TIME_BEGIN) {
				stuff += "'" +  informations[i] + "'";
			
				if(i != (this.nameProperties.length - 2)) {
					stuff += ", ";
				} 
				else {
					stuff += ")";
				}
			}
		}
		
		try {
			this.statement = this.connector.createStatement();
			this.statement.executeUpdate(stuff);
			this.statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void getInformations(String Id) {
		try {
			//Set some variables here
			
			String stuff = "select * from User where Id='" + Id +"'";
			this.prepareStatement = this.connector.prepareStatement(stuff);
			//Execute select query
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				for(int i = 0; i < this.valueProperties.length - 1; i ++) {
					this.valueProperties[i] = this.results.getString(this.convertToString(i));
				}
			}
			
			if(isAdmin) {
				stuff = "select " + this.nameProperties[ADMIN_ADD_INFS] + " from Librarian where UserId='" + this.valueProperties[USER_ID] + "'";
				this.prepareStatement = this.connector.prepareStatement(stuff);
				this.results = this.prepareStatement.executeQuery();
				
				if(this.results.next()) {
					this.valueProperties[ADMIN_ADD_INFS] = this.results.getString("Additional_Information");
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean updateInformations(String value, int type) {
		//Declare for some variables in this function
		
		boolean result = false;
		
		try {
			this.statement = this.connector.createStatement();
			
			String stuff = "update User set " + this.convertToString(type) + "='" + value + "'" + " where Id='" + this.valueProperties[User.USER_ID] + "'";
			
			this.statement.executeUpdate(stuff);
			
			this.statement.close();
			result = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean updateAdditionalInformationLibrarian(String value) {
		//Declare some variables in this function
		
		boolean result = false;
		
		if(this.isAdmin) {
			try {
				this.statement = this.connector.createStatement();
				String stuff = "update Librarian set Additional_Information='" + value + "' where UserId='" + this.valueProperties[USER_ID] + "'";
				
				this.statement.executeUpdate(stuff);
				this.statement.close();
				
				result = true; 
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		MySQLConnector connection = new MySQLConnector();
		User tester = new User(connection.getConnector());
		tester.checkUser("User Tester", "dungkstn");
		tester.getInformations("User Tester", "dungkstn");
		
		String[] test = new String[10];
		test[User.USER_NAME] = "Test from java source code";
		test[User.USER_ADDRESS] = "Số 955, thành phố Hải Dương, tỉnh Hải Dương";
		test[User.USER_BIRTHDAY] = "1992-03-04";
		test[User.USER_GENDER] = "male";
		test[User.USER_JOB] = "Student";
		test[User.USER_TELEPHONE] = "0973675143";
		test[User.USER_PASS] = "dungkstn";
		test[User.USER_EMAIL] = "lyvietdung92@gmail.com";
		test[User.USER_TIME_BEGIN] = "";
		test[User.USER_ID] = new String();
		
		tester.insertUser(test);
		/*
		System.out.println(tester.checkUser("Dung Harry", "dungkstn"));
		System.out.println(tester.getFlagAdmin());
		
		tester.getInformations("Dung Harry", "dungkstn");
		
		String[] result = tester.getValueProperties();
		
		for(int i = 0; i < result.length; i ++) {
			System.out.println(result[i]);
		}
		
		String[] secondTest = new String[11];
		secondTest[User.USER_NAME] = "Dung Harry";
		secondTest[User.USER_ADDRESS] = "Số 955, thành phố Hải Dương, tỉnh Hải Dương";
		secondTest[User.USER_BIRTHDAY] = "1992-03-04";
		secondTest[User.USER_GENDER] = "male";
		secondTest[User.USER_JOB] = "Student";
		secondTest[User.USER_TELEPHONE] = "0973675143";
		secondTest[User.USER_PASS] = "dungkstn";
		secondTest[User.USER_EMAIL] = "lyvietdung92@gmail.com";
		secondTest[User.USER_TIME_BEGIN] = "";
		secondTest[User.USER_ID] = new String();
		secondTest[User.ADMIN_ADD_INFS] = "Founder and CEO at BELibrary. I create BELibrary when I am styding at Hanoi University of Science and Technology - HUST";
		
		tester.updateInformations(secondTest);
		*/
		//tester.updateAdditionalInformationLibrarian("Founder and CEO at BELibrary. I create BELibrary when I am styding at Hanoi University of Science and Technology - HUST");
		//System.out.println(tester.updateInformations("Dung Harry", User.USER_NAME));
		
		System.out.println(tester.getFlagAdmin());
	}

	@Override
	public void setFlag(boolean flag) {
		this.isAdmin = flag;
	}

	@Override
	public boolean existAccount(String userName) {
		//Declare some variable in this function
		
		boolean result = false;
		String stuff = "select count(Id) from User where " + this.nameProperties[User.USER_NAME] + "=\"" + userName + "\"";
		
		try {
			this.prepareStatement = this.connector.prepareStatement(stuff);
			//Execute select query
			
			this.results = this.prepareStatement.executeQuery();
			
			//Compare result to set result variable
			
			if(this.results.next()) {
				if(!this.results.getString("count(Id)").equals("0")) {
					result = true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
