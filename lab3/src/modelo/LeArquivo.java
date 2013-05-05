package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.StyledEditorKit.ForegroundAction;

/**
 * Thread responsavel pela leitura do arquivo passado como parametro ao
 * construtor
 * 
 * @author
 * 
 */
public class LeArquivo extends Thread {
	private Arquivo arquivo;
	private KeyWords keyWords = new KeyWords();
	private static Map<String, Integer> contador = new HashMap<String, Integer>();

	/**
	 * Instancia a Thread
	 * 
	 * @param arq
	 *            - arquivo a ser lido
	 */
	public LeArquivo(Arquivo arq) {
		arquivo = arq;
	}

	/**
	 * Inicia a execucao da thread
	 */
	public void run() {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(arquivo.getArquivo()));
		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel abrir o arquivo.");
		}

		try {
			while (br.ready()) {
				String linha = br.readLine();
				String[] palavras = linha.split(" ");
				for (int i = 0; i < palavras.length; i++) {
					String palavra = palavras[i].trim();
					if (keyWords.isKeyWord(palavra)) {
						if (contador.containsKey(palavra)) {
							Integer qtd = contador.get(palavra);
							contador.put(palavra, new Integer(qtd + 1));
						} else {
							if (palavra != null) {
								contador.put(palavra, new Integer(1));
							}
						}
					}
				}

			}

		} catch (IOException e) {
			System.out.println("Nao foi possivel ler o arquivo.");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Retorna o resultado da leitura
	 * 
	 * @return - map contendo as palavras-chave encontradas e sua quantidade
	 */
	public Map<String, Integer> getResultado() {
		return contador;
	}

}
