package maven.activities;

import java.sql.Connection;
import java.sql.DriverManager;


public class m5_activity2 
{
	
	//Database credentials
	private static final String URL = "jdbc:postgresql://localhost:5432/training_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
		
    public static void main( String[] args )
    {
    	Connection connection = null;        
        try {
        	
        //Connection
        	connection = DriverManager.getConnection(URL, USER, PASSWORD);
        	System.out.println("Connected Successfully");
        	

    }catch(Exception e) {
    	e.printStackTrace();
    }
}
}
