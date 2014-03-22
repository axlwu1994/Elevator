import java.util.HashSet;

/**
 * 
 * @author rtoussaint
 *
 */

public class ElevatorController {
	private HashSet<Elevator> allElevators;
	private boolean takenCareOf_Up = false;
	private boolean takenCareOf_Down = false;
	
	private Building onlyBuilding;
	
	public ElevatorController(Building building){
		onlyBuilding = building;
	}
	
	/**
	 * If a rider presses the up button while an elevator is in pursuit, it may need to reroute and pick this rider
	 * up.  Ex: Elevator on floor 1 going to floor 8.  A rider comes up at floor 4 and wants to go to floor 5.  The
	 * elevator should reroute to stop at floor 4 instead of 8.
	 * @param rerouteFloor
	 */
	protected synchronized void checkUpElevators(int rerouteFloor) {
		if(!takenCareOf_Up){
			for(Elevator curElevator : allElevators){
				if(curElevator.getUpStatus() && curElevator.getCurrentFloor() < rerouteFloor){
					//the elevator is going up so reroute to new floor
					curElevator.setDestinationFloor(rerouteFloor);
					takenCareOf_Up = true;
				}
			}
		}
	}
	
	
	protected synchronized void checkDownElevators(int rerouteFloor) {
		if(!takenCareOf_Down){
			for(Elevator curElevator : allElevators){
				if(!curElevator.getUpStatus() && curElevator.getCurrentFloor() > rerouteFloor){
					//the elevator is going up so reroute to new floor
					curElevator.setDestinationFloor(rerouteFloor);
					takenCareOf_Down = true;
				}
			}
		}
	}
	
	protected Building getBuilding(){
		return onlyBuilding;
	}
	
	protected HashSet<Elevator> getElevators(){
		return allElevators;
	}
	
}
