
/**
 * 
 * @author rtoussaint
 * @author Lyndsay Kerwin
 * @author Ryan Fishel
 * @author Carlos Reyes
 * This class takes care of the functionality that is associated with Riders.  Each rider knows what floor it is
 * on, which floor it is on, whether or not it's on an elevator, and its building and event barrier.  In order to
 * get on an elevator, the rider has to signal but calling buttonUp or buttonDown.
 */

public class Rider extends Thread{

	private int currentFloor;
	private int destFloor;
	private boolean onElevator;
	private boolean goingUp;
	private EventBarrier myBarrier;
	private Building myBuilding;

	public Rider(Building building, int presentFloor, int destination, EventBarrier eventBarrier){
		this.myBuilding = building;
		this.myBarrier = eventBarrier;
		currentFloor = presentFloor;
		destFloor = destination;
		setDirection();
	}

	/**
	 * Signal to the program that this rider wants to take an elevator up.  It will wait() until the Elevator wakes
	 * it up, once it arrives on its floor.
	 */
	public void buttonUp(){
		Elevator newElevator = myBuilding.CallUp(myBarrier); 
		myBarrier.arrive();

		if(newElevator != null && currentFloor == newElevator.getCurrentFloor() && newElevator.getMaxOccupancy() > newElevator.getNumPassengers() && newElevator.getDirectionStatus() != Direction.DOWN){
			newElevator.addPassenger(this);
			onElevator = true;
			goingUp = true;
			myBuilding.addOnBarrier(myBarrier);
			myBuilding.removeUpBarrier(myBarrier);
			myBarrier.complete();
		}
		else if(newElevator != null && currentFloor == newElevator.getCurrentFloor() && newElevator.getMaxOccupancy() < newElevator.getNumPassengers()  && newElevator.getDirectionStatus() != Direction.DOWN){
			myBarrier.complete();
			this.buttonUp();
		}

	}

	/**
	 * Signal to the program that this rider wants to take an elevator down.  It will wait() until the Elevator wakes
	 * it up, once it arrives on its floor.
	 */
	public void buttonDown(){
		Elevator newElevator = myBuilding.CallDown(myBarrier);
		myBarrier.arrive();

		if(newElevator != null && currentFloor == newElevator.getCurrentFloor() && newElevator.getMaxOccupancy() > newElevator.getNumPassengers() && newElevator.getDirectionStatus() != Direction.UP){
			newElevator.addPassenger(this);
			onElevator = true;
			goingUp = false;
			myBuilding.addOnBarrier(myBarrier);
			myBuilding.removeDownBarrier(myBarrier);
			myBarrier.complete();
		}
		else if (newElevator != null && currentFloor == newElevator.getCurrentFloor() && newElevator.getMaxOccupancy() < newElevator.getNumPassengers()  && newElevator.getDirectionStatus() != Direction.UP){
			myBarrier.complete();
			this.buttonDown();
		}
	}

	/**
	 * Update the rider's event barrier based on the floor that it is on.  This method is called when the rider
	 * changes floors and needs to update.
	 * @param newBarrier
	 */
	//TODO: DELETE????
	public void updateEventBarrier(EventBarrier newBarrier){
		myBarrier = newBarrier;
		currentFloor = myBarrier.getFloor();
	}
	
	@Override
	public void run() {
		if(goingUp) {
			buttonUp();
		}
		else{
			buttonDown();
		}
	}

	
	/*----------------------------------------------------------------------------------------------------------
	 *----------------------------------------------------------------------------------------------------------
	 *----------------------------------------------GETTERS-------------------------------------------------
	 *----------------------------------------------------------------------------------------------------------
	 *----------------------------------------------------------------------------------------------------------
	 *----------------------------------------------------------------------------------------------------------
	 * 
	 */
	/**
	 * The destination floor
	 * @return the floor the rider wants to go to
	 */
	public int getDestinationFloor(){
		return destFloor;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Get rider id
	 * @return rider id
	 */
	public int getRiderId(){
		return (int)getId();
	}

	
	/*----------------------------------------------------------------------------------------------------------
	 *----------------------------------------------------------------------------------------------------------
	 *----------------------------------------------SETTERS-------------------------------------------------
	 *----------------------------------------------------------------------------------------------------------
	 *----------------------------------------------------------------------------------------------------------
	 *----------------------------------------------------------------------------------------------------------
	 * 
	 */
	
	private void setDirection() {
		// TODO Auto-generated method stub
		if(currentFloor > destFloor){
			goingUp = false;
		}
		else {
			goingUp = true;
		}
	}

	public void setCurrentFloor(int currentFloor2) {
		currentFloor = 	currentFloor2;	
	}

	
	public void setDestinationFloor(int level){
		//TODO: implement this method so that a rider can change destination once it rides the elevator
		destFloor = level;
	}
	
	public void setOnElevator(boolean status){
		onElevator = status;
	}
	
	public void setMyBarrier(EventBarrier newBarrier){
		myBarrier = newBarrier;
	}

}
