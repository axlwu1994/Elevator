import static org.junit.Assert.*;

import org.junit.Test;


public class TestEventBarrier {

	private EventBarrier bar = new EventBarrier();
	private Minstrels m1 = new Minstrels(bar);
	private Minstrels m2 = new Minstrels(bar);
	private Minstrels m3 = new Minstrels(bar);
	private Minstrels m4 = new Minstrels(bar);
	private Minstrels m5 = new Minstrels(bar);
	private Minstrels m6 = new Minstrels(bar);
	private GateKeeper gk = new GateKeeper(bar);
	
	@Test
	public void test() throws InterruptedException {
		m1.start();
		m2.start();
		m3.start();
		assertFalse(bar.getEventOccuring());
		gk.start();

		//m4 we want to get through while the gate is up
		m4.start();

		Thread.sleep(100);
		//we want these to stop at the gate because it has been lowered
		m5.start();
		m6.start();

		assertTrue(m1.getIntoCastle());
		assertTrue(m2.getIntoCastle());
		assertTrue(m3.getIntoCastle());
		assertTrue(m4.getIntoCastle());
		assertFalse(m5.getIntoCastle());
		assertFalse(m6.getIntoCastle());
		assertFalse(bar.getEventOccuring());
		
	}

}
