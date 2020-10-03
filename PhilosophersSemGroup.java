class SemaphoreGroup {
	private int[] values; 
	public SemaphoreGroup(int numberOfMembers) {
	if(numberOfMembers <= 0)
		return;
		values = new int[numberOfMembers];
	}
	
	public synchronized void changeValues(int[] deltas) {
		if(deltas.length != values.length)
			return;
		while(! canChange(deltas)) {
			try {
				wait();
			} catch(InterruptedException e) {}
		}
		doChange(deltas);
		notifyAll();
	}
	
	private boolean canChange(int[] deltas) {
		for(int i = 0; i < values.length; i++)
		if(values[i] + deltas[i] < 0)
			return false;
		return true;
	}
	
	private void doChange(int[] deltas) {
		for(int i = 0; i < values.length; i++)
		values[i] = values[i] + deltas[i];
	}
	
	public int getNumberOfMembers() {
		return values.length;
	}
}


class PhilosopherSemGroup extends Thread {
	private SemaphoreGroup sems;
	private int place;
	private int leftFork;
	private int rightFork;
	
	public PhilosopherSemGroup(SemaphoreGroup sems, int place) {
		this.sems = sems;
		this.place = place;
		leftFork = place;
		if(place+1 < sems.getNumberOfMembers())
		rightFork = place+1;
		else
		rightFork = 0;
		start();
	}
	
	public void run() {
		int[] deltas = new int[sems.getNumberOfMembers()];
		for(int i = 0; i < deltas.length; i++)
		deltas[i] = 0;
		while(true) {
		thinking(place);
		deltas[leftFork] = -1;
		deltas[rightFork] = -1;
		sems.changeValues(deltas);
		eating(place);
		deltas[leftFork] = 1;
		deltas[rightFork] = 1;
		sems.changeValues(deltas);
		}
	}
	
	private void thinking(int place) {
		System.out.println("Philosopher " + place + " is thinking.");
		try {
			sleep((int)(Math.random() * 20000));
		} catch(InterruptedException e) {}
	}
	
	private void eating(int place) {
		System.out.println("Philosopher " + place + " starts eating.");
		try {
			sleep((int)(Math.random() * 20000));
		} catch(InterruptedException e) {}
		System.out.println("Philosopher " + place + " finished eating.");
	}
}

public class PhilosophersSemGroup {
	private static final int N = 5;
	public static void main(String[] args) {
		SemaphoreGroup group = new SemaphoreGroup(N);
		int[] init = new int[N];
		for (int i = 0; i < init.length; i++)
			init[i] = 1;
		group.changeValues(init);
		for(int i = 0; i < N; i++)
			new PhilosopherSemGroup(group, i);
	}
}
