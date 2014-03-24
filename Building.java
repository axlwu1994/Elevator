import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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

	private Set<EventBarrier> upBarriers; //list of floors that have the 'up' button pushed
	private Set<EventBarrier> downBarriers;
	private Set<EventBarrier> onBarriers;

	private ElevatorController myElevatorController;

	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);

		upBarriers = Collections.newSetFromMap(new ConcurrentHashMap<EventBarrier, Boolean>());
		downBarriers = Collections.newSetFromMap(new ConcurrentHashMap<EventBarrier, Boolean>());
		onBarriers = Collections.newSetFromMap(new ConcurrentHashMap<EventBarrier, Boolean>());
	}

	@Override
	public AbstractElevator CallUp(EventBarrier eb) {		
		//TODO: Ryan Thought: have this return elevator from checkUpElevators()
		addUpBarrier(eb);
		myElevatorController.findClosestUpElevator(eb.getFloor());
		return null;
	}

	@Override
	public AbstractElevator CallDown(EventBarrier eb) {
		// TODO Auto-generated method stub
		addDownBarrier(eb);
		myElevatorController.findClosestDownElevator(eb.getFloor());
		return null;
	}


	public void addOnBarriers(EventBarrier eb){
		onBarriers.add(eb);
	}

	/**
	 * Using the floor level, create an event barrier and add it to the UP list.
	 * @param curFloor 
	 */
	public void addUpBarrier(EventBarrier eb){
		upBarriers.add(eb);
		//TODO: hashing method
	}

	/**
	 * Using the floor level, create an event barrier and add it to the DOWN list.
	 * @param curFloor 
	 */
	public void addDownBarrier(EventBarrier eb){
		downBarriers.add(eb);
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

	protected Set<EventBarrier> getUpBarriers(){
		return upBarriers;
	}

	protected Set<EventBarrier> getDownBarriers(){
		return downBarriers;
	}

	protected Set<EventBarrier> getOnBarriers(){
		return onBarriers;
	}

	protected ElevatorController getElevatorController(){
		return myElevatorController;
	}

	public void setElevatorController(ElevatorController ec) {
		this.myElevatorController = ec;

	}

}
