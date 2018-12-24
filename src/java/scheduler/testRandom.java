/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author TOSHIBA
 */
public class testRandom {
    static int [] practIndex=new int[]{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28};
    
    public static ArrayList<Integer> get() {
        
        ArrayList<Integer> ll=new ArrayList<>();
      Random  r=new Random();
                boolean[] flag1=new boolean[practIndex.length];
                int practNo[]=new int[practIndex.length];
                for (int j = 0; j < practIndex.length; j++) {
                    
                    int r1;
                    while (flag1[r1=r.nextInt(practIndex.length)]==true) {                        
                        
                    }
                    flag1[r1]=true;
                     practNo[j]=(0*(practIndex.length)+r1);  
    }
                for (int i = 0; i < practNo.length; i++) {
            int j = practNo[i];
                  ll.add(j);
        }
               // Collections.sort(ll);
                
             //   System.out.println(""+ll);
                
                return ll;
                
    }
    
    public static int[] addPos(int[] a, int pos, int num) {
    int[] result = new int[a.length];
    
    
    
    for(int i = 0; i < pos; i++)
        result[i] = a[i];
    
    result[pos] = num;
    
    
    for(int i = pos + 1; i < result.length; i++)
        result[i] = a[i - 1];
    return result;
}
    
    
    public static void main(String[] args) {
       int [] r= addPos(practIndex, 10, 10);
       
        r= addPos(practIndex, 10, 10);
        for (int i = 0; i < r.length; i++) {
            int j = r[i];
            System.out.println(""+j);
        }
    }
    
    
}
