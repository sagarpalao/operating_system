import java.io.*;
import java.util.*;

class Priority{

	static int burst[],arrival[];
	public static void main(String args[]){
            
		int p[][]=new int[10][5];
		int i,j,tmp,n;
		Scanner scr=new Scanner(System.in);
                
		System.out.println("Enter number of processes: ");
		n=scr.nextInt();
		System.out.println("Enter Processes Details: ");
		for(i=0;i<n;i++){
			
			p[i][0]=i+1;
			System.out.println("For Process "+(i+1)+": ");
			System.out.print("Enter Burst time: ");
			p[i][1]=scr.nextInt();
			System.out.print("Enter Priority: ");
			p[i][3]=scr.nextInt();
                        System.out.print("Enter arrival Time: ");
                        p[i][2]=scr.nextInt();
			
		}
		priority(p,n);	
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
	
	static void priority(int p[][],int n){

		int time=0,i,cnt,cnt2;
                int pp[][]=new int[10][5];
                int ppp[][]=new int[100][5];
                
                burst=new int[n];
		arrival=new int[n];
		for(i=0;i<n;i++){
			burst[i]=p[i][1];
                        arrival[i]=p[i][2];
		}
                cnt2=0;
                while(true){
                    cnt=0;
                    for(i=0;i<n;i++){
                        if(p[i][2]<=time && p[i][1]!=0){
                            pp[cnt][0]=p[i][0];
                            pp[cnt][1]=p[i][1];
                            pp[cnt][2]=p[i][2];
                            pp[cnt][3]=p[i][3];
                            cnt++;
                        }
                    }
                    int high=0;
                    for(i=0;i<cnt;i++){
                        if(pp[high][3]>pp[i][3])
                            high=i;
                    }
                    if(p[pp[high][0]-1][1]!=0){
                        ppp[cnt2][0]=pp[high][0]-1;
                        ppp[cnt2][1]=time;
                        ppp[cnt2][2]=time+1;
                        time++;
                        cnt2++;
                        p[pp[high][0]-1][1]-=1;
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
                printAvg(ppp,cnt2,n);
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
				ppp[cnt][2]=p[i][2]-arrival[p[i][0]];        
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
