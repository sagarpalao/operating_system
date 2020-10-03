import java.util.concurrent.Semaphore;

public class SemaphoreDiningPhilosophers {

    static enum State {
        THINKING,
        HUNGRY,
        EATING
    }

    static int N = 5;

    static Semaphore mutex;
    static Semaphore[] sem_philo;
    static State[] states;
	
	

    static void philosopher(int i) throws InterruptedException {
        states[i] = State.THINKING;
        System.out.println("Philosopher #" + (i + 1) + " is thinking.");

        while (true) {
            takeForks(i);
			try{
                Thread.sleep(500);
            }
            catch(Exception e){};
            eat(i);
			try{
                Thread.sleep(500);
            }
            catch(Exception e){};
            putForks(i);
			
        }
    }

    static void takeForks(int i) throws InterruptedException {
        mutex.acquire();
        states[i] = State.HUNGRY;
        test(i);
        mutex.release();
        sem_philo[i].acquire();
    }

    static void eat(int i) {
        System.out.println("Philosopher #" + (i + 1) + " is eating.");
    }

    static void putForks(int i) throws InterruptedException {
        mutex.acquire();
        states[i] = State.THINKING;
        System.out.println("Philosopher #" + (i + 1) + " is thinking.");
        test((i + 4) % N);
        test((i + 1) % N);
        mutex.release();
    }

    static void test(int i) {
        if (states[i] == State.HUNGRY
                && states[(i + 4) % N] != State.EATING
                && states[(i + 1) % N] != State.EATING) {
            states[i] = State.EATING;
            sem_philo[i].release();
        }
    }

    public static void main(String[] args) {

        mutex = new Semaphore(1);
        sem_philo = new Semaphore[N];
        for (int i = 0; i < N; i++) {
            sem_philo[i] = new Semaphore(0);
        }
        states = new State[N];

        Thread[] philosophers = new Thread[N];
        for (int i = 0; i < N; i++) {
            final int i2 = i;
            philosophers[i2] = new Thread(new Runnable() {
                public void run() {
                    try {
                        philosopher(i2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            philosophers[i2].start();
        }
    }
}
