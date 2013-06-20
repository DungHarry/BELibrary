package harry.bel.objects;

import java.sql.*;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import org.apache.commons.io.output.*;
import harry.bel.interfaces.BookItemInterfaces;

public class BookItem implements BookItemInterfaces {
	//Declare some variables in this class
	
	private String[] nameProperties = new String[BookItem.BOOK_ITEM_SIZE_PROPERTES];
	private String[] valueProperties = new String[BookItem.BOOK_ITEM_SIZE_PROPERTES];
	private Connection connection;
	private Statement statement;
	private PreparedStatement prepareStatement;
	private ResultSet results;
	int numResultsSearch = 0;
	
	public BookItem(Connection connection) {
		this.connection = connection;
		this.initialize();
	}
	//Declare method to auto initialize some members in this class
	
	private void initialize() {
		//Initialize value of nameProperties variable
		
		this.nameProperties[BookItem.BOOK_ITEM_AUTHOR] = "Author";
		this.nameProperties[BookItem.BOOK_ITEM_CODE] = "Code";
		this.nameProperties[BookItem.BOOK_ITEM_DAY_PUBPLICATION] = "Day_Publication";
		this.nameProperties[BookItem.BOOK_ITEM_ID] = "Id";
		this.nameProperties[BookItem.BOOK_ITEM_IMAGE] = "Image";
		this.nameProperties[BookItem.BOOK_ITEM_PUBLISHER] = "Publisher";
		this.nameProperties[BookItem.BOOK_ITEM_SUMMARY] = "Summary";
		this.nameProperties[BookItem.BOOK_ITEM_TITLE] = "Title";
		this.nameProperties[BookItem.BOOK_ITEM_TYPE_EXPLAIN] = "Type_Explain";
		this.nameProperties[BookItem.BOOK_ITEM_TYPE_SYMBOL] = "Type_Symbol";
		//Initialize value of valueProperties
		
		for(int i = 0; i < BookItem.BOOK_ITEM_SIZE_PROPERTES; i ++) {
			this.valueProperties[i] = new String("");
		}
	}
	
