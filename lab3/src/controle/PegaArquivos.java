package controle;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modelo.Arquivo;
import modelo.LeArquivo;

/**
 * Thread responsavel por pegar os arquivos de um dado diretorio (e seus
 * subdiretorios), verificar dentre esses arquivos quais possuem a extensao
 * '.java' e inicializar a thread de leitura para estes
 * 
 * @author
 * 
 */
public class PegaArquivos extends Thread {
	private LeArquivo leitor;
	private String diretorio;
	private Map<String, Integer> resultado = null;
	private int totalDeArqProcessados = 0;
	private int totalDeArqEncontrados = 0;
	private static List<File> arquivos = new ArrayList<File>();

	Arquivo arquivo = new Arquivo();

	/**
	 * Instancia a thread
	 * 
	 * @param diretorio
	 *            - diretorio raiz o qual quero fazer a pesquisa.
	 */
	public PegaArquivos(String diretorio) {
		this.diretorio = diretorio;
	}

	/**
	 * Inicia a execucao da thread
	 */
	public void run() {

		List<File> listarquivos = getArqDiretoriosESubDiretorios(diretorio);
		File[] arquivos = converteListParaArray(listarquivos);
		if (arquivos.length > 0) {
			for (int i = 0; i < arquivos.length; i++) {
				File arq = arquivos[i];

				if (arq.getName().endsWith(".java")) {

					totalDeArqEncontrados++;

					arquivo.putArquivo(arq.getPath());
					leitor = new LeArquivo(arquivo);
					leitor.start();
					try {
						leitor.join();// esperar a thread terminar de executar
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					resultado = leitor.getResultado();
					totalDeArqProcessados++;
				}
			}
		}
	}

	/**
	 * Converte a list que contem todos os arquivos encontrados no diretorio e
	 * em seus subdiretorios em um array com estes mesmos arquivos
	 * 
	 * @param listarquivos
	 * @return
	 */
	private File[] converteListParaArray(List<File> listarquivos) {
		int tamanhoTotal = listarquivos.size();
		File[] arrayRetorno = new File[tamanhoTotal];
		for (int i = 0; i < tamanhoTotal; i++) {
			arrayRetorno[i] = listarquivos.get(i);
		}
		return arrayRetorno;
	}

	/**
	 * Retorna uma list contendo todos os arquivos encontrados no diretorio
	 * passado e em seus subdiretorios
	 * 
	 * @param diretorio2
	 *            - diretorio onde ocorrera a busca inicial
	 * @return - lista contendo todos os arquivos encontrados no diretorio
	 *         passado e em seus subdiretorios
	 */
	private List<File> getArqDiretoriosESubDiretorios(String diretorio2) {
		File dir = new File(diretorio2);
		File[] conteudos = dir.listFiles(); // pego tudo o q tem no diretorio

		for (File file : conteudos) {
			if (file.isDirectory()) {
				System.out.println("eh diretorio: " + file.getName());
				getArqDiretoriosESubDiretorios(file.getPath());
			} else {
				if (!arquivos.contains(file)) {
					arquivos.add(file);
				}
			}
		}
		return arquivos;
	}

	/**
	 * Retorna o resultado da leitura do arquivo.
	 * 
	 * @return - map contendo as palavras-chave e sua respectiva quantidade nos
	 *         documentos
	 */
	public Map<String, Integer> getResultado() {
		return resultado;
	}

	/**
	 * Retorna o total de arquivos encontrados no diretorio, e seus respectivos
	 * subdiretorios.
	 * 
	 * @return
	 */
	public int totalDeArquivosEncontrados() {
		return totalDeArqEncontrados;
	}

	/**
	 * Retorna o total de arquivos processados no diretorio, e seus respectivos
	 * subdiretorios.
	 * 
	 * @return
	 */
	public int totalDearquivosProcessados() {
		return totalDeArqProcessados;
	}
}
