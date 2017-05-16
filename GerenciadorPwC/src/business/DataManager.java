package business;

import java.io.IOException;
import java.util.ArrayList;

import persistence.FileProcess;

public class DataManager {

	private ArrayList<APE> apes;
	private ArrayList<Relatorio> relatorios;
	private ArrayList<Contato> contatos;
	FileProcess fp;


	public DataManager(){
		this.apes = new ArrayList<>();
		this.relatorios = new ArrayList<>();
		this.contatos= new ArrayList<>();
		fp = new FileProcess();
	}


	public void adicionarAPE(APE ape) throws IOException{
		apes.add(ape);
		fp.writeAPE(ape);
	}

	public void adicionarRelatorio(Relatorio relatorio) throws IOException{ 
		relatorios.add(relatorio);
		fp.writeRelatorio(relatorio);
	}

	public void adicionarContato(Contato contato) throws IOException{
		contatos.add(contato);
		fp.writeContato(contato);
	}

	public boolean removerAPE(String numPedido) throws IOException{
		APE apeParaRemover = null;
		boolean removido = false;
		for(APE ape : apes)
			if(ape.getNumPedido().equals(numPedido)){
				apeParaRemover = ape;
				removido = true;
			}
		apes.remove(apeParaRemover);
		fp.clearTxt("APEs.txt");
		for(APE ape : apes)
			fp.writeAPE(ape);
		
		return removido;
	}

	public boolean removerRelatorio(int id) throws IOException{
		Relatorio relatorioParaRemover = null;
		boolean removido = false;
		for(Relatorio relatorio : relatorios)
			if(relatorio.getId()==id){
				relatorioParaRemover=relatorio;
				removido = true;
			}
		relatorios.remove(relatorioParaRemover);
		fp.clearTxt("Relatorios.txt");
		for(Relatorio relatorio : relatorios)
			fp.writeRelatorio(relatorio);
		
		return removido;
	}
	
	public boolean removerContato(int id) throws IOException{
		Contato contatoParaRemover = null;
		boolean removido = false;
		for(Contato contato : contatos)
			if(contato.getId()==id){
				removido = true;
				contatoParaRemover = contato;
			}
		contatos.remove(contatoParaRemover);
		fp.clearTxt("Contatos.txt");
		for(Contato contato : contatos)
			fp.writeContato(contato);
		
		return removido;
	}

	// Parametro = Fornecedor, NumBatch, NumPedido ou Data de Envio
	public ArrayList<APE> buscarApes(String parametro){
		String parametroToLower = parametro.toLowerCase();
		ArrayList<APE> apesQueAtendemOParametro = new ArrayList<>();
		for(APE ape : apes){
			String fornecedorToLower = ape.getFornecedor().toLowerCase();
			if(fornecedorToLower.contains(parametroToLower)
					|| ape.getFornecedor().equals(parametro)
					|| ape.getNumBatch().equals(parametro)
					|| ape.getNumPedido().equals(parametro))
			{
				apesQueAtendemOParametro.add(ape);
			}
		}

		return apesQueAtendemOParametro;
	}

	// Parametro = Cliente ou Data de Emissão
	public ArrayList<Relatorio> buscarRelatorios(String parametro){
		String parametroToLower = parametro.toLowerCase();
		ArrayList <Relatorio> relatoriosQueAtendemOParametro = new ArrayList<Relatorio>();
		for(Relatorio relatorio : relatorios){
			String nomeClienteToLower = relatorio.getCliente().toLowerCase();
			if(nomeClienteToLower.equals(parametroToLower)
					|| relatorio.getDataDeEmissao().equals(parametro))
			{
				relatoriosQueAtendemOParametro.add(relatorio);
			}
		}
		return relatoriosQueAtendemOParametro;
	}
	
	public Contato buscarContato (String nome){
		for(Contato contato : contatos){
			String nomeContatoAtual = contato.getNome().toLowerCase();
			String nomeProcuradoToLowerCase = nome.toLowerCase();
			if(nomeContatoAtual.equals(nomeProcuradoToLowerCase))
				return contato;
		}
	
		return null;
	}

	
	public ArrayList<APE> getApes() {
		return apes;
	}


	public ArrayList<Relatorio> getRelatorios() {
		return relatorios;
	}


	public ArrayList<Contato> getContatos() {
		return contatos;
	}


	public void load() throws IOException{
		apes.clear();
		relatorios.clear();
		contatos.clear();
		apes = fp.loadAPE();
		relatorios = fp.loadRelatorio();
		contatos = fp.loadContatos();
		}
	
}
