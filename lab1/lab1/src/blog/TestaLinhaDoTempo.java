package blog;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class TestaLinhaDoTempo {
	
	@Test
	public void testaLinhaDoTempo(){
		LinkedList<Link> list = new LinkedList<Link>();
		LinhaDoTempo line1 =  new LinhaDoTempo();
		LinhaDoTempo line2 =  new LinhaDoTempo();
		
		Link l1 = new Link("http://fsfsfs");
		Link l2 = new Link("http://gmail.com");
		
		Assert.assertEquals(list, line1.getLinks());
		Assert.assertEquals(list, line2.getLinks());
		
		line1.addLink(l1);
		Assert.assertEquals("[http://fsfsfs]", line1.toString());
		line1.addLink(l2);
		Assert.assertEquals("[http://fsfsfs, http://gmail.com]", line1.toString());
		line1.addLink(l1);
		Assert.assertEquals("[http://fsfsfs, http://gmail.com, http://fsfsfs]", line1.toString());
		
		
		line2.addLink(l2);
		Assert.assertEquals("[http://gmail.com]", line2.toString());
	}
	
	

}
