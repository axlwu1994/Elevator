import java.util.HashSet;

/**
 * 
 * @author rtoussaint
 * @author Ryan Fishel
 * @author Lyndsay Kerwin
 * 
 */

public class ElevatorController {
	private HashSet<Elevator> allElevators;
	private boolean checkUp;
	private boolean checkDown;
	
	private Building onlyBuilding;
	
	public ElevatorController(Building building, Elevator e){
		//TODO: add multiple elevators
		allElevators = new HashSet<Elevator>();
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
	protected synchronized Elevator checkUpElevators(int rerouteFloor) {
		Elevator finalElevator = null;
		if(!checkUp){
			for(Elevator curElevator : allElevators){
				if(curElevator.getDirectionStatus() != Direction.DOWN && curElevator.getCurrentFloor() <= rerouteFloor){
					//the elevator is going up so reroute to new floor
					curElevator.setDestinationFloorAndChangeDirection(rerouteFloor);
					checkUp = true;
					finalElevator = curElevator;
				}
				
			}
		}
		return finalElevator;
		
	}
	
	
	protected synchronized Elevator checkDownElevators(int rerouteFloor) {
		Elevator finalElevator = null;
		if(!checkDown){
			for(Elevator curElevator : allElevators){
				if(curElevator.getDirectionStatus() != Direction.UP && curElevator.getCurrentFloor() >= rerouteFloor){
					//the elevator is going up so reroute to new floor
					curElevator.setDestinationFloorAndChangeDirection(rerouteFloor);
					checkDown = true;
					finalElevator = curElevator;
				}
			}
		}
		return finalElevator;
	}
	
	protected synchronized void checkOnElevators(int rerouteFloor) {
		
	}
	
	protected Building getBuilding(){
		return onlyBuilding;
	}
	
	protected HashSet<Elevator> getElevators(){
		return allElevators;
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
