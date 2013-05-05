package modelo;

public class Arquivo {
	private String arquivo;
	private boolean arquivoAvaliado = false;
	private static Arquivo instance = null;
	
	public synchronized void putArquivo(String caminhoArquivo){
		
		
		while (arquivoAvaliado == true) {

			try {

				wait();

			} catch (InterruptedException e) { }

		}

		this.arquivo = caminhoArquivo;

		arquivoAvaliado = true;

		notifyAll();
		
	}
	
	public synchronized String getArquivo(){
		
		while (arquivoAvaliado == false) {
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		arquivoAvaliado = false;
		notifyAll();
		return this.arquivo;
	}
	
	public boolean arquivoEhAvaliado(){
		return arquivoAvaliado;
	}
	
	
	public static Arquivo getInstance(){
		if (instance == null) {
			instance = new Arquivo();
		}return instance;
	}
}
