package visao;
import java.io.File;
import java.io.FileFilter;
import java.util.Map;
import java.util.Scanner;

import controle.PegaArquivos;


public class InterfaceTexto {
	
	
	private static Scanner sc;

	public static void main(String[] args) {
		PegaArquivos arquivosDoDiretorio;
		
		
		String entrada = "";
		System.out.println("Para encerrar digite 'Sair' a qualquer momento.");
		
		do {
			
			sc = new Scanner(System.in);
			System.out.println("Digite um diretorio: ");
			entrada = sc.next();
			if (!entrada.equalsIgnoreCase("sair")) {
				arquivosDoDiretorio = new PegaArquivos(entrada);
				System.out.println("Analisar? Sim ou Nao");
				entrada = sc.next();
				if (entrada.equalsIgnoreCase("sim")) {
					arquivosDoDiretorio.start();
					try {
						arquivosDoDiretorio.join();//esperar a thread terminar de executar
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Map<String, Integer>result = arquivosDoDiretorio.getResultado();
					for (int i = 0; i < result.size(); i++) {
						System.out.println(result.toString());
					}
					
				}
			}
			
			
			
		} while (!entrada.equalsIgnoreCase("sair"));
	}
	
}
