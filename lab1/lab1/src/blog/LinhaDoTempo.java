package blog;
import blog.Link;
import java.util.LinkedList;
public class LinhaDoTempo {

	//lista de links
	private LinkedList<Link> links;
	
	
	public LinhaDoTempo(){
		links = new LinkedList<Link>();
		
	}

	public LinkedList<Link> getLinks() {
		return links;
	}

//	public void setLinks(LinkedList<Link> links) {
//		this.links = links;
//	}
	
	public boolean addLink(Link link){
		if(link.isLinkValido()){
			links.add(link);
			return true;
		}return false;
	}
	
	
	@Override
	public String toString() {
		String retorno;
		if(links.size() > 0){
			retorno = "[";
			for(Link l1 : links){
				retorno += l1.getEndereco() + ", ";
			}
			
			retorno = retiraUltimaVirgula(retorno) + "]";
		}else{retorno = "[]";}
		return retorno;
	}
	
	private String retiraUltimaVirgula(String str){
		return str.subSequence(0, str.lastIndexOf(",")) + "";
	}
	

}
