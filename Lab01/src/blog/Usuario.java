package blog;
import blog.LinhaDoTempo;

public class Usuario {

	private String nome;
	private LinhaDoTempo linha;
	
	public Usuario(String nome){
		this.setNome(nome);
		this.setLinha(new LinhaDoTempo());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LinhaDoTempo getLinha() {
		return linha;
	}

	public void setLinha(LinhaDoTempo linha) {
		this.linha = linha;
	}
	
	public String mostraLinha(){
		return linha.toString();
	}
	
	public void addLink(Link link){
		if(link != null){
			linha.addLink(link);
		}
	}

}
