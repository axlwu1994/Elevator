
/**
 * 
 * @author rtoussaint
 * This class takes care of the functionality that is associated with Riders.  Each rider knows what floor it is
 * on, which floor it is on, whether or not it's on an elevator, and its building and event barrier.  In order to
 * get on an elevator, the rider has to signal but calling buttonUp or buttonDown.
 */

public class Rider extends Thread{

	private int currentFloor;
	private int destFloor;
	
	private boolean onElevator;
	
	private EventBarrier myBarrier;
	private Building myBuilding;
	
	
	public Rider(Building building, int presentFloor, int destination){
		Building myBuilding = building;
		EventBarrier myBarrier = new EventBarrier();
		currentFloor = presentFloor;
		destFloor = destination;
		
	}
	
	/**
	 * Signal to the program that this rider wants to take an elevator up.  It will wait() until the Elevator wakes
	 * it up, once it arrives on its floor.
	 */
	public void buttonUp(){
		myBuilding.CallUp(currentFloor); //also reroute the elevator in case it is in progress
		myBarrier.arrive();
		//TODO: add rider to onboarding
	}

	/**
	 * Signal to the program that this rider wants to take an elevator down.  It will wait() until the Elevator wakes
	 * it up, once it arrives on its floor.
	 */
	public void buttonDown(){
		myBuilding.CallDown(currentFloor);
		myBarrier.arrive();
	}
	
	/**
	 * Get rider id
	 * @return rider id
	 */
	public int getRiderId(){
		return (int)getId();
	}
	
	/**
	 * Update the rider's event barrier based on the floor that it is on.  This method is called when the rider
	 * changes floors and needs to update.
	 * @param newBarrier
	 */
	public void updateEventBarrier(EventBarrier newBarrier){
		myBarrier = newBarrier;
		currentFloor = myBarrier.getFloor();
	}
	
	/**
	 * The destination floor
	 * @return the floor the rider wants to go to
	 */
	public int getDestinationFloor(){
		return destFloor;
	}
	
	
	public void setDestinationFloor(){
		//TODO: implement this method so that a rider can change destination once it rides the elevator
	}
	
}
