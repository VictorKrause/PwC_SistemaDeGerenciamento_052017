package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import business.*;

public class FileProcess {

	public void writeAPE(APE ape) throws IOException{
		
		FileWriter writer = new FileWriter("APEs.txt",true);
		String line = ape.toString();
		writer.write(line);
		writer.write("\n");
		writer.flush();
		writer.close();

	}

	public ArrayList<APE> loadAPE() throws IOException{

		ArrayList<APE> apesAntigas = new ArrayList<APE>();
		// Leitura do arquivo
		File file = new File("APEs.txt");
		Scanner reader = new Scanner(file);
		// Variaveis iniciais
		String line;

		while(reader.hasNext()){
			line = reader.nextLine();
			String[] lineSplitada = line.split(";");
			apesAntigas.add(new APE(lineSplitada[0], lineSplitada[1], lineSplitada[2], 
					lineSplitada[3], lineSplitada[4], lineSplitada[5], lineSplitada[6], lineSplitada[7]));
		}
		return apesAntigas;	
	}
	
	public void writeRelatorio(Relatorio relatorio) throws IOException{
		FileWriter writer = new FileWriter("Relatorios.txt", true);
		String line = relatorio.toString();
		writer.write(line);
		writer.write("\n");
		writer.flush();
		writer.close();
	}
	
	public ArrayList<Relatorio> loadRelatorio() throws IOException{
		ArrayList<Relatorio> relatoriosAntigos = new ArrayList<Relatorio>();
		File file = new File("Relatorios.txt");
		Scanner reader = new Scanner(file);
		String line;
		
		while(reader.hasNext()){
			line = reader.nextLine();
			String [] lineSplitada = line.split(";");
			relatoriosAntigos.add(new Relatorio(Integer.parseInt(lineSplitada[0]), lineSplitada[1], lineSplitada[2], 
					lineSplitada[3], lineSplitada[4], lineSplitada[5], lineSplitada[6]));
		}
		return relatoriosAntigos;
		
	}

	public void writeContato(Contato contato) throws IOException{
		File file = new File("Contatos.txt");
		FileWriter writer = new FileWriter(file, true);
		String line = contato.toString();
		writer.write(line);
		writer.write("\n");
		writer.flush();
		writer.close();
	}
	
	public ArrayList<Contato> loadContatos() throws IOException{
		ArrayList<Contato> contatosAntigos = new ArrayList<Contato>();
		File file = new File("Contatos.txt");
		Scanner reader = new Scanner(file);
		String line;
		
		while(reader.hasNext()){
			line = reader.nextLine();
			String [] lineSplitada = line.split(";");
			contatosAntigos.add(new Contato(Integer.parseInt(lineSplitada[0]), lineSplitada[1], lineSplitada[2], 
					lineSplitada[3], lineSplitada[4], lineSplitada[5]));
		}
		return contatosAntigos;
	}

	public void clearTxt(String nomeArquivo) throws IOException{
	    FileWriter out = new FileWriter(nomeArquivo);
        out.write("");
        out.flush();
        out.close();
	}
}

