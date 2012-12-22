package blog;
import org.junit.Test;

import junit.framework.Assert;


public class TestaUsuario {

	@Test
	public void testaUsuario(){
		Usuario user1 = new Usuario("Joao");
		Usuario user2 = new Usuario("Maria");
		
		Assert.assertEquals("Joao", user1.getNome());
		Assert.assertEquals("Maria", user2.getNome());
		
		user1.setNome("Pedro");
		Assert.assertEquals("Pedro", user1.getNome());
		
		user2.setNome("Carolina");
		Assert.assertEquals("Carolina", user2.getNome());
		
	}

}
