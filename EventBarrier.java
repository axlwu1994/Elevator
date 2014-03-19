

/**
 * 
 * @author Ryan Fishel
 * @author Ryan Toussaint
 *
 */

public class EventBarrier extends AbstractEventBarrier{


	private int numThreadsNotFinished;
	private boolean eventOccurring;
	//private Map<Thread, Long> threadMap;

	public EventBarrier(){
		numThreadsNotFinished = 0;
		eventOccurring = false;
		//threadMap = new HashMap<Thread, Long>();
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
		eventOccurring = false;
		return;
	}

	public boolean getEventOccuring(){
		return eventOccurring;
	}

}
