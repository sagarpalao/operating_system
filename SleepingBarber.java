import java.util.concurrent.Semaphore;

class SleepingBarber{
	
	static Semaphore barberready;
	static Semaphore custready;
	static Semaphore acquireseat;
	static int free_seat;
	
	static void barber()throws Exception{
		
		while(true){
				System.out.println("Barber is sleeping...");
				custready.acquire();
				acquireseat.acquire();
				free_seat++;
				acquireseat.release();
				barberready.release();
				System.out.println("Barber is cutting hair");
				try{
					Thread.sleep(500);
				}
				catch(Exception e){}
		}
	}
	
	static void customer()throws Exception{	
		while(true){
		acquireseat.acquire();
		if(free_seat==0){
			acquireseat.release();
			System.out.println("Customer Leaves without Haircut");
		}
		else{
			custready.release();
			free_seat--;
			acquireseat.release();
			barberready.acquire();
			System.out.println("Customer has haircut");
			
		}
		try{
					Thread.sleep(10);
				}
				catch(Exception e){}
	}
	}
	
	public static void main(String...args)throws Exception{
		
		barberready=new Semaphore(0);
		custready=new Semaphore(0);
		acquireseat=new Semaphore(1);		
		free_seat=3;
		
		
		Thread b=new Thread(new Runnable(){
			public void run(){
				try{
					barber();
				}catch(Exception e){}
			}
		});
		b.start();
		
		Thread c=new Thread(new Runnable(){
			public void run(){
				try{
					customer();
				}catch(Exception e){}
			}
		});
		c.start();
	}
}