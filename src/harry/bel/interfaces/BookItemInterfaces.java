package harry.bel.interfaces;

import javax.servlet.http.HttpServletRequest;

public interface BookItemInterfaces {
	//Declare some variables in this interface
	
	public static final int BOOK_ITEM_SIZE_PROPERTES = 10;
	public static final int BOOK_ITEM_ID = 0;
	public static final int BOOK_ITEM_TITLE = 1;
	public static final int BOOK_ITEM_AUTHOR = 2;
	public static final int BOOK_ITEM_TYPE_SYMBOL = 3;
	public static final int BOOK_ITEM_TYPE_EXPLAIN = 4;
	public static final int BOOK_ITEM_IMAGE = 5;
	public static final int BOOK_ITEM_SUMMARY = 6;
	public static final int BOOK_ITEM_DAY_PUBPLICATION = 7;
	public static final int BOOK_ITEM_CODE = 8;
	public static final int BOOK_ITEM_PUBLISHER = 9;
	public static final String DIRECTORY_FILE_UPLOAD = "/host/Programming/Ubuntu/workspace/BELibrary/WebContent/BELibrary/Images/";
	public static final String DIRECTORY_FILE_TEMPLE = "/host/Programming/Ubuntu/workspace/BELibrary/WebContent/BELibrary/Images/Temples/";
	//Declare some method in this functions
	
	public void insertBookItem(String[] valueProperties);
	public void updateBookItem(String[] valueProperties);
	public void updateBookItem(String value, int index);
	public String[] getNameProperties();
	public String[] getValueProperties();
	public void setValueProperties(String[] valueProperties);
	public boolean getValueInformations(String value, int typeValue);
	public String uploadImage(HttpServletRequest request, String fileNameSave);
	public void getValueInformations(String[] arrayValue);
	public String[][] searchBook(String keyWords);
	public int getNumResultsSearch();
}
