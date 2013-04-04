package classes;

public class Inversa implements Recombinacao{

	private int ultimaLinhaRepetida = -1; 
	
	@Override
	public String pegaLinhaRecombinada(String texto) {
		String retorno = "";
		if(ultimaLinhaRepetida != 0){ //verifica se ja foram recombinadas todas as linhas
			String[] frases = texto.split("\n");
			ultimaLinhaRepetida = frases.length;
			retorno = frases[ultimaLinhaRepetida];
			ultimaLinhaRepetida--;
		}
		return retorno;
	}

}
