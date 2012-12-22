package blog;

public class Link {
	
	private String endereco;
	
	public Link(String enderecoWeb){
		if(enderecoValido(enderecoWeb)){
			this.endereco = enderecoWeb;
			
		}
		
	}

	private boolean enderecoValido(String endereco) {
		return endereco.contains("http://") || endereco.contains("https://");
		
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		if (new Link(endereco).isLinkValido()){
			this.endereco = endereco;
		}
	}
	
	
	public boolean isLinkValido(){
		return this.getEndereco().contains("http://") || this.getEndereco().contains("https://");
		
	}
	
//	@Override
//	public String toString() {
//		
//	}

}
