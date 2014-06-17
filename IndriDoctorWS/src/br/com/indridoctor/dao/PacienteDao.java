package br.com.indridoctor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.indridoctor.mysqlconnector.MySqlConnector;
import br.com.indridoctor.vo.PacienteVO;

public class PacienteDao {
	
	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 */
	public PacienteVO recuperarPaciente(String login, String senha){
		
		Connection con = new MySqlConnector().getConnection();
		PacienteVO pvo = null;
		
		try{
			
			String sql = "select id, nome, email, login , senha from paciente where login = ? and senha = ?";
			PreparedStatement stmt = con.prepareStatement(sql);  
				stmt.setString(1, login);
				stmt.setString(2, senha);
            
			ResultSet rs = stmt.executeQuery();  
	  
	        if (rs.next()) {  
	        
	        	 pvo = new PacienteVO();
		        	pvo.setId(rs.getInt(1));
		        	pvo.setNome(rs.getString(2));
		        	pvo.setEmail(rs.getString(3));
		        	pvo.setLogin(rs.getString(4));
		        	pvo.setSenha(rs.getString(5));
	        }  
	  
	        rs.close();  
	        stmt.close(); 
	        con.close();
	            
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return pvo;
		
	}

}
