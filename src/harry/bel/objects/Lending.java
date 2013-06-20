package harry.bel.objects;

import java.sql.*;

import harry.bel.interfaces.LendingInterface;

public class Lending implements LendingInterface {
	//Declare some variables in this class
	
	private String[] nameProperties = new String[Lending.LENDING_SIZE_PROPERTIES];
	private String[] valueProperties = new String[Lending.LENDING_SIZE_PROPERTIES];
	private Connection connector;
	private Statement statement;
	private ResultSet results;
	private PreparedStatement prepareStatement;
	private int numUsersLate = 0;
	//Declare for constructor
	
	public Lending(Connection connector) {
		this.connector = connector;
		this.initialize();
	}
	//Declare for function to initialize some variables in this function
	
	private void initialize() {
		this.nameProperties[Lending.LENDING_BOOK_ITEM_ID] = "Book_Item_Id";
		this.nameProperties[Lending.LENDING_CODE] = "Code";
		this.nameProperties[Lending.LENDING_STATUS] = "Status";
		this.nameProperties[Lending.LENDING_TIME] = "Time";
		this.nameProperties[Lending.LENDING_USER_ID] = "UserId";
		
		for(int i = 0; i < Lending.LENDING_SIZE_PROPERTIES; i ++) {
			this.valueProperties[i] = new String();
		}
	}
	
