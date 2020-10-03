import java.io.*;
import java.util.*;

class LRU{
	
	static long timestamp[][]=new long[100][2];
	static int n;

	public static long getTimestamp(int i){
		for(int x=n-1;x>=0;x--){
			if(timestamp[x][0]==i){
				return timestamp[x][1];
			}
		}
		return 0;
	}

	public static void main(String...args){
		
		Scanner scr=new Scanner(System.in);
		
		System.out.println("Enter no. of pages in stream: ");
		n=scr.nextInt();
		int stream[]=new int[n];
		
		int frame[]=new int[n];
		int i,x,j,k,small,misno=0;
		
		System.out.println("Enter frame size: ");
		int fsize=scr.nextInt();
		int full=0;
		
		System.out.println("Enter stream:");
		for(i=0;i<n;i++){
			System.out.print("Sequence "+i+" :");
			stream[i]=scr.nextInt();
			
			timestamp[i][1]=new Date().getTime();
			timestamp[i][0]=stream[i];
				
			for(k=0;k<12;k++){
				for(j=0;j<2;j++){
					System.out.print(timestamp[k][j]);
				}
				System.out.println();
			}
			for(j=0;j<fsize;j++){
				if(stream[i]==frame[j]){
					break;
				}
			}
				if(j==fsize){
					if(full==fsize){
						misno++;
						x=0;
						small=x;
						for(x=0;x<fsize;x++){
																if(getTimestamp(frame[small])>getTimestamp(frame[x])){
								small=x;
							}
						}
						frame[small]=stream[i];
					}
					else{
						frame[full]=stream[i];
						full++;
					}
				}
				else{	
					
				}
			
			for(x=0;x<fsize;x++){
				System.out.print(frame[x]+"\t");
				
			}
			System.out.println();
		}
                
                double misratio=(double)misno/n;
                double hitratio=1-misratio;
                System.out.println("Hit Ratio: "+hitratio+"\nMiss Ratio: "+misratio);
	}
}
