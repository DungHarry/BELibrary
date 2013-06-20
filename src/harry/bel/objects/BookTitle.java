package harry.bel.objects;

import java.sql.*;

import harry.bel.interfaces.BookTitleInterface;

public class BookTitle implements BookTitleInterface {
	//Declare for some variables in this function
	
	String[] nameProperties = new String[BookTitle.BOOK_TITLE_SIZE_PROPERTIES];
	String[] valueProperties = new String[BookTitle.BOOK_TITLE_SIZE_PROPERTIES];
	Connection connector;
	Statement statement;
	PreparedStatement prepareStatement;
	ResultSet results;
	
	public BookTitle(Connection connector) {
		this.connector = connector;
		
		this.initialize();
	}
	//Report for method to initialize members in this class
	
	private void initialize() {
		this.nameProperties[BookTitle.BOOK_TITLE_BI_ID] = "Book_Item_Id";
		this.nameProperties[BookTitle.BOOK_TITLE_COUNT] = "Count";
		this.nameProperties[BookTitle.BOOK_TITLE_ID] = "Id";
		this.nameProperties[BookTitle.BOOK_TITLE_NUM_LENDING] = "Num_Lending";
		this.nameProperties[BookTitle.BOOK_TITLE_NUM_PLACING] = "Num_Placing";
		
		for(int i = 0; i < BookTitle.BOOK_TITLE_SIZE_PROPERTIES; i ++) {
			this.valueProperties[i] = new String("");
		}
	}
	
	@Override
	public String[] getValueProperties() {
		return this.valueProperties;
	}

	@Override
	public void getValueProperties(String value, int index) {
		//Create statement to select data from database
		
		String stuff = "select * from Book_Title where " + this.nameProperties[index] + "=\"" + value + "\"";
		
		try {
			this.prepareStatement = this.connector.prepareStatement(stuff);
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				for(int i = 0; i < BookTitle.BOOK_TITLE_SIZE_PROPERTIES; i ++) {
					this.valueProperties[i] = this.results.getString(this.nameProperties[i]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
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
	public void insertBookTitle(String[] valueProperties) {
		//Create insert statement
		
		String stuff = "insert into Book_Title(";
		int lastIndexNonzero = 0;
		//Get index last nonzero in valueProperties array
		
		for(int i = BookTitle.BOOK_TITLE_SIZE_PROPERTIES - 1; i >= 0; i --) {
			if((!valueProperties[i].equals(null)) && (!valueProperties[i].equals(""))) {
				lastIndexNonzero = i;
				
				break;
			}
		}
		
		for(int i = 1; i < BookTitle.BOOK_TITLE_SIZE_PROPERTIES; i ++) {
			if((!valueProperties[i].equals(null)) && (!valueProperties[i].equals(""))) {
				if(i != lastIndexNonzero) {
					stuff += this.nameProperties[i] + ", ";
				}
				else {
					stuff += this.nameProperties[i] + ") ";
				}
			}
		}
		
		stuff += "values(";
		
		for(int i = 1; i < BookTitle.BOOK_TITLE_SIZE_PROPERTIES; i ++) {
			if((!valueProperties[i].equals(null)) && (!valueProperties[i].equals(""))) {
				if(i != lastIndexNonzero) {
					stuff += "'" + valueProperties[i] + "', ";
				}
				else {
					stuff += "'" + valueProperties[i] + "')";
				}
			}
		}
		//Create and insert data to database
		
		try {
			this.statement = this.connector.createStatement();
			this.statement.executeUpdate(stuff);
			this.statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateValueProperties(String value, int index) {
		//Create update statement to database
		
		String stuff = "update Book_Title set " + this.nameProperties[index] + "='" + value + "' where " + this.nameProperties[BookTitle.BOOK_TITLE_ID] + "='" + this.valueProperties[BookTitle.BOOK_TITLE_ID] + "'";
		
		try {
			this.statement = this.connector.createStatement();
			
			this.statement.executeUpdate(stuff);
			
			this.statement.close();
		}	catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean lendingBook(String bookItemId, int typeLending) {
		//Declare for some variables in this function
		
		boolean result = false;
		int numLending;
		
		if(this.isAvaiable(bookItemId) == BookTitle.BOOK_ITEM_EXIST) {
			numLending = Integer.parseInt(this.valueProperties[BookTitle.BOOK_TITLE_NUM_LENDING]);
			numLending ++;
			
			if(typeLending == BookTitle.LENDING_REGISTER) {
				this.updateValueProperties(Integer.toString(Integer.parseInt(this.valueProperties[BookTitle.BOOK_TITLE_NUM_PLACING]) - 1), BookTitle.BOOK_TITLE_NUM_PLACING);
			}
			
			this.updateValueProperties(Integer.toString(numLending), BookTitle.BOOK_TITLE_NUM_LENDING);
			
			result = true;
		}
		else {
			result = false;
		}
		
		return result;
	}

	@Override
	public boolean placingBook(String bookItemId) {
		
		this.getValueProperties(bookItemId, BookTitle.BOOK_TITLE_BI_ID);
		
		this.updateValueProperties(Integer.toString(Integer.parseInt(this.valueProperties[BookTitle.BOOK_TITLE_NUM_PLACING]) + 1), BookTitle.BOOK_TITLE_NUM_PLACING);
		
		return true;
	}

	@Override
	public int isAvaiable(String bookItemId) {
		//Declare for result variable
		
		int result = BookTitle.BOOK_ITEM_EXIST;
		int numPlacing, numLending, sumBookItem;
		
		this.valueProperties[BookTitle.BOOK_TITLE_BI_ID] = "";
		
		this.getValueProperties(bookItemId, BookTitle.BOOK_TITLE_BI_ID);
		
		if(this.valueProperties[BookTitle.BOOK_TITLE_BI_ID].equals(bookItemId)) {
			numPlacing = Integer.parseInt(this.valueProperties[BookTitle.BOOK_TITLE_NUM_PLACING]);
			numLending = Integer.parseInt(this.valueProperties[BookTitle.BOOK_TITLE_NUM_LENDING]);
			sumBookItem = Integer.parseInt(this.valueProperties[BookTitle.BOOK_TITLE_COUNT]);
			
			if((numPlacing + numLending) < sumBookItem) {
				result = BookTitle.BOOK_ITEM_EXIST;
			}
			else {
				result = BookTitle.ERROR_NOT_AVAIABLE;
			}
		}
		else {
			result = BookTitle.ERROR_NOT_EXIST;
		}
		
		return result;
	}

	@Override
	public boolean isUpdateCountBook() {
		//Create some variables in this function
		
		boolean result = false;
		
		if(Integer.parseInt(this.valueProperties[BookTitle.BOOK_TITLE_COUNT]) >= (Integer.parseInt(this.valueProperties[BookTitle.BOOK_TITLE_NUM_LENDING]) + Integer.parseInt(this.valueProperties[BookTitle.BOOK_TITLE_NUM_PLACING]))) {
			result = true;
		}
		else {
			result = false;
		}
		
		return result;
	}

	@Override
	public void payingBook(String bookItemId) {
		//Get information book title
		
		this.getValueProperties(bookItemId, BookTitle.BOOK_TITLE_BI_ID);
		//Decrease number of books by an unit
		
		this.updateValueProperties(Integer.toString(Integer.parseInt(this.getValueProperties()[BookTitle.BOOK_TITLE_NUM_LENDING]) - 1), BookTitle.BOOK_TITLE_NUM_LENDING);
	}
}
