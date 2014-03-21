import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;

/**
 * 
 * @author rtoussaint
 *
 */


public class Elevator extends AbstractElevator{
	
	
	private boolean doorsOpen;
	private boolean atFloor; //at the current floor
	private boolean goingUp;

	private TreeSet<Integer> upRequests;
	private TreeSet<Integer> downRequests;
	
	private int numOfRiders;
	
	List<Rider> passengers;
	
	private int currentFloor;
	
	private int destinationFloor;
	
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);

			
	}

	@Override
	public void OpenDoors() {
		//people get out and new members get in --> close doors
		
		
		
		subtractRiders();
		addRiders();
		
		//maybe other methods here
		
		ClosedDoors();

	}

	@Override
	public void ClosedDoors() {
		// TODO Auto-generated method stub
		//choose floor to go to
		//loop through people in elevator to see where to go to
		int val = numFloors;
		for(Rider currentRider : passengers){
			if(goingUp && currentRider.getDestinationFloor() < val){
				val = currentRider.getDestinationFloor();
			}
			else if(!goingUp){
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

	@Override
	public void VisitFloor(int floor) {
		//set currentFloor to the correctValue
		//call openDoors
		
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
	
	
	
	
	private void addRiders(){
		//use goingUp boolean to see which people to add
		//Ryan Fishel
	}
	
	private void subtractRiders(){
		for(Rider x : passengers){
			if(x.getDestinationFloor() == currentFloor){
				numOfRiders--;
			}
		}
	}
	
	public boolean getUpStatus(){
		return goingUp;
	}
	
	public int getCurrentFloor(){
		return currentFloor;
	}

	
	
	public void setDestinationFloor(int floor){
		destinationFloor = floor;
	}
}
