/**
 * 
 * @author rtoussaint
 *
 */


public class Rider extends Thread{

	private int currentFloor;
	private int destFloor;
	
	Boolean onElevator;
	
	private EventBarrier myBarrier;
	private Building myBuilding;
	
	
	public Rider(Building building, int presentFloor, int destination){
		
		Building myBuilding = building;
		EventBarrier myBarrier = new EventBarrier();
		currentFloor = presentFloor;
		destFloor = destination;
		
	}
	
	public void buttonUp(){
		
		myBuilding.CallUp(currentFloor); //also reroute the elevator incase it is in progress
		myBarrier.arrive();
	}

	public void buttonDown(){
		myBuilding.CallDown(currentFloor);
	}
	
	
	public int getRiderId(){
		return (int)getId();
	}
	
	public void updateEventBarrier(EventBarrier newBarrier){
		myBarrier = newBarrier;
		currentFloor = myBarrier.getFloor();
	}
	
	public int getDestinationFloor(){
		return destFloor;
	}
	
}
