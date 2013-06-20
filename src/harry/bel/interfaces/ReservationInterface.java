package harry.bel.interfaces;

public interface ReservationInterface {
	//Report some constant in this function
	
	public static final int RESERVATION_SIZE_PROPERTIES = 6;
	public static final int RESERVATION_Id = 0;
	public static final int RESERVATION_USER_ID = 1;
	public static final int RESERVATION_BOOK_TITLE_ID = 2;
	public static final int RESERVATION_NOTE = 3;
	public static final int RESERVATION_TIME_BEGIN = 4;
	public static final int RESERVATION_TIME_FINISH = 5;
	//Declare some functions in this interface
	
	public String[][] getByEachProperty(String value, int type);
	public String[] getValueProperties();
	public void getValueProperties(String[] valueProperties);
	public void setValueProperties(String[] valueProperties);
	public void setEachProperty(String value, int type);
	public void insertReservation(String[] valueProperties);
	public String[] getNameProperties();
	public boolean isInfringement();
}
