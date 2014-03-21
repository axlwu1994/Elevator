import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * @author rtoussaint
 *
 */

public class Building extends AbstractBuilding{
	
	private HashSet<EventBarrier> upBarriers; //list of floors that have the 'up' button pushed
	private HashSet<EventBarrier> downBarriers;
	
	private ElevatorController elevatorController;
	
	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {
		// TODO Auto-generated method stub
		addUpBarrier(fromFloor);
		elevatorController.checkUpElevators(fromFloor);
		return null;
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		// TODO Auto-generated method stub
		addDownBarrier(fromFloor);
		
		return null;
	}
	
	public void addUpBarrier(int curFloor){
		
		EventBarrier myBarrier = new EventBarrier();
		myBarrier.setFloor(curFloor);
		
		upBarriers.add(myBarrier);
	}
	
	public void addDownBarrier(int curFloor){
		EventBarrier myBarrier = new EventBarrier();
		myBarrier.setFloor(curFloor);
		
		downBarriers.add(myBarrier);
	}
	
	public void removeUpBarrier(int floor){
		for(EventBarrier myBarrier : upBarriers){
			if(myBarrier.getFloor() == floor){
				upBarriers.remove(myBarrier);
				break;
			}
		}		
	}
	
	public void removeDownBarrier(int floor){
		for(EventBarrier myBarrier : downBarriers){
			if(myBarrier.getFloor() == floor){
				downBarriers.remove(myBarrier);
				break;
			}
		}
	}

}
