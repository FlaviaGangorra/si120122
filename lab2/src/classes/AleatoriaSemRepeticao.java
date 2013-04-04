package classes;

import java.util.Random;

public class AleatoriaSemRepeticao implements Recombinacao{

	@Override
	public String pegaLinhaRecombinada(String texto) {
		String novaFrase = "";
		String[] palavras = texto.split(" ");
		Random random = new Random();
		do {
			for (int i = 0; i < 10; i++) {
				int ind = random.nextInt(palavras.length);
				novaFrase += palavras[ind] + " ";
			}
			
		} while (texto.contains(novaFrase.trim()));
		return novaFrase.trim();
	}

}
