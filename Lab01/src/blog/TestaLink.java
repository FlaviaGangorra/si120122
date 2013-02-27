package blog;
import junit.framework.Assert;


import org.junit.Test;

public class TestaLink {
	
	@Test
	public void testaLink(){
		
		Link l1 = new Link("http://youtube.com");
		Link l2 = new Link("https://google.com");

		
		Assert.assertEquals("http://youtube.com", l1.getEndereco());
		Assert.assertEquals("https://google.com", l2.getEndereco());
		
		l1.setEndereco("http://facebook.com");
		Assert.assertEquals("http://facebook.com", l1.getEndereco());
		
		l2.setEndereco("globo.com");
		//nao muda porque o endereco web eh invalido
		Assert.assertEquals("https://google.com", l2.getEndereco());
		
		Assert.assertEquals(false, new Link("globo.com").isLinkValido());
		Assert.assertEquals(true, new Link("http://fgdsgfhdgfgsdj").isLinkValido());
	}

}
