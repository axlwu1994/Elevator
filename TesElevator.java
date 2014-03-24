import static org.junit.Assert.*;
import org.junit.Test;


public class TesElevator {
	
	private Building building = new Building(18, 1);
	private Elevator e = new Elevator(18, 1, 50);
	private ElevatorController ec = new ElevatorController(building, e);
	
	
	
	@Test
	public void test() {
		
		//CHANGE IF POSSIBLE
		building.setElevatorController(ec);
		e.setController(ec);
		
		EventBarrier ebOn = new EventBarrier();
		ebOn.setFloor(3);
		Rider r1 = new Rider(building, 3, 7, ebOn);
				
		r1.start();
		//assertEquals(7, r1.getDestinationFloor());
		building.runElevatorLoop();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(r1.getCurrentFloor());
		assertEquals(7, r1.getCurrentFloor());
		//assertEquals(e.getDirectionStatus(), Direction.STAGNANT);
		
		
		
	}

}
