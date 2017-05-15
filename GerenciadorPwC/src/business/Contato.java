package business;

public class Contato {

	private int id;
	private String nome;
	private String tipoDeServico;
	private String telefone;
	private String email;
	private String infoAdicional;

	public Contato(int id, String nome, String tipoDeServico, String telefone, String email, String infoAdicional) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipoDeServico = tipoDeServico;
		this.telefone = telefone;
		this.email = email;
		this.infoAdicional = infoAdicional;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getTipoDeServico() {
		return tipoDeServico;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getEmail() {
		return email;
	}
	public String getInfoAdicional() {
		return infoAdicional;
	}

	@Override
	public String toString() {
		return id + "," + nome + "," + tipoDeServico + "," + telefone + "," + email + "," + infoAdicional;
	}



}
