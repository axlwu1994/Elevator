import java.util.ArrayList;
import java.util.List;


public class Elevator extends AbstractElevator{
	
	
	private boolean doorsOpen;
	private boolean atFloor; //at the current floor
	private boolean goingUp;
	
	private int numOfRiders;
	
	List<Rider> passengers;
	
	private int currentFloor;
	
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		passengers = new ArrayList<Thread>();
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
