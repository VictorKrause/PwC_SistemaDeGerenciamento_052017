package business;

public class APE {
	
	private String dataDeEnvio;
	private String dataDeVencimento;
	private String descricao;
	private String meioDePagamento;
	private String valor;
	private String numPedido;
	private String numBatch;
	private String fornecedor;		
	
	public APE(String dataDeEnvio, String dataDeVencimento, String descricao, String meioDePagamento, String valor,
			String numPedido, String numBatch, String fornecedor) {
		this.dataDeEnvio = dataDeEnvio;
		this.dataDeVencimento = dataDeVencimento;
		this.descricao = descricao;
		this.meioDePagamento = meioDePagamento;
		this.valor = valor;
		this.numPedido = numPedido;
		this.numBatch = numBatch;
		this.fornecedor = fornecedor;
	}
	
	public String getDataDeEnvio() {
		return dataDeEnvio;
	}
	public String getDataDeVencimento() {
		return dataDeVencimento;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getMeioDePagamento() {
		return meioDePagamento;
	}
	public String getValor() {
		return valor;
	}
	public String getNumPedido() {
		return numPedido;
	}
	public String getNumBatch() {
		return numBatch;
	}
	public String getFornecedor() {
		return fornecedor;
	}

	@Override
	public String toString() {
		return dataDeEnvio + ";" + dataDeVencimento + ";" + descricao + ";" + meioDePagamento + ";" + valor + ";"
				+ numPedido + ";" + numBatch + ";" + fornecedor;
	}
	
	

}
