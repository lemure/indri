package br.com.indridoctor.facade;

import br.com.indridoctor.dao.PacienteDao;
import br.com.indridoctor.vo.PacienteVO;

public abstract class PacienteFacade {
	
	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 */
	public static PacienteVO fazerLogin(String login, String senha){
		PacienteDao dao = new PacienteDao();
		return dao.recuperarPaciente(login, senha);
	}
	
	
	
	
}