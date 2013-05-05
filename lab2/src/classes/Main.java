package classes;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sistema sistema = new Sistema();
		Recombinacao rec = new Inversa();
	//	rec.pegaLinhaRecombinada("1 2 3 4 5 6 7 8 9 10 11");
		String texto = "uma/n duas/n tres/n quatro/n cinco";
		sistema.cadastrarNovoTexto(texto);
		
		sistema.recombinarTexto(texto, rec);
		sistema.adicionaLinhaRecombinada();
		
		System.out.println(sistema.getTexto());
		
		//mp.leArquivo("uma duas tres quatro cinco ceis sete oito nove dez onze doze");
		
		//String texto = "uma duas tres quatro cinco ceis sete oito nove dez onze doze";
		sistema.cadastrarNovoTexto(texto);
		
	//	sistema.recombinarTexto(texto, rec);

	}

}
