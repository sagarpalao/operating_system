import java.io.*;
import java.util.*;

class FCFS{	

	public static void main(String args[]){
		int p[][]=new int[10][5];
		int i,j,n,tmp;
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
		fcfs(p,n);
		display(p,n);
		printAvg(p,n);	
	}
	
	static void display(int p[][],int n){
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
	
	static void fcfs(int p[][],int n){
		int i,j,sum;
		for(i=0;i<n;i++){
			sum=0;
			for(j=i-1;j>=0;j--){
				sum=sum+p[j][1];
			}
			sum=sum-p[i][2];
			p[i][3]=sum;
		}
		for(i=0;i<n;i++){
			p[i][4]=p[i][1]+p[i][3];
		}	
	}
	
	static void printAvg(int p[][],int n){
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
