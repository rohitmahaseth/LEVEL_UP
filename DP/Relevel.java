import java.util.*;
import java.io.*;
public class Relevel {
    public static void main(String[] args){
        try (Scanner scn = new Scanner(System.in)) {
            int n= scn.nextInt();
            int[] arr= new int[n];
            for(int i= 0; i< n; i++){
                arr[i]= scn.nextInt();
            }
            HashSet<String> set= new HashSet<>();
            totalCount(arr, 0, 
            0, "", set);
            System.out.println(set.size());
        }
    }

    public static void totalCount(int[] arr, int count, int idx, String ssf, HashSet<String> set){
        
        if(count== arr.length- 3){
            HashSet<Integer> index= new HashSet<>();
            for(int i= 0; i< ssf.length(); i++){
                index.add(ssf.charAt(i)- '0');
            }

            String str= "";
            for(int i= 0; i< arr.length; i++){
                if(!index.contains(i)){
                    str+= arr[i];
                }
            }
            System.out.println(str);
            set.add(str);
            return;
        }

        if(idx== arr.length){
            return;
        }
        // yes
        totalCount(arr, count+ 1, idx+ 1, ssf+ idx, set);

        // no
        totalCount(arr, count, idx+ 1, ssf, set);
    }
}
