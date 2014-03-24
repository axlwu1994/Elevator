import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;


public class TestMultipleRiders {
	
	private Building building = new Building(18, 1);
	private Elevator e = new Elevator(18, 1, 5);
	private Elevator e2 = new Elevator(18, 2, 5);
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
		e.setRange(0, 10);
		e2.setRange(11, 20);
		ec.addElevator(e2);
		e.setController(ec);
		e2.setController(ec);
		building.setElevatorController(ec);
		
		
		EventBarrier upBar1 = new EventBarrier();
		upBar1.setFloor(12);
		
		EventBarrier upBar2 = new EventBarrier();
		upBar2.setFloor(11);
		
		EventBarrier upBar3 = new EventBarrier();
		upBar3.setFloor(13);
		
		EventBarrier upBar4 = new EventBarrier();
		upBar4.setFloor(4);
		
		EventBarrier upBar5 = new EventBarrier();
		upBar5.setFloor(5);
		
		EventBarrier upBar6 = new EventBarrier();
		upBar6.setFloor(6);
		
		Rider r1 = new Rider(building, 12, 18, upBar1);
		Rider r2 = new Rider(building, 11, 17, upBar2);
		Rider r3 = new Rider(building, 13, 16, upBar3);
		
		Rider r4 = new Rider(building, 4, 7, upBar4);
		Rider r5 = new Rider(building, 5, 2, upBar5);
		Rider r6 = new Rider(building, 6, 9, upBar6);
		
		r1.start();
		r2.start();
		r3.start();
		
		r4.start();
		r5.start();
		r6.start();
		
		Thread elevatorThread1 = new Thread(e);
		elevatorThread1.start();
		
		Thread elevatorThread2 = new Thread(e2);
		elevatorThread2.start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(18, r1.getCurrentFloor());
		assertEquals(17, r2.getCurrentFloor());	
		assertEquals(16, r3.getCurrentFloor());
		
		assertEquals(7, r4.getCurrentFloor());	
		assertEquals(2, r5.getCurrentFloor());
		assertEquals(9, r6.getCurrentFloor());	
	}

}
