import java.io.*;
import java.util.*;

class RoundRobin{
	
	public static int burst[];
	public static void main(String args[]){
            
                int p[][]=new int[10][5];
		int i,j,n,tmp,tq;
		Scanner scr=new Scanner(System.in);
                
		System.out.println("Enter number of processes: ");
		n=scr.nextInt();
		System.out.println("Enter Processes Details: ");
		for(i=0;i<n;i++){	
			p[i][0]=i+1;
			System.out.println("For Process "+(i+1)+": ");
			System.out.print("Enter Burst time: ");
			p[i][1]=scr.nextInt();
		}
		System.out.print("Enter Time Quantum: ");
		tq=scr.nextInt();
		rr(p,n,tq);
	}
	
	static void display(int p[][],int n){
		int i,j;
		for(i=0;i<n;i++){
			for(j=0;j<3;j++){
				System.out.print("\t"+p[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	static void rr(int p[][],int n,int tq){
		
		int pp[][]=new int[30][3];
		int timeinstinct=0;
		int cnt=0;
		int i,j;
		burst=new int[n];		
		for(i=0;i<n;i++){
			burst[i]=p[i][1];
		}
		while(true){
			for(i=0;i<n;i++){
				if(p[i][1]==0){
					
				}
				else if(p[i][1]<=tq){
					pp[cnt][0]=i;
					pp[cnt][1]=timeinstinct;
					pp[cnt][2]=pp[cnt][1]+p[i][1];
					timeinstinct+=p[i][1];
					p[i][1]=0;
					cnt++;
				}
				else{
					pp[cnt][0]=i;
					pp[cnt][1]=timeinstinct;
					pp[cnt][2]=pp[cnt][1]+tq;
					timeinstinct+=tq;
					p[i][1]-=tq;
					cnt++;
				}
			}
			for(i=0;i<n;i++){
				if(p[i][1]!=0){
					break;
				}
			}
			if(i==n){
				break;
			}
		}			
		printAvg(pp,cnt,n);		
	}
	
	static void printAvg(int p[][],int n,int pr){
		
		int i,j;
		
		double avg_waiting=0;
		double avg_turn=0;
		int ppp[][]=new int[n][3];
		boolean visited[]=new boolean[n];
		int cnt=0;
		for(i=0;i<n;i++)
			visited[i]=false;		
		for(i=n-1;i>=0;i--){
			if(visited[p[i][0]]==false){
				ppp[cnt][0]=p[i][0];
				ppp[cnt][2]=p[i][2];
				ppp[cnt][1]=ppp[cnt][2]-burst[p[i][0]];
				visited[p[i][0]]=true;
				cnt++;
			}
		}
		System.out.println("\n\n");                
                System.out.println("\tProcess\tWaiting T.\tTurnaround T.");
		System.out.println("-------------------------------------------------------------------------------------------");
		display(ppp,cnt);		
		for(i=0;i<pr;i++){
			avg_waiting=avg_waiting+ppp[i][1];
			avg_turn=avg_turn+ppp[i][2];
		}
		avg_waiting=avg_waiting/pr;
		avg_turn=avg_turn/pr;
		System.out.println("\nAverage Waiting Time: "+avg_waiting);
		System.out.println("Average Turnaroundtime Time: "+avg_turn);
		
	}
}
