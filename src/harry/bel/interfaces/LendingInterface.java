package harry.bel.interfaces;

public interface LendingInterface {
	//Declare some constant in this interface
	
	public static final int LENDING_SIZE_PROPERTIES = 5;
	public static final int LENDING_CODE = 0;
	public static final int LENDING_USER_ID = 1;
	public static final int LENDING_BOOK_ITEM_ID = 2;
	public static final int LENDING_STATUS = 3;
	public static final int LENDING_TIME = 4;
	public static final int ERROR_OVER_15_DAYS = 1;
	public static final int ERROR_GREATER_THAN_10_BOOKS = 2;
	public static final int ERROR_PLACING_SAME_BOOK = 3;
	//Declare some functions in this interface
	
	public void getValueProperties(String value, int type);
	public void getValueProperties(String[] valueArray);
	public String[] getValueProperties();
	public void setValueProperties(String[] valueProperties);
	public void updateEachProperties(String value, int type);
	public boolean isPlacingBook(String userId, String bookItemId);
	public boolean isLendingBook(String userId, String bookItemId);
	public String[][] getEntirePlacing();
	public String[][] getEntireLending();
	public String[][] getUsersLate();
	public String[] getNameProperties();
	public void insertLendingRecord(String[] valueProperties);
	public boolean checkForLending(String userId, int typeCheck, String bookId);
	public int getNumUsersLate();
	public void deleteLendingRecord(String lendingCode);
}