	@Override
	public void insertBookItem(String[] valueProperties) {
		//Create insert query
		
		String stuff = "insert into Book_Item(";
		
		for(int i = 1; i < BookItem.BOOK_ITEM_SIZE_PROPERTES; i ++) {
			if(i != (BookItem.BOOK_ITEM_SIZE_PROPERTES - 1)) {
				stuff += this.nameProperties[i] + ", ";
			}
			else {
				stuff += this.nameProperties[i] + ") "; 
			}
		}
		
		stuff += "values(";
		
		for(int i = 1; i < BookItem.BOOK_ITEM_SIZE_PROPERTES; i ++) {
			if(i != (BookItem.BOOK_ITEM_SIZE_PROPERTES - 1)) {
				stuff += "\"" + valueProperties[i] + "\", ";
			}
			else {
				stuff += "\"" + valueProperties[i] + "\")";
			}
		}
		
		try {
			this.statement = this.connection.createStatement();
			
			this.statement.executeUpdate(stuff);
			
			this.statement.close();
		}	catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBookItem(String[] valueProperties) {
		//Create update query for this function
		
		String stuff = "update Book_Item set ";
		int lastIndex = 0;
		//Get index of last nonzero in parameter of this function
		
		for(int i = (BookItem.BOOK_ITEM_SIZE_PROPERTES - 1); i >= 0; i --) {
			if(!valueProperties.equals("")) {
				lastIndex = i;
				break;
			}
		}
		
		for(int i = 1; i < BookItem.BOOK_ITEM_SIZE_PROPERTES; i ++) {
			if((i != lastIndex) && (!valueProperties[i].equals(""))) {
				stuff += this.nameProperties[i] + "=\"" + valueProperties[i] + "\", ";
			}
			else {
				stuff += this.nameProperties[i] + "=\"" + valueProperties[i] + "\" ";
				
				break;
			}
		}
		//Add conditional information
		
		stuff += "where Id=\"" + this.valueProperties[BOOK_ITEM_ID] + "\"";
		//Create statement and update information of book item
		
		try {
			this.statement = this.connection.createStatement();
			
			this.statement.executeUpdate(stuff);
			
			this.statement.close();
		}	catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBookItem(String value, int index) {
		//Create statement for this update query
		
		String stuff = "update Book_Item set " + this.nameProperties[index] + "=\"" + value + "\" where Id=\"" + this.valueProperties[BOOK_ITEM_ID] + "\"";
		
		try {
			this.statement = this.connection.createStatement();
			
			this.statement.executeUpdate(stuff);
			
			this.statement.close();
		}	catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] getNameProperties() {
		return this.nameProperties;
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
	public String uploadImage(HttpServletRequest request, String fileNameSave) {
		//Report some variables in this method
		
		File file;
		String result = "";
		int maxFileSize = 50000 * 1024;
		int maxDiskSize = 50000 * 1024;
		//Get content type of request
		
		String contentType = request.getContentType();
		//Check for entype have format of multipart/form-data
		
		if(contentType.indexOf("multipart/form-data") >= 0) {
			//Create new disk file system factory
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//Set maximum file size wont stored in system
			
			factory.setSizeThreshold(maxDiskSize);
			//Set directory to store file has size is larger maxDiskSize
			
			factory.setRepository(new File(BookItem.DIRECTORY_FILE_TEMPLE));
			//Create file upload handler
			
			ServletFileUpload fileUploader = new ServletFileUpload(factory);
			//Set maximum size of file to upload
			
			fileUploader.setSizeMax(maxFileSize);
			//Get and handle to upload file
			
			try {
				//Parse request to get file item
				
				List<FileItem> fileItems = fileUploader.parseRequest(request);
				//Processed file uploaded item
				
				Iterator<FileItem> looper = fileItems.iterator();
				//Get file to upload
				if(looper.hasNext()) {
					//Create FileItem
					
					FileItem fileItem = (FileItem) looper.next();
					//Check to make sure that item isn't file field
					
					if(!fileItem.isFormField()) {
						//Get file name
						
						String fileName = fileItem.getName();
						//Create file to directory needed with name as parameter
						
						file = new File(BookItem.DIRECTORY_FILE_UPLOAD + fileNameSave + fileName.substring(fileName.lastIndexOf(".") - 1));
						
						result = BookItem.DIRECTORY_FILE_UPLOAD + fileNameSave + fileName.substring(fileName.lastIndexOf(".") - 1);
						
						fileItem.write(file);
					}
				}
			}	catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public boolean getValueInformations(String value, int typeValue) {
		//Create query to get data from Database
		
		boolean result = false;
		String stuff = "select * from Book_Item where " + this.nameProperties[typeValue] + "=\"" + value + "\"";
		
		try {
			//Prepare for select query 
			
			this.prepareStatement = this.connection.prepareStatement(stuff);
			//Execute query for statement stuff
			
			this.results = this.prepareStatement.executeQuery();
			
			while(this.results.next()) {
				for(int i = 0; i < BookItem.BOOK_ITEM_SIZE_PROPERTES; i ++) {
					this.valueProperties[i] = this.results.getString(this.nameProperties[i]);
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
	public void getValueInformations(String[] arrayValue) {
		//Create select query to database
		
		String stuff = "select * from Book_Item where ";
		for(int i = 1; i < arrayValue.length; i ++) {
			if(i != BookItem.BOOK_ITEM_SUMMARY) {
				if(i != (arrayValue.length - 1)) {
					stuff += this.nameProperties[i] + "=\"" + arrayValue[i] + "\" and ";
				}
				else {
					stuff += this.nameProperties[i] + "=\"" + arrayValue[i] + "\"";
				}
			}
		}
		
		try {
			//Create prepare statement to database
			
			this.prepareStatement = this.connection.prepareStatement(stuff);
			this.results = this.prepareStatement.executeQuery();
		
			while(this.results.next()) {
				for(int i = 0; i < BookItem.BOOK_ITEM_SIZE_PROPERTES; i ++) {
					this.valueProperties[i] = this.results.getString(this.nameProperties[i]);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Declare for function to get number of book item for key words 
	
	private String countNumResults(String keyWords) {
		//Split keyWords by space character
		
		String[] keysArray = keyWords.split(" ");
		//Create select query
		
		String stuff = "select count(Id) from Book_Item where ";
		
		for(int i = 0; i < keysArray.length; i ++) {
			if((!keysArray[i].equals("")) && (!keysArray[i].equals(null))) {
				if(i != (keysArray.length - 1)) {
					stuff += this.nameProperties[BookItem.BOOK_ITEM_TITLE] + " like \"%" + keysArray[i] + "%\" or " + this.nameProperties[BookItem.BOOK_ITEM_AUTHOR] + " like \"%" + keysArray[i] + "%\" or ";
				} else {
					stuff += this.nameProperties[BookItem.BOOK_ITEM_TITLE] + " like \"%" + keysArray[i] + "%\" or " + this.nameProperties[BookItem.BOOK_ITEM_AUTHOR] + " like \"%" + keysArray[i] + "%\"";
				}
			}
		}
		
		try {
			this.prepareStatement = this.connection.prepareStatement(stuff);
			//Execute query and get result
			
			this.results = this.prepareStatement.executeQuery();
			
			if(this.results.next()) {
				this.numResultsSearch = Integer.parseInt(this.results.getString("count(Id)"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return stuff;
	}
	
	@Override
	public String[][] searchBook(String keyWords) {
		//Declare for some variables in this function
		
		String[][] result = null;
		//Call to countNumResults
		
		int temple = 0;
		String stuff = this.countNumResults(keyWords);
		
		stuff = stuff.replace("count(Id)", "*");
		
		if(this.numResultsSearch != 0) {
			//Create two dimensions string arrays
			
			result = new String[this.numResultsSearch][BookItem.BOOK_ITEM_SIZE_PROPERTES];
			//Initialize for result array
			
			for(int i = 0; i < this.numResultsSearch; i ++) {
				for(int j = 0; j < BookItem.BOOK_ITEM_SIZE_PROPERTES; j ++) {
					result[i][j] = new String("");
				}
			}
			
			try {
				this.prepareStatement = this.connection.prepareStatement(stuff);
				//Execute query and get result
				
				this.results = this.prepareStatement.executeQuery();
				
				while(this.results.next()) {
					//Get value of properties
					
					for(int i = 0; i < BookItem.BOOK_ITEM_SIZE_PROPERTES; i ++) {
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
	public int getNumResultsSearch() {
		return this.numResultsSearch;
	}
}
