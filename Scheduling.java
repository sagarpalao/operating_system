import java.io.*;
import java.util.*;

public class Scheduling {
    
    static int burst[],arrival[];
    static void display(int p[][],int n,int x){
        int i,j;
        for(i=0;i<n;i++){
                for(j=0;j<x;j++){
                        System.out.print("\t"+p[i][j]+"\t");
                }
                System.out.println();
        }
    }
    
    static void printAvgFCFS(int p[][],int n){
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
    
    static void printAvgRR(int p[][],int n,int pr){
		
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
            display(ppp,cnt,3);		
            for(i=0;i<pr;i++){
                    avg_waiting=avg_waiting+ppp[i][1];
                    avg_turn=avg_turn+ppp[i][2];
            }
            avg_waiting=avg_waiting/pr;
            avg_turn=avg_turn/pr;
            System.out.println("\nAverage Waiting Time: "+avg_waiting);
            System.out.println("Average Turnaroundtime Time: "+avg_turn);
		
	}
    
    static void printAvgP(int p[][],int n,int pr){	
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
        display(ppp,cnt,3);

        for(i=0;i<pr;i++){
                avg_waiting=avg_waiting+ppp[i][1];
                avg_turn=avg_turn+ppp[i][2];
        }
        avg_waiting=avg_waiting/pr;
        avg_turn=avg_turn/pr;
        System.out.println("\nAverage Waiting Time: "+avg_waiting);
        System.out.println("Average Turnaroundtime Time: "+avg_turn);
		
    }
    
    static void printAvgSJFP(int p[][],int n,int pr){
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
                            ppp[cnt][2]=p[i][2]-arrival[p[i][0]-1];
                            ppp[cnt][1]=ppp[cnt][2]-burst[p[i][0]-1];
                            visited[p[i][0]]=true;
                            cnt++;
                    }
            }
            System.out.println("\n\n");
            System.out.println("\tProcess\tWaiting T.\tTurnaround T.");
            System.out.println("-------------------------------------------------------------------------------------------");
            //display(ppp,cnt);

