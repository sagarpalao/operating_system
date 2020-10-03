import java.util.concurrent.Semaphore;

class ReaderWriter{
	
	static Semaphore mutex;
	static Semaphore db;
	static int rc;
	
	static void read(int i)throws Exception{
		
		while(true){
				mutex.acquire();
				rc++;
				if(rc==1){
					db.acquire();
				}
				mutex.release();
				System.out.println("Reader "+i+" reading....");
				
				mutex.acquire();
				rc--;
				if(rc==0){
					db.release();
				}
				mutex.release();
				try{
					Thread.sleep(1000);
				}
				catch(Exception e){}
		}
	}
	
	static void write()throws Exception{		
		while(true){
			db.acquire();
			System.out.println("Writer writing...");
			db.release();
			try{
					Thread.sleep(1000);
				}
				catch(Exception e){}
		}
	}
	
	public static void main(String...args)throws Exception{
		
		mutex=new Semaphore(1);
		db=new Semaphore(1);
		rc=0;
		int i;
		
		Thread reader[]=new Thread[3];
		for(i=0;i<3;i++){
			final int i2=i;
			reader[i]=new Thread(new Runnable(){
				public void run(){
					try{
						read(i2);
					}catch(Exception e){}
				}
			});
			reader[i].start();
		}
		Thread writer=new Thread(new Runnable(){
			public void run(){
				try{
					write();
				}catch(Exception e){}
			}
		});
		writer.start();
	}
}