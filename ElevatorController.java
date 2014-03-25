import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 
 * @author rtoussaint
 * @author Ryan Fishel
 * @author Lyndsay Kerwin
 * @author Carlos Reyes
 * 
 */

public class ElevatorController {
	private Set<Elevator> allElevators;
	private boolean checkUp;
	private boolean checkDown;
	
	private Building onlyBuilding;
	
	public ElevatorController(Building building, Elevator e){
		//TODO: add multiple elevators
		allElevators = Collections.newSetFromMap(new ConcurrentHashMap<Elevator, Boolean>());
		allElevators.add(e);
		onlyBuilding = building;
		checkUp = false;
		checkDown = false;
	}
	
	/**
	 * If a rider presses the up button while an elevator is in pursuit, it may need to reroute and pick this rider
	 * up.  Ex: Elevator on floor 1 going to floor 8.  A rider comes up at floor 4 and wants to go to floor 5.  The
	 * elevator should reroute to stop at floor 4 instead of 8.
	 * @param rerouteFloor
	 */
	protected synchronized void findClosestUpElevator(int rerouteFloor) {
		if(!checkUp){
			for(Elevator curElevator : allElevators){
				if(curElevator.getDirectionStatus() != Direction.DOWN && curElevator.getCurrentFloor() <= rerouteFloor){
					//the elevator is going up so reroute to new floor
					curElevator.setDestinationFloorAndChangeDirection(rerouteFloor);
					//TODO: Ryan Thought: return the elevator id all the way back to the rider.
					checkUp = true;
				}
			}
		}
	}
	
	
	protected synchronized void findClosestDownElevator(int rerouteFloor) {
		if(!checkDown){
			for(Elevator curElevator : allElevators){
				if(curElevator.getDirectionStatus() != Direction.UP && curElevator.getCurrentFloor() >= rerouteFloor){
					//the elevator is going up so reroute to new floor
					curElevator.setDestinationFloorAndChangeDirection(rerouteFloor);
					checkDown = true;
				}
			}
		}
	}
	
	protected synchronized void checkOnElevators(int rerouteFloor) {
		
	}
	
	protected Building getBuilding(){
		return onlyBuilding;
	}
	
	protected Set<Elevator> getElevators(){
		return allElevators;
	}
	
	public void addElevator(Elevator e) {
		allElevators.add(e);
	}
	
	public Elevator chooseElevator(){
		//TODO: pick which elevator should go
		Elevator el = null;
		for(Elevator e : allElevators) {
			if(e != null) {
				el = e;
			}
		}
		return el;

	}
	
}
