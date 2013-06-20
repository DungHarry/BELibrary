package harry.bel.interfaces;

public interface BookTitleInterface {
	//Declare for some constants in this interfaces
	
	public static final int BOOK_TITLE_SIZE_PROPERTIES = 5;
	public static final int BOOK_TITLE_ID = 0;
	public static final int BOOK_TITLE_BI_ID = 1;
	public static final int BOOK_TITLE_COUNT = 2;
	public static final int BOOK_TITLE_NUM_LENDING = 3;
	public static final int BOOK_TITLE_NUM_PLACING = 4;
	public static final int BOOK_ITEM_EXIST = 0;
	public static final int ERROR_NOT_EXIST = 1;
	public static final int ERROR_NOT_AVAIABLE = 2;
	public static final int LENDING_REGISTER = 0;
	public static final int LENDING_NO_REGISTER = 1;
	//Declare for some methods in this interfaces
	
	public String[] getValueProperties();
	public void getValueProperties(String value, int index);
	public String[] getNameProperties();
	public void setValueProperties(String[] valueProperties);
	public void updateValueProperties(String value, int index);
	public void insertBookTitle(String[] valueProperties);
	public boolean lendingBook(String bookItemId, int typeLending);
	public boolean placingBook(String bookItemId);
	public int isAvaiable(String bookItemId);
	public boolean isUpdateCountBook();
	public void payingBook(String bookItemId);
}
