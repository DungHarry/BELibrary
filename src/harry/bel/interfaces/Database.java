package harry.bel.interfaces;

public interface Database {
	//Report for some variable in this interface
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/";
	public String defaultUser = "root";
	public String defaultPass = "dungkstn";
	public String defaultDatabase = "BELibrary?useUnicode=true&characterEncoding=UTF-8";
	
	public boolean getConnection();
	public boolean getConnection(String databaseName, String userName, String userPass);
}
