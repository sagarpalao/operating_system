import java.io.*;
import java.util.*;

class Dining{
	
	static final int n=5;
	static final int THINKING=0;
	static final int HUNGRY=1;
	static final int EATING=2;
	static int state[]=new int[n];
	static int s[]=new int[n];
	static int mutex=1;
	
	static int left(int i,int n){
		return (i+n-1)%n;
	}
	
	static int right(int i,int n){
		return (i+1)%n;
	}
	
	static void philosopher(int i){
		
			System.out.println("Thinking: "+i);
			take_fork(i);
			System.out.println("Eating: "+i);
			put_fork(i);
		
	}
	
	static void take_fork(int i){
		downM();
		state[i]=HUNGRY;
		test(i);
		upM();
		down(i);
	}
	
	static void put_fork(int i){
		downM();
		state[i]=THINKING;
		test(left(i,n));
		test(right(i,n));
		upM();
	}
	
	static void test(int i){
		if(state[i]==HUNGRY && state[left(i,n)]!=EATING && state[right(i,n)]!=EATING){			
			state[i]=EATING;
			up(i);
		}
	}
	
	static void up(int i){
		s[i]++;
	}
	
	static void down(int i){
		while(s[i]<=0);
		s[i]--;
	}
	
	static void upM(){
		mutex++;
	}
	
	static void downM(){
		while(mutex<=0);
		mutex--;
	}
	
	static class p extends Thread{
		int i;
		p(int i){
			this.i=i;
		}
		public void run(){
			philosopher(i);
		}
	}
	public static void main(String...args){
		int i=0;
		Random r=new Random();
		while(i<=10){
			int x=((r.nextInt()%n)+5)%5;
			System.out.println("Value: "+x);
			new p(x).start();
			//System.out.println("cool");
			i++;
		}
	}
	
	
}