import classes.Sistema;


public class SistemaBean {
	String mensagem = "";
	
	Sistema sistema = new Sistema();
	
	public String cadastrarTexto(String texto){
		boolean foiCadastrado = sistema.cadastrarNovoTexto(texto);
		if (foiCadastrado) {
			mensagem = "Seu texto foi cadsatrado com sucesso!";
		}
		return mensagem;
	}

}