            for(i=0;i<pr;i++){
                    avg_waiting=avg_waiting+ppp[i][1];
                    avg_turn=avg_turn+ppp[i][2];
            }
            avg_waiting=avg_waiting/pr;
            avg_turn=avg_turn/pr;
            System.out.println("\nAverage Waiting Time: "+avg_waiting);
            System.out.println("Average Turnaroundtime Time: "+avg_turn);
    }
    
    static void printAvgSJFNP(int p[][],int n){
		
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
    static void fcfs(int p[][],int n){
        int i,j,sum,tmp;

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
        System.out.println("\tProcess\tBurst T.\tArrival T.\tWaiting T.\tTurnaround T.");
	System.out.println("-------------------------------------------------------------------------------------------");
        display(p,n,5);
        printAvgFCFS(p,n);
    }
    
    static void sjfnp(int p[][],int n){

            int pp[][]=new int[n][5];
            int timeInstant=0;
            int x,i,j,small,copy_n,sum,tmp;
            
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
            System.out.println("\tProcess\tBurst T.\tArrival T.\tWaiting T.\tTurnaround T.");
            System.out.println("-------------------------------------------------------------------------------------------");
            display(pp,n,5);
            printAvgSJFNP(pp,n);
    }
    
    static void sjfp(int p[][],int n){
        int time=0,cnt,cnt2,i;
                    int pp[][]=new int[10][5];
                    int ppp[][]=new int[1000][5];

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
                                //pp[cnt][3]=p[i][3];
                                cnt++;
                            }
                        }
                        
                        //display(pp,cnt);
                        
                        int shortest=0;
                        for(i=0;i<cnt;i++){
                            if(pp[shortest][1]>pp[i][1])
                                shortest=i;
                        }
                       
                        //System.out.println(cnt2+" "+shortest);
                        ppp[cnt2][0]=pp[shortest][0];
                        ppp[cnt2][1]=time;
                        ppp[cnt2][2]=time+1;
                        time++;
                        cnt2++;
                        p[pp[shortest][0]-1][1]-=1;
                        
                        //display(ppp,cnt);
                        
                        for(i=0;i<n;i++){
                            if(p[i][1]!=0){
                                break;
                            }
                        }
                        if(i==n){
                            break;
                        }
                        
                        //System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                    } 
                    printAvgP(ppp,cnt2,n);
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
                printAvgP(ppp,cnt2,n);
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
		printAvgRR(pp,cnt,n);		
	}
	
	public static void printGantt(int p[][],int n){
        
        int i,j=-1,nj,sum_burst=0,cnt=0;
        System.out.print(sum_burst);
        cnt++;
        for(i=0;i<n;i++){
            nj=p[i][0];
            if(nj==j){
                sum_burst=sum_burst+p[i][0];
            }
            else{               
                sum_burst=sum_burst+p[i][1];
                System.out.print("  P"+p[i][0]+"   "+sum_burst);
                cnt++;
            }
            j=nj;
        }
        System.out.println();
        for(i=0;i<cnt;i++){
            if(i==cnt-1){
                System.out.print("|");
            }
            else{
                System.out.print("|-------");
            }
        }
        System.out.println();
    }
    public static void printGanttP(int p[][],int n){
        
        int i,j,nj,sum_burst=0,cnt=0;
        System.out.print(sum_burst);
        cnt++;
        j=p[0][0];
        for(i=0;i<n;i++){
            nj=p[i][0];
            System.out.print("  P"+p[i][0]);
            while(nj==j){
                i++;
                j=p[i][0];
            }                
            System.out.print("   "+p[i-1][2]);
            cnt++;   
            j=nj;
        }
        System.out.println();
        for(i=0;i<cnt;i++){
            if(i==cnt-1){
                System.out.print("|");
            }
            else{
                System.out.print("|-------");
            }
        }
        System.out.println();
    }
    
    public static void main(String arga[]){
        int p[][]=new int[10][5];
		int i,j,n;
		Scanner scr=new Scanner(System.in);
                
                System.out.println("Enter choice:\n1.FCFS\n2.SJF (Preemptive)\n3.SJF (Non preemptive)\n4.RR\n5.Priority");
                int ch=scr.nextInt();
                
		System.out.println("Enter number of processes: ");
		n=scr.nextInt();
		System.out.println("Enter Processes Details:\n");
		
                switch(ch){
                    case 1:
                            for(i=0;i<n;i++){	
                                p[i][0]=i+1;
                                System.out.println("For Process "+(i+1)+": ");
                                System.out.print("Enter Burst time: ");
                                p[i][1]=scr.nextInt();
                                System.out.print("Enter Arrival Time: ");
                                p[i][2]=scr.nextInt();	
                            }
                            fcfs(p,n);
                        break;
                    case 2:
                            for(i=0;i<n;i++){	
                                p[i][0]=i+1;
                                System.out.println("For Process "+(i+1)+": ");
                                System.out.print("Enter Burst time: ");
                                p[i][1]=scr.nextInt();
                                System.out.print("Enter Arrival Time: ");
                                p[i][2]=scr.nextInt();	
                            }
                            sjfp(p,n);
                        break;
                    case 3:
                            for(i=0;i<n;i++){	
                                p[i][0]=i+1;
                                System.out.println("For Process "+(i+1)+": ");
                                System.out.print("Enter Burst time: ");
                                p[i][1]=scr.nextInt();
                                System.out.print("Enter Arrival Time: ");
                                p[i][2]=scr.nextInt();	
                            }
                            sjfnp(p,n);
                        break;
                    case 4:
                            for(i=0;i<n;i++){	
                                p[i][0]=i+1;
                                System.out.println("For Process "+(i+1)+": ");
                                System.out.print("Enter Burst time: ");
                                p[i][1]=scr.nextInt();	
                            }
                            System.out.print("Enter Time Quantum: ");
                            int tq=scr.nextInt();
                            rr(p,n,tq);
                        break;
                    case 5:
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
			break;
                }    
    }
}
