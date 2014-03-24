import static org.junit.Assert.*;
import org.junit.Test;


public class TestMultipleRiders {
	
	private Building building = new Building(18, 1);
	private Elevator e = new Elevator(18, 1, 5);
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
		upBar1.setFloor(0);
		
		EventBarrier upBar2 = new EventBarrier();
		upBar2.setFloor(1);
		
		EventBarrier upBar3 = new EventBarrier();
		upBar3.setFloor(3);
		
		EventBarrier upBar4 = new EventBarrier();
		upBar4.setFloor(4);
		
		EventBarrier upBar5 = new EventBarrier();
		upBar5.setFloor(5);
		
		EventBarrier upBar6 = new EventBarrier();
		upBar6.setFloor(6);
		
		Rider r1 = new Rider(building, 0, 18, upBar1);
		Rider r2 = new Rider(building, 1, 17, upBar2);
		Rider r3 = new Rider(building, 3, 16, upBar3);
		Rider r4 = new Rider(building, 4, 15, upBar4);
		Rider r5 = new Rider(building, 5, 14, upBar5);
		Rider r6 = new Rider(building, 6, 13, upBar6);
		
		r1.start();
		r2.start();
		r3.start();
		r4.start();
		r5.start();
		r6.start();

		building.runElevatorLoop();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(18, r1.getCurrentFloor());
		assertEquals(17, r2.getCurrentFloor());	
		assertEquals(16, r3.getCurrentFloor());
		assertEquals(15, r4.getCurrentFloor());	
		assertEquals(14, r5.getCurrentFloor());
		assertEquals(13, r6.getCurrentFloor());	
	}

}
