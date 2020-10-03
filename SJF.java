import java.io.*;
import java.util.*;

class SJF{
	
	static int n;
	public static void main(String args[]){
			
		int p[][]=new int[10][5];
		int i,j,tmp;
		Scanner scr=new Scanner(System.in);

		System.out.println("Enter number of processes: ");
		n=scr.nextInt();
		System.out.println("Enter Processes Details: ");
		for(i=0;i<n;i++){
			
			p[i][0]=i+1;
			System.out.println("For Process "+(i+1)+": ");
			System.out.print("Enter Burst time: ");
			p[i][1]=scr.nextInt();
			System.out.print("Enter Arrival Time: ");
			p[i][2]=scr.nextInt();
			
		}
		for(i=0;i<n-1;i++){
			
			for(j=0;j<n-1-i;j++){
				
				if(p[j][2]>p[j+1][2]){
					
					tmp=p[j][0];
					p[j][0]=p[j+1][0];
					p[j+1][0]=tmp;
					
					tmp=p[j][1];
					p[j][1]=p[j+1][1];
					p[j+1][1]=tmp;
					
					tmp=p[j][2];
					p[j][2]=p[j+1][2];
					p[j+1][2]=tmp;
				}
			}
		}
		sjf(p);
	}
	
	static void display(int p[][]){
            
		int i,j;
                System.out.println("\tProcess\tBurst T.\tArrival T.\tWaiting T.\tTurnaround T.");
		System.out.println("-------------------------------------------------------------------------------------------");
		for(i=0;i<n;i++){
			for(j=0;j<5;j++){
				System.out.print("\t"+p[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	static void sjf(int p[][]){
		
		int pp[][]=new int[n][5];
		int timeInstant=0;
		int x,i,j,small,copy_n,sum;
		copy_n=n;
		for(x=0;x<n;x++){
			small=0;
			for(i=0;i<copy_n;i++){
				if(p[i][1]<p[small][1] && p[i][2]<=timeInstant){
					small=i;
				}
			}
			pp[x][0]=p[small][0];
			pp[x][1]=p[small][1];
			pp[x][2]=p[small][2];
			
			for(i=small;i<n-1;i++){
				p[i][0]=p[i+1][0];
				p[i][1]=p[i+1][1];
				p[i][2]=p[i+1][2];
			}
			copy_n=copy_n-1;
			timeInstant+=pp[x][1];
		}
		
		for(i=0;i<n;i++){
			sum=0;
			for(j=i-1;j>=0;j--){
				sum=sum+pp[j][1];
			}
			sum=sum-pp[i][2];
			pp[i][3]=sum;
		}
		
		for(i=0;i<n;i++){
			pp[i][4]=pp[i][1]+pp[i][3];
		}
		
		display(pp);
		printAvg(pp);
	}
	
	static void printAvg(int p[][]){
		
		int i,j;
		double avg_waiting=0;
		double avg_turn=0;
		for(i=0;i<n;i++){
			avg_waiting=avg_waiting+p[i][3];
			avg_turn=avg_turn+p[i][4];
		}
		avg_waiting=avg_waiting/n;
		avg_turn=avg_turn/n;
		
		System.out.println("Average Waiting Time: "+avg_waiting);
		System.out.println("Average Turnaroundtime Time: "+avg_turn);
		
	}
}
