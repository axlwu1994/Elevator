import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;


public class Elevator extends AbstractElevator{
	
	
	private boolean doorsOpen;
	private boolean atFloor; //at the current floor
	private boolean goingUp;
	private ArrayList<EventBarrier> upBarriers;
	private ArrayList<EventBarrier> downBarriers;
	private TreeSet<Integer> upRequests;
	private TreeSet<Integer> downRequests;
	
	private int numOfRiders;
	
	List<Rider> passengers;
	
	private int currentFloor;
	
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		//passengers = new ArrayList<Thread>();
		//array list of all of the event barriers
		upBarriers = new ArrayList<EventBarrier>();
		downBarriers = new ArrayList<EventBarrier>();
		//create an up and down barrier for each floor
		for (int i = 0; i < numFloors + 1; i++) {
			upBarriers.add(new EventBarrier());
			downBarriers.add(new EventBarrier());
		}
			
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
	
	}
	
	private void subtractRiders(){
		for(Rider x : passengers){
			if(x.getState == currentFloor){
				numOfRiders--;
			}
		}
	}

}
