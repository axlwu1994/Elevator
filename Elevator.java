import java.util.Set;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author rtoussaint
 * @author Lyndsay Kerwin
 *
 */

public class Elevator extends AbstractElevator{

	private boolean doorsOpen;
	private boolean atFloor; //at the current floor
	private Direction direction;

//	private TreeSet<Integer> upRequests;
//	private TreeSet<Integer> downRequests;
	
	private int numOfRiders;
	
	private List<Rider> passengers;
	private List<Rider> peopleBoarding;
	
	private int currentFloor;
	
	private int destinationFloor;
	
	private ElevatorController controller;
	
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		numOfRiders = 0;
		passengers = new CopyOnWriteArrayList<Rider>();
		peopleBoarding = new CopyOnWriteArrayList<Rider>();
		currentFloor = -1;
		destinationFloor = 0;
		doorsOpen = false;
		atFloor = false;
		direction = Direction.STAGNANT;
	}

	@Override
	public void OpenDoors() {
		//Not used
		//people get out and new members get in --> close doors
	}

	@Override
	public void ClosedDoors() {
		//choose floor to go to
		//loop through people in elevator to see where to go to
		int val = numFloors;
		for(Rider currentRider : passengers){
			if(direction == Direction.UP && currentRider.getDestinationFloor() < val){
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
		destinationFloor = val;
		
	}

	/**
	 * TODO: Check race conditions (doors can't open in transit, rider can't
	 * leave during transit
	 */
	@Override
	public synchronized void VisitFloor(int floor) {
		//set currentFloor to the correctValue
		atFloor = true;
		currentFloor = floor;
		//wake everyone up on the elevator
		for(EventBarrier x : controller.getBuilding().getOnBarriers()){
			if(floor == x.getFloor()){
				x.raise();
				subtractRiders();
			}
		}
				
		//raise the event so riders know to wake up
		if(direction == Direction.UP){
			for(EventBarrier curBarrier : controller.getBuilding().getUpBarriers()){
				if(curBarrier.getFloor() == floor){
					//tell that floor to wake up and get on the elevator
					curBarrier.raise();
					//TODO: add riders to list for the elevator to know who to add.
					
					
				}
			}
		}
		if(direction == Direction.DOWN){
			for(EventBarrier curBarrier : controller.getBuilding().getDownBarriers()){
				if(curBarrier.getFloor() == floor){
					//tell that floor to wake up and get on the elevator
					curBarrier.raise();
					
				}
			}
		}
			
		
		ClosedDoors();
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
	
	
	private synchronized void subtractRiders(){
		for(Rider x : passengers){
			if(x.getDestinationFloor() == currentFloor){
				numOfRiders--;
				x.setCurrentFloor(currentFloor);
				controller.getBuilding().getOnBarriers().remove(x.getEventBarrier());
				passengers.remove(x);
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
//		
//		if (destinationFloor > oldFloor) {
//			direction = Direction.UP;
//		}
//		else if (destinationFloor < oldFloor) {
//			direction = Direction.DOWN;
//		}
//		else {
//			direction = Direction.STAGNANT;
//		}
		
	}
	
	public void calculateDirection(Set<EventBarrier> upBarriers, Set<EventBarrier> downBarriers, List<Rider> riders){
		if(!riders.isEmpty()) {
			for(Rider r : riders){
				if(r.getDirection()) {
					direction = Direction.UP;
				}
				else if(!r.getDirection()) {
					direction = Direction.DOWN;
				}
				 break;
			}
			return;
		}
		
		if(upBarriers.isEmpty() && downBarriers.isEmpty()){
			direction = Direction.STAGNANT;
		}
		else if(direction == Direction.UP && (upBarriers.isEmpty() || aboveTopOfUpBarriers(upBarriers))){
			direction = Direction.DOWN;
		}
		else if(direction == Direction.DOWN && (downBarriers.isEmpty() || belowBottomOfDownBarriers(downBarriers))){
			direction = Direction.UP;
		}
		else if (direction == Direction.STAGNANT && !upBarriers.isEmpty()) {
			direction = Direction.UP;
		}
		else if (direction == Direction.STAGNANT && !downBarriers.isEmpty()) {
			direction = Direction.DOWN;
		}
		
	}
	
	private boolean aboveTopOfUpBarriers(Set<EventBarrier> upBarriers) {
		int highestFloor = findHighestBarrier(upBarriers);
		if (highestFloor < currentFloor) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean belowBottomOfDownBarriers(Set<EventBarrier> downBarriers) {
		int lowestFloor = findLowestBarrier(downBarriers);
		if (currentFloor < lowestFloor) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private int findHighestBarrier(Set<EventBarrier> upBarriers) {
		int highest = 0;
		for (EventBarrier eb : upBarriers) {
			if (eb.getFloor() > highest) {
				highest = eb.getFloor();
			}
		}
		return highest;
	}
	
	private int findLowestBarrier(Set<EventBarrier> downBarriers) {
		int lowest = this.numFloors;
		for (EventBarrier eb : downBarriers) {
			if (eb.getFloor() < lowest) {
				lowest = eb.getFloor();
			}
		}
		return lowest;
	}

	public int getMaxOccupancy(){
		return maxOccupancyThreshold;
	}
	
	public int getNumPassengers(){
		return numOfRiders;
	}

	public void addPassenger(Rider x) {
		numOfRiders++;
		passengers.add(x);	
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}
	public void setController(ElevatorController ec) {
		controller = ec;
	}

	public List<Rider> getPassengers() {
		return passengers;
	}
	
}
