import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * @author rtoussaint
 * @author Lyndsay Kerwin
 * 
 *
 *There is only one building for program.  It knows which floors have their 'up' and 'down' buttons pushed.  If a 
 *floor has its up button pushed, then this floor is added to upBarriers -- same for the 'down' button pushed.
 */

public class Building extends AbstractBuilding{
	
	private HashSet<EventBarrier> upBarriers; //list of floors that have the 'up' button pushed
	private HashSet<EventBarrier> downBarriers;
	private HashSet<EventBarrier> onBarriers;
	
	private ElevatorController myElevatorController;
	
	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {
		// TODO Auto-generated method stub
		addUpBarrier(fromFloor);
		myElevatorController.checkUpElevators(fromFloor);
		return null;
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		// TODO Auto-generated method stub
		addDownBarrier(fromFloor);
		myElevatorController.checkDownElevators(fromFloor);
		return null;
	}
	
	/**
	 * Using the floor level, create an event barrier and add it to the UP list.
	 * @param curFloor 
	 */
	public void addUpBarrier(int curFloor){
		
		EventBarrier myBarrier = new EventBarrier();
		myBarrier.setFloor(curFloor);
		upBarriers.add(myBarrier);
		//TODO: hashing method
	}
	
	/**
	 * Using the floor level, create an event barrier and add it to the DOWN list.
	 * @param curFloor 
	 */
	public void addDownBarrier(int curFloor){
		EventBarrier myBarrier = new EventBarrier();
		myBarrier.setFloor(curFloor);
		
		downBarriers.add(myBarrier);
	}
	
	/**
	 * Once the elevator comes and picks up the riders, remove this floor from the UpBarrier list.
	 * @param floor
	 */
	public void removeUpBarrier(int floor){
		for(EventBarrier myBarrier : upBarriers){
			if(myBarrier.getFloor() == floor){
				upBarriers.remove(myBarrier);
				break;
			}
		}		
	}
	
	/**
	 * Once the elevator comes and picks up the riders, remove this floor from the DownBarrier list.
	 * @param floor
	 */
	public void removeDownBarrier(int floor){
		for(EventBarrier myBarrier : downBarriers){
			if(myBarrier.getFloor() == floor){
				downBarriers.remove(myBarrier);
				break;
			}
		}
	}
	
	/**
	 * TODO: Make elevator run
	 */
	public void runElevatorLoop () {
		while (true) {
			if (!upBarriers.isEmpty()) {
				for (EventBarrier eb : upBarriers) {
					myElevatorController.checkUpElevators(eb.getFloor());
				}
			}
			if (!downBarriers.isEmpty()) {
				for (EventBarrier eb : downBarriers) {
					myElevatorController.checkDownElevators(eb.getFloor());
				}
			}
			if (!onBarriers.isEmpty()) {
				for (EventBarrier eb : onBarriers) {
					myElevatorController.checkOnElevators(eb.getFloor());
				}
			}
		}
	}
	
	protected HashSet<EventBarrier> getUpBarriers(){
		return upBarriers;
	}
	
	protected HashSet<EventBarrier> getDownBarriers(){
		return downBarriers;
	}
	
	protected HashSet<EventBarrier> getOnBarriers(){
		return onBarriers;
	}
	
	protected ElevatorController getElevatorController(){
		return myElevatorController;
	}

}
