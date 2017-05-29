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
			if(nomeClienteToLower.contains(parametroToLower)
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

	public APE buscarParaEditarAPE(String numPedido){
		APE apeParaEditar = null;	
		for(APE ape : apes)
			if(ape.getNumPedido().equals(numPedido))
				apeParaEditar=ape;

		try {
			removerAPE(numPedido);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return apeParaEditar;
	}
	
	public Relatorio buscarParaEditarRelatorio(int id){
		Relatorio relatorioParaEditar = null;
		for(Relatorio relatorio : relatorios)
			if(relatorio.getId()==id)
				relatorioParaEditar=relatorio;
		
		try{removerRelatorio(id);}
		catch(IOException e){e.printStackTrace();}
		
		return relatorioParaEditar;
		
	}

	//1 para relatorio
	//2 para contato
	public int setId(int i) throws IOException{
		load();
		int id = 0;
		if(i<1||i>2)
			return 0;	
		else

			if (i==1){
				if(relatorios.size()>0)// IF para primeiro caso
					for(Relatorio relat : relatorios)
						id=relat.getId();
				else return 1;		
			}		
			else{
				if(contatos.size()>0) // IF para primeiro caso
					for(Contato cont : contatos)
						id=cont.getId();
				else return 1;
			}

		return id+1;
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
