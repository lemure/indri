package br.com.indridoctor.mysqlconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlConnector {
	
	public Connection getConnection(){  
        
		Connection con = null;
		
		try{
        	//Para esse código funcionar no servidor, precisa colocar o driver jdbc no classLoader do mesmo (commonLibs)
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost/indri_doctor?user=root&password=root");  
        }
        catch(ClassNotFoundException ex){  
        	System.out.println(ex.getMessage());
        }
        catch(SQLException ex){  
        	System.out.println(ex.getMessage());
        }  
		
		return con;
    }  

}
