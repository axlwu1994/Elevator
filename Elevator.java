import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;

/**
 * 
 * @author rtoussaint
 * @author Lyndsay Kerwin
 *
 */

public class Elevator extends AbstractElevator{

	private boolean doorsOpen;
	private boolean atFloor; //at the current floor
	//TODO: Delete atFloor???
	private Direction direction;
	private int numOfRiders; //TODO: Delete????
	
	List<Rider> passengers;
	List<Rider> peopleBoarding;
	
	private int currentFloor;
	
	private int destinationFloor;
	
	private ElevatorController controller;
	
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		numOfRiders = 0;
		passengers = new ArrayList<Rider>();
		peopleBoarding = new ArrayList<Rider>();
		currentFloor = 0;
		destinationFloor = 0;
		doorsOpen = false;
		atFloor = false;
		direction = Direction.STAGNANT;
	}

	@Override
	public void OpenDoors() {
		//TODO: Delete this method????
		//Not used
		//people get out and new members get in --> close doors
		
		//raise the event so riders know to wake up
		if(direction == Direction.UP){
			for(EventBarrier curBarrier : controller.getBuilding().getUpBarriers()){
				if(curBarrier.getFloor() == currentFloor){
					curBarrier.raise(); //wake up all the people on this floor that want to go up
				}
			}
		}
		if(direction == Direction.DOWN){
			for(EventBarrier curBarrier : controller.getBuilding().getDownBarriers()){
				if(curBarrier.getFloor() == currentFloor){
					curBarrier.raise();
				}
			}
		}	
	}

	@Override
	public void ClosedDoors() {		
		destinationFloor = findNextStop();
		//TODO: go to this Floor
		
	}
	
	public int findNextStop(){
		int val = numFloors;
		for(Rider currentRider : passengers){
			if(direction == Direction.UP && currentRider.getDestinationFloor() < val){
				assert currentRider.getDestinationFloor() < currentFloor;
				val = currentRider.getDestinationFloor();
			}
			else if(direction == Direction.DOWN){
				if(val == numFloors){
					val = 0;
				}
				if(currentRider.getDestinationFloor() > val){
					val = currentRider.getDestinationFloor();
				}
			}	
		}
		return val;
	}

	/**
	 * TODO: Check race conditions (doors can't open in transit, rider can't
	 * leave during transit
	 */
	
	public void arriveAtFloor(int floor) {
		atFloor = true;
		currentFloor = floor;
		for(EventBarrier x : controller.getBuilding().getOnBarriers()){
			if(floor == x.getFloor()){
				//x is the eventBarrier for the 'ith' floor -- we are now at that floor so wake up all those riders
				x.raise();
				subtractRiders();
				updateExitingRiders(x);
			}
		}	
		
		OpenDoors();
		//TODO:Thread should block here until complete -- maybe it is already doing this??
		
		ClosedDoors();
	}

	private void updateExitingRiders(EventBarrier currentBarrier) {
		for(Rider x : passengers){
			if(x.getDestinationFloor() == currentFloor){
				x.setCurrentFloor(currentFloor);
				x.setOnElevator(false);
				x.setMyBarrier(currentBarrier);
			}
		}
	}

	@Override
	public boolean Enter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RequestFloor(int floor) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void subtractRiders(){
		for(Rider x : passengers){
			if(x.getDestinationFloor() == currentFloor){
				numOfRiders--;
				controller.getBuilding().getOnBarriers().remove(x);
				/*now this 'rider' is off the elevator but just staying on the floor -- it will need to 'callUp' 
				 * or 'callDown' in order to get back on.
				 */
			}
		}
	}
	
	public Direction getDirectionStatus(){
		return direction;
	}
	
	public int getCurrentFloor(){
		return currentFloor;
	}

	public void setDestinationFloorAndChangeDirection(int floor){
		int oldFloor = destinationFloor;
		destinationFloor = floor;
		
		if (destinationFloor > oldFloor) {
			direction = Direction.UP;
		}
		else if (destinationFloor < oldFloor) {
			direction = Direction.DOWN;
		}
		else {
			direction = Direction.STAGNANT;
		}
		
	}
	
	//TODO: change elevator direction
	public void calculateDirection(Set<EventBarrier> upBarriers, Set<EventBarrier> downBarriers){
		if(upBarriers.isEmpty() && downBarriers.isEmpty()){
			direction = Direction.STAGNANT;
		}
		else if(direction == Direction.UP && upBarriers.isEmpty()){
			direction = Direction.DOWN;
		}
		else if(direction == Direction.DOWN && downBarriers.isEmpty()){
			direction = Direction.UP;
		}
		else if (direction == Direction.STAGNANT && !upBarriers.isEmpty()) {
			direction = Direction.UP;
		}
		else if (direction == Direction.STAGNANT && !downBarriers.isEmpty()) {
			direction = Direction.DOWN;
		}
		
	}
	
	public int getMaxOccupancy(){
		return maxOccupancyThreshold;
	}
	
	public int getNumPassengers(){
		return passengers.size();
	}

	public void addPassenger(Rider x) {
		passengers.add(x);	
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}
	public void setController(ElevatorController ec) {
		controller = ec;
	}
	
}
