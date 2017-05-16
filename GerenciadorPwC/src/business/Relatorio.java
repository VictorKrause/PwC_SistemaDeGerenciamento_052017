package business;

public class Relatorio {

	private int id;
	private String cliente;
	private String solicitante;
	private String descricao;
	private String entreguePara;
	private String dataDeEmissao;
	private String qtdVias;
	
	
	
	
	public Relatorio(int id, String cliente, String solicitante, String descricao, String entreguePara,
			String dataDeEmissao, String qtdVias ) {
		this.qtdVias = qtdVias;
		this.cliente = cliente;
		this.solicitante = solicitante;
		this.descricao = descricao;
		this.entreguePara = entreguePara;
		this.dataDeEmissao = dataDeEmissao;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public String vias(){
		return qtdVias;
	}
	public String getCliente() {
		return cliente;
	}
	public String getSolicitante() {
		return solicitante;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getEntreguePara() {
		return entreguePara;
	}
	public String getDataDeEmissao() {
		return dataDeEmissao;
	}
	
	@Override
	public String toString() {
		return id + ";" + cliente + ";" + solicitante + ";" + descricao + ";" + entreguePara + ";" + dataDeEmissao + 
				";" + qtdVias;
	}
	
	
}
