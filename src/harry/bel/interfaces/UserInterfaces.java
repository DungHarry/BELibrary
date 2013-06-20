package harry.bel.interfaces;

public interface UserInterfaces {
	//Declare constants for User
	
	public static final int SIZE_USER_PROPERTIES = 11;
	public static final int USER_ID = 0;
	public static final int USER_NAME = 1;
	public static final int USER_PASS = 2;
	public static final int USER_TIME_BEGIN = 3;
	public static final int USER_EMAIL = 4;
	public static final int USER_ADDRESS = 5;
	public static final int USER_TELEPHONE = 6;
	public static final int USER_GENDER = 7;
	public static final int USER_BIRTHDAY = 8;
	public static final int USER_JOB = 9;
	public static final int ADMIN_ADD_INFS = 10;
	
	public boolean checkUser(String userName, String userPassword);
	public boolean updateInformations(String[] newValues);
	public boolean updateInformations(String value, int type);
	public boolean updateAdditionalInformationLibrarian(String value);
	public void getInformations(String userName, String userPassword);
	public void getInformations(String Id);
	public boolean getFlagAdmin();
	public void setNameProperties(String[] nameProperties);
	public String[] getNameProperties();
	public void setValueProperties(String[] valueProperties);
	public String[] getValueProperties();
	public void insertUser(String[] informations);
	public void setFlag(boolean flag);
	public boolean existAccount(String userName);
}