	@Override
	public void getValueProperties(String value, int type) {
		//Declare some variables in this function
		
		String stuff = "select * from Lending where " + this.nameProperties[type] + "=\"" + value + "\"";
		
		try {
			//Prepare for select statement
			
			this.prepareStatement = this.connector.prepareStatement(stuff);
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				for(int i = 0; i < Lending.LENDING_SIZE_PROPERTIES; i ++) {
					this.valueProperties[i] = this.results.getString(this.nameProperties[i]);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] getValueProperties() {
		return this.valueProperties;
	}

	@Override
	public void setValueProperties(String[] valueProperties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPlacingBook(String userId, String bookItemId) {
		//Create select query
		
		boolean result = false;
		String stuff = "select count(Code) from Lending where " + this.nameProperties[Lending.LENDING_USER_ID] + "=\"" + userId + "\" and " + this.nameProperties[Lending.LENDING_BOOK_ITEM_ID] + "=\"" + bookItemId + "\" and " + this.nameProperties[Lending.LENDING_STATUS] + "='1'";
		
		try {
			this.prepareStatement = this.connector.prepareStatement(stuff);
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				if(Integer.parseInt(this.results.getString("count(Code)")) > 0) {
					result = true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean isLendingBook(String userId, String bookItemId) {
		//Create some variables in this function
		
		boolean result = false;
		String stuff = "select count(Code) from Lending where " + this.nameProperties[Lending.LENDING_USER_ID] + "=\"" + userId + "\" and " + this.nameProperties[Lending.LENDING_BOOK_ITEM_ID] + "=\"" + bookItemId + "\" and " + this.nameProperties[Lending.LENDING_STATUS] + "='0'";
		
		try {
			this.prepareStatement = this.connector.prepareStatement(stuff);
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				if(Integer.parseInt(this.results.getString("count(Code)")) > 0) {
					result = true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String[][] getEntirePlacing() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getEntireLending() {
		// TODO Auto-generated method stub
		return null;
	}
	//Declare function to get number of users lending book late

	private String countNumUsersLate() {
		//Declare for some variables in this function
		
		String stuff = "select count(Code) from Lending where Time <= date_sub(now(), interval 15 day)";
		//Execute query and get number of users lending late
		
		try {
			//Prepare statement for stuff
			
			this.prepareStatement = this.connector.prepareStatement(stuff);
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				this.numUsersLate = Integer.parseInt(this.results.getString("count(Code)"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return stuff;
	}
	
	@Override
	public String[][] getUsersLate() {
		//Report for array elements lending
		
		String[][] result = null;
		String stuff = this.countNumUsersLate();
		int temple = 0;
		
		stuff = stuff.replace("count(Code)", "*");
		
		if(this.numUsersLate > 0) {
			result = new String[this.numUsersLate][Lending.LENDING_SIZE_PROPERTIES];
			
			for(int i = 0; i < this.numUsersLate; i ++) {
				for(int j = 0; j < Lending.LENDING_SIZE_PROPERTIES; i ++) {
					result[i][j] = new String("");
				}
			}
			//Execute select statement
			
			try {
				this.prepareStatement = this.connector.prepareStatement(stuff);
				
				this.results = this.prepareStatement.executeQuery();
				
				while(this.results.next()) {
					for(int i = 0; i < Lending.LENDING_SIZE_PROPERTIES; i ++) {
						result[temple][i] = this.results.getString(this.nameProperties[i]);
					}
					
					temple ++;
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public String[] getNameProperties() {
		return this.nameProperties;
	}

	@Override
	public void insertLendingRecord(String[] valueProperties) {
		//Create insert statement to database
		
		String stuff = "insert into Lending(";
		int lastIndex = 0;
		//Get last index of element has nonzero value
		
		for(int i = (valueProperties.length - 1); i >= 0; i --) {
			if((valueProperties[i] != null) && (!valueProperties[i].equals(""))) {
				lastIndex = i;
				break;
			}
		}
		//Create insert statement
		
		for(int i = 1; i <= lastIndex; i ++) {
			if((valueProperties[i] != null) && (!valueProperties[i].equals(""))) {
				if(i != lastIndex) {
					stuff += this.nameProperties[i] + ", ";
				} else {
					stuff += this.nameProperties[i] + ") ";
				}
			}
		}
		
		stuff += "values(";
		
		for(int i = 1; i <= lastIndex; i ++) {
			if((valueProperties[i] != null) && (!valueProperties[i].equals(""))) {
				if(i != lastIndex) {
					if(i != Lending.LENDING_STATUS) {
						stuff += "\"" + valueProperties[i] + "\", ";
					} else {
						stuff += valueProperties[i] + ", ";
					}
				}
				else {
					if(i != Lending.LENDING_STATUS) {
						stuff += "\"" + valueProperties[i] + "\")";
					} else {
						stuff += valueProperties[i] + ")";
					}
				}
			}
		}
		//Execute statement

		try {
			this.statement = this.connector.createStatement();
			
			this.statement.executeUpdate(stuff);
			
			this.statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEachProperties(String value, int type) {
		//Create update statement
		
		String stuff = "";
		
		if((type != Lending.LENDING_TIME) && (type != Lending.LENDING_STATUS)) {
			stuff = "update Lending set " + this.nameProperties[type] + "=\"" + value + "\" where Code=\"" + this.valueProperties[Lending.LENDING_CODE] + "\"";
		} else {
			stuff = "update Lending set " + this.nameProperties[type] + "=" + value + " where Code=\"" + this.valueProperties[Lending.LENDING_CODE] + "\"";
		}
		//Execute update statement
		
		try {
			this.statement = this.connector.createStatement();
			
			this.statement.executeUpdate(stuff);
			
			this.statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkForLending(String userId, int typeCheck, String bookId) {
		//Declare some variable in this function
		
		boolean result = false;
		int temple;
		String stuff = "";
		
		switch(typeCheck) {
			case Lending.ERROR_OVER_15_DAYS: {
				//Create check select statement
				
				stuff = "select count(Code) from Lending where " + this.nameProperties[Lending.LENDING_USER_ID] + "=\"" + userId + "\" and " + this.nameProperties[Lending.LENDING_STATUS] + "='1' and " + this.nameProperties[Lending.LENDING_TIME] + " <= date_sub(now(), interval 15 day)";
			}	break;
			
			case Lending.ERROR_GREATER_THAN_10_BOOKS: {
				//Create check select statement
				
				stuff = "select count(Code) from Lending where " + this.nameProperties[Lending.LENDING_USER_ID] + "=\"" + userId + "\" and " + this.nameProperties[Lending.LENDING_STATUS] + "='1'";
			}	break;
			
			case Lending.ERROR_PLACING_SAME_BOOK: {
				//Create check select statement
				
				stuff = "select count(Code) from Lending where " + this.nameProperties[Lending.LENDING_USER_ID] + "=\"" + userId + "\" and " + this.nameProperties[Lending.LENDING_BOOK_ITEM_ID] + "=\"" + bookId + "\"";
			}	break;
			
			default: {
				
			}	break;
		}
		
		try {
			this.prepareStatement = this.connector.prepareStatement(stuff);
			//Execute prepare statement
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				temple = Integer.parseInt(this.results.getString("count(Code)"));
				
				switch(typeCheck) {
					case Lending.ERROR_OVER_15_DAYS: {
						if(temple == 0) {
							result = true;
						} else {
							result = false;
						}
					}	break;
				
					case Lending.ERROR_GREATER_THAN_10_BOOKS: {
						if(temple < 10) {
							result = true;
						} else {
							result = false;
						}
					} break;
					
					case Lending.ERROR_PLACING_SAME_BOOK: {
						if(temple == 0) {
							result = true;
						} else {
							result = false;
						}
					} break;

					default: {
						
					} break;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int getNumUsersLate() {
		return this.numUsersLate;
	}

	@Override
	public void getValueProperties(String[] valueArray) {
		//Declare some variables in this function
		
		String stuff = "select * from Lending where ";
		int lastIndex = 0;
		//Get last index of nonzero in valueArray
		
		for(int i = Lending.LENDING_SIZE_PROPERTIES - 1; i >= 0; i --) {
			if((!valueArray[i].equals("")) && (valueArray != null)) {
				lastIndex = i;
				
				break;
			}
		}
		
		for(int i = 0; i < Lending.LENDING_SIZE_PROPERTIES; i ++) {
			if((!valueArray[i].equals("")) && (valueArray[i] != null)) {
				if(i != lastIndex) {
					stuff += this.nameProperties[i] + "=\"" + valueArray[i] + "\" and ";
				}
				else {
					stuff += this.nameProperties[i] + "=\"" + valueArray[i] + "\"";
				}
			}
		}
		//Execute select statement
		
		try {
			this.prepareStatement = this.connector.prepareStatement(stuff);
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				for(int i = 0; i < Lending.LENDING_SIZE_PROPERTIES; i ++) {
					this.valueProperties[i] = this.results.getString(this.nameProperties[i]);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteLendingRecord(String lendingCode) {
		//Create some variables in his function
		
		String stuff = "delete from Lending where Code='" + lendingCode + "'";
		
		try {
			this.statement = this.connector.createStatement();
			
			this.statement.executeUpdate(stuff);
			
			this.statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}