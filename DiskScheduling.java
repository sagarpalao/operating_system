import java.util.*;

public class DiskScheduling {
    
    public static void main(String...args){
        
        int i,j,x=0,tmp;
        Scanner scr=new Scanner(System.in);
        System.out.print("Enter no. of access: ");
        int n=scr.nextInt();
        int r[][]=new int[n][2];
        int p[]=new int[n+2];
        System.out.print("Enter current position: ");
        int cur=scr.nextInt();
        System.out.println("Enter disk request pattern: ");
        for(i=0;i<n;i++){
            r[i][0]=scr.nextInt();
            r[i][1]=0;
        }
        System.out.println("1.FCFS 2.SSTF 3.SCAN 4.C-SCAN 5.LOOK 6.C-LOOK");
        int ch=scr.nextInt();
        
        switch(ch){
            case 1:
                for(i=0;i<n;i++){
                    p[i]=r[i][0];
                    r[i][1]=1;
                }
                x=n;
                break;
            case 2:
                int dist=211;
                int shortest=-1;
                for(j=0;j<n;j++){
                    for(i=0;i<n;i++){
                        if(Math.abs(r[i][0]-cur)<dist && r[i][1]==0){
                            shortest=i;
                            dist=Math.abs(r[i][0]-cur);
                        }                   
                    }
                    r[shortest][1]=1;
                    p[j]=r[shortest][0];
                    cur=p[j];
                    dist=211;
                    x=n;
                }
                break;
            case 3:
                x=0;
                for(i=0;i<n-1;i++){
                    for(j=0;j<n-1;j++){
                        if(r[j][0]>r[j+1][0]){
                            tmp=r[j][0];
                            r[j][0]=r[j+1][0];
                            r[j+1][0]=tmp;
                        }
                    }
                }
                for(i=0;i<n;i++){
                    if(r[i][0]>cur){
                        break;
                    }
                }
                for(j=i-1;j>=0;j--){
                    p[x]=r[j][0];
                    r[j][1]=1;
                    x++;
                }
                p[x]=0;
                x++;
                for(j=i;j<n;j++){
                    p[x]=r[j][0];
                    r[j][1]=1;
                    x++;
                }
                p[x]=199;
                x++;
                break;
            case 4:
                x=0;
                for(i=0;i<n-1;i++){
                    for(j=0;j<n-1;j++){
                        if(r[j][0]>r[j+1][0]){
                            tmp=r[j][0];
                            r[j][0]=r[j+1][0];
                            r[j+1][0]=tmp;
                        }
                    }
                }
                for(i=0;i<n;i++){
                    if(r[i][0]>cur){
                        break;
                    }
                }
                for(j=i-1;j>=0;j--){
                    p[x]=r[j][0];
                    r[j][1]=1;
                    x++;
                }
                p[x]=0;
                x++;
                p[x]=199;
                x++;
                for(j=n-1;j>i;j--){
                    p[x]=r[j][0];
                    r[j][1]=1;
                    x++;
                }  
                break;
            case 5:
                x=0;
                for(i=0;i<n-1;i++){
                    for(j=0;j<n-1;j++){
                        if(r[j][0]>r[j+1][0]){
                            tmp=r[j][0];
                            r[j][0]=r[j+1][0];
                            r[j+1][0]=tmp;
                        }
                    }
                }
                for(i=0;i<n;i++){
                    if(r[i][0]>cur){
                        break;
                    }
                }
                for(j=i-1;j>=0;j--){
                    p[x]=r[j][0];
                    r[j][1]=1;
                    x++;
                }
                
                for(j=i;j<n;j++){
                    p[x]=r[j][0];
                    r[j][1]=1;
                    x++;
                }
                
                break;
            case 6:
                x=0;
                for(i=0;i<n-1;i++){
                    for(j=0;j<n-1;j++){
                        if(r[j][0]>r[j+1][0]){
                            tmp=r[j][0];
                            r[j][0]=r[j+1][0];
                            r[j+1][0]=tmp;
                        }
                    }
                }
                for(i=0;i<n;i++){
                    if(r[i][0]>cur){
                        break;
                    }
                }
                for(j=i-1;j>=0;j--){
                    p[x]=r[j][0];
                    r[j][1]=1;
                    x++;
                }
              
                for(j=n-1;j>i;j--){
                    p[x]=r[j][0];
                    r[j][1]=1;
                    x++;
                }  
                
                break;                        
        }
        
        System.out.println("Acess Sequence: ");
        for(i=0;i<x;i++){
            System.out.println(p[i]);
        }
    }
    
}
