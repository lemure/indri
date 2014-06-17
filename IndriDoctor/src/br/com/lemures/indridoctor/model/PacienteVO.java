package br.com.lemures.indridoctor.model;

public class PacienteVO  implements java.io.Serializable {
    
	private static final long serialVersionUID = 132172785806606384L;

	private Integer id;
	private String email;
    private String login;
    private String nome;
    private String senha;

    
  //Construtor
    
    public PacienteVO() {
    }

    public PacienteVO(Integer id, String email, String login, String nome, String senha) {
    	this.id = id;   
    	this.email = email;
    	this.login = login;
    	this.nome = nome;
    	this.senha = senha;
    }

    
    //Getters e Setters
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    


   
}
