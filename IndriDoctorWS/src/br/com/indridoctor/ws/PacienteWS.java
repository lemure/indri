package br.com.indridoctor.ws;

import br.com.indridoctor.facade.PacienteFacade;
import br.com.indridoctor.vo.PacienteVO;

public class PacienteWS {
	
	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 */
	public PacienteVO fazerLogin(String login, String senha){
		return PacienteFacade.fazerLogin(login, senha);
	}
}