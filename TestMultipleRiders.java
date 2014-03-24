import static org.junit.Assert.*;
import org.junit.Test;


public class TestMultipleRiders {
	
	private Building building = new Building(18, 1);
	private Elevator e = new Elevator(18, 1, 50);
	private ElevatorController ec = new ElevatorController(building, e);
	
	
	
	@Test
	public void test() {
//		
//		//CHANGE IF POSSIBLE
//		building.setElevatorController(ec);
//		e.setController(ec);
//		
//		EventBarrier upBar = new EventBarrier();
//		upBar.setFloor(3);
//		
//		Rider r1 = new Rider(building, 3, 7, upBar);
//		Rider r2 = new Rider(building, 3, 7, upBar);
//		
//		r1.start();
//		r2.start();
//
//		building.runElevatorLoop();
//		
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		assertEquals(7, r1.getCurrentFloor());
//		assertEquals(7, r1.getCurrentFloor());		
	}
	
	@Test
	public void test2 () {
		//CHANGE IF POSSIBLE
		building.setElevatorController(ec);
		e.setController(ec);
		
		EventBarrier upBar1 = new EventBarrier();
		upBar1.setFloor(2);
		
		EventBarrier upBar2 = new EventBarrier();
		upBar2.setFloor(2);
		
		Rider r1 = new Rider(building, 2, 8, upBar1);
		
		Rider r2 = new Rider(building, 2, 7, upBar2);
		
		r1.start();
		r2.start();

		building.runElevatorLoop();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(8, r1.getCurrentFloor());
		assertEquals(7, r2.getCurrentFloor());	
	}

}
