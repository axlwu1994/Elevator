import java.util.HashSet;

/**
 * 
 * @author rtoussaint
 * @author Lyndsay Kerwin
 * 
 */

public class ElevatorController {
	private HashSet<Elevator> allElevators;
	private boolean checkUp;
	private boolean checkDown;
	
	private Building onlyBuilding;
	
	public ElevatorController(Building building){
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
	protected synchronized void checkUpElevators(int rerouteFloor) {
		if(!checkUp){
			for(Elevator curElevator : allElevators){
				if(curElevator.getDirectionStatus() != Direction.DOWN && curElevator.getCurrentFloor() < rerouteFloor){
					//the elevator is going up so reroute to new floor
					curElevator.setDestinationFloor(rerouteFloor);
					checkUp = true;
				}
			}
		}
	}
	
	
	protected synchronized void checkDownElevators(int rerouteFloor) {
		if(!checkDown){
			for(Elevator curElevator : allElevators){
				if(curElevator.getDirectionStatus() != Direction.UP && curElevator.getCurrentFloor() > rerouteFloor){
					//the elevator is going up so reroute to new floor
					curElevator.setDestinationFloor(rerouteFloor);
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
	
	protected HashSet<Elevator> getElevators(){
		return allElevators;
	}
	
}
