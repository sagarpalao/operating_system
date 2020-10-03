import java.io.*;
import java.util.*;

class Paging{
	
	static int logical[]=new int[32];
	
	static int decimal(){		
		int sum=0,i;	
		for(i=31;i>=12;i--){
			sum=sum+(int)(logical[i]*Math.pow(2,i-12));
		}		
		return sum;
	}
	
	static int[] binary(int no){
		int binary[]=new int[20];		
		int j=19,rem;
		while(no!=0){
			rem=no%2;
			binary[j]=rem;
			no=no/2;		
			j--;
		}		
		return binary;		
	}
	
	public static void main(String...args){
		int n=32;
		int offset=12;
		int pagenumber=20;
		int frame[][]=new int[1048576][1];
		int process_size;
		int page_size=4096;
		int i,j;
		
		Scanner scr=new Scanner(System.in);
		
		System.out.println("Enter process size: ");
		process_size=scr.nextInt();
		
		int no_pages=process_size/page_size;
		int pagetable[][]=new int[no_pages][2];
		
		for(i=0;i<no_pages;i++){
			for(j=0;j<1048576;j++){
				if(frame[j][0]==0){
					break;
				}
			}
			pagetable[i][0]=j*4096;
			pagetable[i][1]=1;
			frame[j][0]=1;
		}
		System.out.println("Page Table: ");
		System.out.println("Page Index  :  Base Address");
		System.out.println("------------------------");
		for(i=0;i<no_pages;i++){
			System.out.println(i+"       :  "+pagetable[i][0]);
		}
		
		System.out.println("Enter logical address (32 bits): ");
		for(i=31;i>=0;i--){
			logical[i]=scr.nextInt();
		}
		
		for(i=31;i>=0;i--){
			System.out.print(logical[i]);
		}
		
		int page_index=decimal();
		System.out.println(page_index);
		int baseaddress=0;
		
		if(pagetable[page_index][1]==1)
			baseaddress=pagetable[page_index][0];
		else
			System.out.println("invalid address");
				
		int base[]=binary(baseaddress);
		int physical[]=new int[32];
				
		for(i=11,j=11;i>=0;i--,j--){
			physical[i]=logical[j];
		}		
		for(i=31,j=19;i>=12;i--,j--){
			physical[i]=base[j];
		}
		
		System.out.println("\n\nPhysical address");
		for(i=31;i>=0;i--){
			if(i==11){
				System.out.print(" ");
			}
			System.out.print(physical[i]);
		}
	}
}
