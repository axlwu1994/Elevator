import java.util.HashSet;

/**
 * 
 * @author rtoussaint
 *
 */

public class ElevatorController {
	private HashSet<Elevator> allElevators;
	
	
	
	
	protected void checkUpElevators(int rerouteFloor) {
		for(Elevator curElevator : allElevators){
			if(curElevator.getUpStatus() && curElevator.getCurrentFloor() < rerouteFloor){
				//the elevator is going up so reroute to new floor
				curElevator.setDestinationFloor(rerouteFloor);
			}
		}
	}
	
}
