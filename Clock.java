import java.io.*;
import java.util.*;

public class Clock {
    
    public static void main(String...args){
        
        int fsize,n,i,j,miscnt=0,full=0;
        Scanner scr=new Scanner(System.in);
        
        System.out.print("Enter Page Stream Size: ");
        n=scr.nextInt();
        System.out.print("Enter Frame Size: ");
        fsize=scr.nextInt();
        
        int stream[]=new int[n];
        int frame[][]=new int[fsize][2];
        int ptr=0;
        
        for(i=0;i<n;i++){
            System.out.print("\nSequence "+i+" : ");
            stream[i]=scr.nextInt();
            
            for(j=0;j<fsize;j++){
                if(frame[j][0]==stream[i]){
                    break;
                }
            }
            if(j==fsize){
                if(full==fsize){
                    miscnt++;                   
                    while(true){
                        if(frame[ptr][1]==1){
                            frame[ptr][1]=0;
                            ptr=(ptr+1)%fsize;
                        }
                        else{
                            frame[ptr][0]=stream[i];
                            frame[ptr][1]=1;
                            ptr=(ptr+1)%fsize;
                            break;
                        }
                    }
                }
                else{
                    frame[full][0]=stream[i];
                    frame[full][1]=1;
                    full++;
                    ptr=(ptr+1)%fsize;
                }
            }
            else{
                ptr=(j+1)%fsize;
            }
            
            for(j=0;j<fsize;j++){
                System.out.print("\t"+frame[j][0]);
            }
        }
        
        double misratio=(double)miscnt/n;
        double hitratio=1-misratio;
        System.out.println("\nHit Ratio: "+hitratio+"\nMiss Ratio: "+misratio);
    }
}
