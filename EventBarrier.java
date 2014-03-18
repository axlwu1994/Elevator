/**
 * 
 * @author Ryan Fishel
 * @author Ryan Toussaint
 *
 */

public class EventBarrier extends AbstractEventBarrier{

	
	private int numThreadsNotFinished;
	private boolean eventOccurring;
	
	public EventBarrier(){
		numThreadsNotFinished = 0;
		eventOccurring = false;
	}
	
	@Override
	public synchronized void arrive() {
		// check to see if an event is occurring -- if occurring, return.
		// if the event is NOT occurring, then block
		numThreadsNotFinished++;
		while(!eventOccurring){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}

	@Override
	public synchronized void raise() {
		eventOccurring = true;
		this.notifyAll();
		
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
	
	
	private synchronized void blockUntilAllThreadsComplete(){
		while(numThreadsNotFinished > 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
