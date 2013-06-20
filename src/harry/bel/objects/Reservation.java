package harry.bel.objects;

import java.sql.*;

import harry.bel.interfaces.ReservationInterface;

public class Reservation implements ReservationInterface {
	//Declare for some variables in this class
	
	private String[] nameProperties = new String[Reservation.RESERVATION_SIZE_PROPERTIES];
	private String[] valueProperties = new String[Reservation.RESERVATION_SIZE_PROPERTIES];
	private Connection connector;
	private Statement statement;
	private PreparedStatement prepareStatement;
	private ResultSet results;
	private int sumResult = 0;
	
	public Reservation(Connection connector) {
		this.connector = connector;
		this.initialize();
	}
	//Declare function to initialize value of arrays
	
	private void initialize() {
		this.nameProperties[Reservation.RESERVATION_Id] = "Id";
		this.nameProperties[Reservation.RESERVATION_BOOK_TITLE_ID] = "Book_Title_Id";
		this.nameProperties[Reservation.RESERVATION_NOTE] = "Note";
		this.nameProperties[Reservation.RESERVATION_TIME_BEGIN] = "Time_Begin";
		this.nameProperties[Reservation.RESERVATION_TIME_FINISH] = "Time_Finish";
		this.nameProperties[Reservation.RESERVATION_USER_ID] = "UserId";
		
		for(int i = 0; i < Reservation.RESERVATION_SIZE_PROPERTIES; i ++) {
			this.valueProperties[i] = new String("");
		}
	}
	
	private String getSumByEachProperty(String value, int type) {
		//Create some variables in this function
		
		String stuff = "select count(Id) from Reservation where " + this.nameProperties[type] + "=\"" + value + "\"";
		
		try {
			//Create prepare statement
			
			this.prepareStatement = this.connector.prepareStatement(stuff);
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				this.sumResult = Integer.parseInt(this.results.getString("count(Id)"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return stuff;
	}
	
	@Override
	public String[][] getByEachProperty(String value, int type) {
		//Declare some variable here
		
		String stuff = this.getSumByEachProperty(value, type);
		String[][] result = new String[this.sumResult][Reservation.RESERVATION_SIZE_PROPERTIES];
		int temple = 0;
		//Initialize for two dimensionals array result
		
		for(int i = 0; i < this.sumResult; i ++) {
			for(int j = 0; j < Reservation.RESERVATION_SIZE_PROPERTIES; j ++) {
				result[i][j] = new String("");
			}
		}
		
		//Get really select query
		
		stuff = stuff.replace("count(Id)", "*");
		
		try {
			//Create prepare statement
			
			this.prepareStatement = this.connector.prepareStatement(stuff);
			
			this.results = this.prepareStatement.executeQuery();
			
			while(!this.results.next()) {
				for(int i = 0; i < Reservation.RESERVATION_SIZE_PROPERTIES; i ++) {
					result[temple][i] = this.results.getString(this.nameProperties[i]);
				}
				
				temple ++;
			}
			
			this.prepareStatement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String[] getValueProperties() {
		return this.valueProperties;
	}

	@Override
	public void setValueProperties(String[] valueProperties) {
		this.valueProperties = valueProperties;
	}

	@Override
	public void setEachProperty(String value, int type) {
		//Create some variables in this function
		
		String stuff = "update Reservation set " + this.nameProperties[type] + "=";
		
		if(type == Reservation.RESERVATION_TIME_FINISH) {
			stuff += value;
		} else {
			stuff += "\"" + value + "\"";
		}
		
		stuff += " where Id=\"" + this.valueProperties[Reservation.RESERVATION_Id] + "\"";
		
		try {
			//Create statement
			
			this.statement = this.connector.createStatement();
			
			this.statement.executeUpdate(stuff);
			
			this.statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertReservation(String[] valueProperties) {
		//Declare some variable in this function
		
		String stuff = "insert into Reservation(";
		int lastIndex = 0;
		//Get last index is nonzero
		
		for(int i = (Reservation.RESERVATION_SIZE_PROPERTIES - 1); i >= 0; i --) {
			if((!valueProperties[i].equals("")) && (valueProperties[i] != null)) {
				lastIndex = i;
				
				break;
			}
		}
		//Create insert statement
		
		for(int i = 0; i < Reservation.RESERVATION_SIZE_PROPERTIES; i ++) {
			if((!valueProperties[i].equals("")) && (valueProperties[i] != null)) {
				if(i != lastIndex) {
					stuff += this.nameProperties[i] + ", ";
				}
				else {
					stuff += this.nameProperties[i] + ")";
					
					break;
				}
			}
		}
		
		stuff += " values(";
		
		for(int i = 0; i < Reservation.RESERVATION_SIZE_PROPERTIES; i ++) {
			if((!valueProperties[i].equals("")) && (valueProperties[i] != null)) {
				if(i != lastIndex) {
					if(i != Reservation.RESERVATION_TIME_FINISH) {
						stuff += "\"" + valueProperties[i] + "\", ";
					} else {
						stuff += valueProperties[i] + ", ";
					}
				} else {
					if(i != Reservation.RESERVATION_TIME_FINISH) {
						stuff += "\"" + valueProperties[i] + "\")";
					} else {
						stuff += valueProperties[i] + ")";
					}
					
					break;
				}
			}
		}
		
		try {
			//Create statement
			
			this.statement = this.connector.createStatement();
			
			this.statement.executeUpdate(stuff);
			
			this.statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] getNameProperties() {
		return this.nameProperties;
	}

	@Override
	public void getValueProperties(String[] valueProperties) {
		//Create select statement
		
		String stuff = "select * from Reservation where ";
		int lastIndex = 0;
		//Get lastIndex has none-empty
		
		for(int i = (Reservation.RESERVATION_SIZE_PROPERTIES - 1); i >= 0; i --) {
			if((!valueProperties[i].equals("")) && (valueProperties[i] != null)) {
				lastIndex = i;
				
				break;
			}
		}
		
		for(int i = 0; i < Reservation.RESERVATION_SIZE_PROPERTIES; i ++) {
			if((!valueProperties[i].equals("")) && (valueProperties[i] != null)) {
				if(i != lastIndex) {
					stuff += this.nameProperties[i] + "=\"" + valueProperties[i] + "\" and ";
				} else {
					stuff += this.nameProperties[i] + "=\"" + valueProperties[i] + "\"";
				}
			}
		}
		//Execute select query
		
		try{
			//Prepare statement
			
			this.prepareStatement = this.connector.prepareStatement(stuff);
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				for(int i = 0; i < Reservation.RESERVATION_SIZE_PROPERTIES; i ++) {
					this.valueProperties[i] = this.results.getString(this.nameProperties[i]);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isInfringement() {
		//Declare some variables in this function
		
		boolean result = false;
		String stuff = "select count(Id) from Reservation where Id=\"" + this.valueProperties[Reservation.RESERVATION_Id] + "\" and date_sub(\"" + this.valueProperties[Reservation.RESERVATION_TIME_FINISH] + "\", interval 15 day) >= \"" + this.valueProperties[Reservation.RESERVATION_TIME_BEGIN] + "\""; 
		//Execute select query
		
		try {
			//Prepare for statement
			
			this.prepareStatement = this.connector.prepareStatement(stuff);
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				if(Integer.parseInt(this.results.getString("count(Id)")) >= 1) {
					result = false;
				} else {
					result = true;
				}
			}
			
			this.prepareStatement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
