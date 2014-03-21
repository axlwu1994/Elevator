

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
	//private Map<Thread, Long> threadMap;

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

	public boolean getEventOccuring(){
		return eventOccurring;
	}
	
	public void setFloor(int floor){
		myFloor = floor;
	}
	
	public int getFloor(){
		return myFloor;
	}

}
