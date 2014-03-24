import static org.junit.Assert.*;

import org.junit.Test;


public class TesElevator {
	
	private Building building = new Building(10, 1);
	private Rider r1 = new Rider(building, 1, 2);
	private Elevator e = new Elevator(10, 1, 50);
	private ElevatorController ec = new ElevatorController(building, e);
	
	
	
	@Test
	public void test() {
		
		//CHANGE IF POSSIBLE
		building.setElevatorController(ec);
		e.setController(ec);
		
		EventBarrier eb1 = new EventBarrier();
		eb1.setFloor(1);
		r1.updateEventBarrier(eb1);
		r1.start();
		r1.run();
		assertEquals(2, r1.getDestinationFloor());
		building.runElevatorLoop();
		
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
