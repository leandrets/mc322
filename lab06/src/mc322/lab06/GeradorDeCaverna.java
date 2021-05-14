package mc322.lab06;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class GeradorDeCaverna {
	
	public boolean ouroNasPontas(List<String> conteudo) {
		return (conteudo.get(2).equals("O") ||conteudo.get(11).equals("O") || conteudo.get(14).equals("O"));
	}
	
	public List<String> gerarConteudo() {
		Random random = new Random();
		int numeroDeBuracos = random.nextInt(1) + 2;
		List<String> conteudo = new ArrayList<String>();
		for (int i = 0; i < numeroDeBuracos; i++) {
			conteudo.add("B");
		}
		conteudo.add("W");
		conteudo.add("O");
	    for (int i = 0; i < 13 - numeroDeBuracos; i++) {
	    	conteudo.add("-");
	    }
	    Collections.shuffle(conteudo);
	    while (ouroNasPontas(conteudo)) {
	    	 Collections.shuffle(conteudo);
	    }
	    return conteudo;
	}
	
	public void escreverConteudo(String path) {
		List<String> conteudo = gerarConteudo();
		File arquivo = new File(path);
		try { 
			if(!arquivo.exists()) {
				arquivo.createNewFile();
			}
			FileWriter fw = new FileWriter(arquivo, false);
			BufferedWriter bw = new BufferedWriter(fw);
			
			int k = 0;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					String linha = Integer.toString(i+1) + ":" + Integer.toString(j+1) + ",";
			        if (i == 0 && j == 0) {
			            linha += "P";
			        }
			        else {
			        	linha += conteudo.get(k);
			            k++;
			        }
			        linha += "\n";
			        bw.write(linha);
				}
			}
			bw.close();
			fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
