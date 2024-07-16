package bank;
import java.sql.*;
public class ConnectionFactory {
	//Instance variable
	Connection con;
	Statement stmt;
	
	
	
	
	
	public  ConnectionFactory() {
		try {
			//Loading the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the Connection with database
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagement","root","Akm@12345");
			stmt=con.createStatement();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		}
	}
	

