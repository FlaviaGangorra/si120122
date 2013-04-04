package classes;

import java.util.Random;

public class AleatoriaComRepeticao implements Recombinacao{

	@Override
	public String pegaLinhaRecombinada(String texto) {
		String[] frases = texto.split("\n");
		Random random = new Random();
		int ind = random.nextInt(texto.length()); //sera q isso ta certo
		
		return frases[ind];
	}

}
