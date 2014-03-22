
/**
 * 
 * @author Ryan Fishel
 * @author rtoussaint
 *
 */

public class EventBarrier extends AbstractEventBarrier{


	private int numThreadsNotFinished;
	private boolean eventOccurring;
	private int myFloor;
	

	public EventBarrier(){
		numThreadsNotFinished = 0;
		eventOccurring = false;
		//threadMap = new HashMap<Thread, Long>();
		myFloor = 0;
	}

	@Override
	public synchronized void arrive() {
		// check to see if an event is occurring -- if occurring, return.
		// if the event is NOT occurring, then block
		numThreadsNotFinished++;
		
		while(!eventOccurring) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return;
	}

	@Override
	public synchronized void raise() {
		eventOccurring = true;
		notifyAll();

		//wait for all threads to complete, then change boolean back to false --> then return from raise
		blockUntilAllThreadsComplete();
		eventOccurring = false;
	}

	@Override
	public synchronized void complete() {
		numThreadsNotFinished--;
		blockUntilAllThreadsComplete();
	}

	@Override
	public synchronized int waiters() {
		return numThreadsNotFinished;
	}


	private void blockUntilAllThreadsComplete(){
		while(numThreadsNotFinished >0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("END OF BLOCK UNTIL COMPLETE");
		notifyAll();
		return;
	}
	
	/**
	 * See if an event is occurring 
	 * @return is the event occurring
	 */
	public boolean getEventOccuring(){
		return eventOccurring;
	}
	
	/**
	 * Set the floor for this Event Barrier
	 * @param floor
	 */
	public void setFloor(int floor){
		myFloor = floor;
	}
	
	/**
	 * Floor for this event barrier
	 * @return the floor for this event barrier
	 */
	public int getFloor(){
		return myFloor;
	}
	
	@Override
    public int hashCode() {
		int hash = 17;
		hash += 13*myFloor;
		return myFloor;
	}
	

}
