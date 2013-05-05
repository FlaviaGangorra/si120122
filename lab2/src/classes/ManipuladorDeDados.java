package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ManipuladorDeDados {

	private Recombinacao rec = null;
	private String nomeTexto = "";
	private String textoLido = "";
	
	public boolean gravaNovoTexto(String texto) {
		boolean foiGravado = false;
		FileWriter arquivo;  
        String nomeDoTxto = pegaNomeDoTexto(texto);
        try {  
            arquivo = new FileWriter(new File(nomeDoTxto));  
            arquivo.write(texto);  
            arquivo.close();  
            foiGravado = true;
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		return foiGravado;
	}

	private String pegaNomeDoTexto(String texto) {
		String nome = "";
		String[] palavras = texto.split(" "); 
		if (palavras.length >= 12) {
			for (int i = 0; i < 12; i++) {
				nome += palavras[i] + " ";
			}
		}else{
			for (int i = 0; i < palavras.length; i++) {
				nome += palavras[i] + " ";
			}
		}
		
		return nome.trim();
	}

	public String adicionaLinhaRecombinada() {
		String texto = leArquivo(nomeTexto);
		String linhaNova = pegaLinhaRecombinada(texto);
		adicionaNoArquivo(nomeTexto, linhaNova);
		
		return leArquivo(nomeTexto);//pra mostrar na interface ao usuario a nova linha add
	}
	
	
	private void adicionaNoArquivo(String nomeTexto, String linhaNova) {
		try {
			FileWriter arq = new FileWriter(nomeTexto, true);
			arq.write(linhaNova);
			arq.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	private String pegaLinhaRecombinada(String texto) {
		return rec.pegaLinhaRecombinada(texto);
		
	}

	private String leArquivo(String nomeTexto) {
		
		String textoLido = "";
		try {
		      FileReader arq = new FileReader(nomeTexto);
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String linha = lerArq.readLine();
		      while (linha != null) {
				textoLido += linha;
				
		      }
		      arq.close();
		}catch (IOException e) {  
            e.printStackTrace();  
        }
		
		this.textoLido = textoLido;
		return textoLido;
	}

	public void recombinarTexto(String nomeTexto, Recombinacao rec){
		this.rec = rec;
		this.nomeTexto = nomeTexto;
	}

	public String getTexto(){
		return textoLido;
	}
}
