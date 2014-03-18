import static org.junit.Assert.*;

import org.junit.Test;


public class TestEventBarrier {

	private EventBarrier bar = new EventBarrier();
	private Minstrels m1 = new Minstrels(bar);
	private Minstrels m2 = new Minstrels(bar);
	private Minstrels m3 = new Minstrels(bar);
	private Minstrels m4 = new Minstrels(bar);
	private GateKeeper gk = new GateKeeper(bar);
	
	@Test
	public void test() {
		m1.run();
		m2.run();
		m3.run();
		
		gk.run();

		assertTrue(bar.getEventOccuring());
		
		m4.run();

		assertTrue(m1.getIntoCastle());
		assertTrue(m2.getIntoCastle());
		assertTrue(m3.getIntoCastle());
		assertTrue(m4.getIntoCastle());
		assertFalse(bar.getEventOccuring());
		
	}

}
