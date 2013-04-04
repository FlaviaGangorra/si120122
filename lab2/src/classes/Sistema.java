package classes;

public class Sistema {
	
	ManipuladorDeDados dados = new ManipuladorDeDados();
	
	public boolean cadastrarNovoTexto(String texto){
		return dados.gravaNovoTexto(texto);
	}

	public void recombinarTexto(String texto, Recombinacao rec){
		dados.recombinarTexto(texto, rec);
	}
	
	public String adicionaLinhaRecombinada(){
		return dados.adicionaLinhaRecombinada();
	}
}
