import static org.junit.Assert.*;

import org.junit.Test;


public class TesElevator {
	
	private Building building = new Building(10, 1);
	private Rider r1 = new Rider(building, 0, 2);
	private Elevator e = new Elevator(10, 1, 50);
	private ElevatorController ec= new ElevatorController(building, e);
	
	
	
	@Test
	public void test() {
		r1.start();
		r1.run();
		assertEquals(2, r1.getDestinationFloor());
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(2, r1.getCurrentFloor());
		assertEquals(e.getDirectionStatus(), Direction.STAGNANT);
		
		
		
	}

}
