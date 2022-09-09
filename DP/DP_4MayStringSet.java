import java.util.*;
import java.io.*;
public class DP_4MayStringSet {
    public static void display1D(int[] d){
        for(int e: d){
            System.out.print(e+ " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp){
        for(int[] d: dp){
            display1D(d);
        }
    }

    // leetcode 516
    public static int longestPalindromeSubseq_memo(String s, int i, int j, int[][] dp){
        if(i>= j) return dp[i][j]= i== j? 1: 0;
        if(dp[i][j]!= 0)
            return dp[i][j];

        int a= longestPalindromeSubseq_memo(s, i+ 1, j- 1, dp);
        int b= longestPalindromeSubseq_memo(s, i, j- 1, dp);
        int c= longestPalindromeSubseq_memo(s, i+ 1, j, dp);

        if(s.charAt(i)== s.charAt(j)){
            return dp[i][j]= 2+ a;
        }
        else{
            return dp[i][j]= Math.max(b, c);
        }

    }

    public static int longestPalindromeSubseq_tabu(String s, int I, int J, int[][] dp){
        for(int gap= 0; gap< s.length(); gap++){
            for(int i= 0, j= gap; j< s.length(); i++, j++){
                if(i>= j){
                    dp[i][j]= i== j? 1: 0;
                    continue;
                }       

                if(s.charAt(i)== s.charAt(j)){
                    dp[i][j]= 2+ dp[i+ 1][j- 1];
                }
                else{
                    dp[i][j]= Math.max(dp[i][j- 1], dp[i+ 1][j]);
                }                  
            }
        }
        return dp[I][J];
    }

    public static void longestPalindromeSubseq(String s) {
        int n= s.length();
        int[][] dp= new int[n][n];
        System.out.println(longestPalindromeSubseq_tabu(s, 0, n- 1, dp));
        display2D(dp);
    }

    // leetcode 1143
   
    public static int longestCommonSubsequence_memo(String s1, String s2, int n, int m, int[][] dp){
        if(n== 0 || m== 0)
            return dp[n][m]= 0;
        if(dp[n][m]!= 0)
            return dp[n][m];

        int a= longestCommonSubsequence_memo(s1, s2, n- 1, m- 1, dp);
        int b= longestCommonSubsequence_memo(s1, s2, n- 1, m, dp);
        int c= longestCommonSubsequence_memo(s1, s2, n, m- 1, dp);

        if(s1.charAt(n- 1)== s2.charAt(m- 1)){
            return dp[n][m]= a+ 1;
        }
        else{
            return dp[n][m]= Math.max(b, c);
        }
    }

    public static int longestCommonSubsequence_tabu(String s1, String s2, int N, int M, int[][] dp){
        for(int n= 0; n<= N; n++){
            for(int m= 0; m<= M; m++){
                if(n== 0 || m== 0){
                    dp[n][m]= 0;
                    continue;
                }
               
                if(s1.charAt(n- 1)== s2.charAt(m- 1)){
                    dp[n][m]= dp[n- 1][m- 1]+ 1;
                }
                else{
                    dp[n][m]= Math.max(dp[n- 1][m], dp[n][m- 1]);
                }
            }
        }
        return dp[N][M];
    }

    public static void longestCommonSubsequence(String text1, String text2) {
        int n= text1.length(), m= text2.length();
        int[][] dp= new int[n+ 1][m+ 1];
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }
        System.out.println(longestCommonSubsequence_tabu(text1, text2, n, m, dp)); 
        display2D(dp);      
    }

    // leetcode 72
    public static int minDistance_memo(String s1, String s2, int n, int m, int[][] dp) {
        if(n== 0 || m== 0){
            return n!= 0? n: m;
        }
        if(dp[n][m]!= -1)
            return dp[n][m];

        int a= minDistance_memo(s1, s2, n- 1, m- 1, dp);
        int b= minDistance_memo(s1, s2, n- 1, m, dp); //delete
        int c= minDistance_memo(s1, s2, n- 1, m- 1, dp); //replace
        int d= minDistance_memo(s1, s2, n, m- 1, dp); //insert

        if(s1.charAt(n- 1)== s2.charAt(m- 1)){
            return dp[n][m]= a;
        }
        else{
            return dp[n][m]= Math.min( Math.min(b, c), d)+ 1;
        }
 
    }

    public static int minDistance_tabu(String s1, String s2, int N, int M, int[][] dp) {
        for(int n= 0; n<= N; n++){
            for(int m= 0; m<= M; m++){
                if(n== 0 || m== 0){
                    dp[n][m]=  n!= 0? n: m;
                    continue;
                }
        
                if(s1.charAt(n- 1)== s2.charAt(m- 1)){
                    dp[n][m]= dp[n- 1][m- 1];
                }
                else{
                    dp[n][m]= Math.min( Math.min(dp[n- 1][m], dp[n- 1][m- 1]), dp[n][m- 1])+ 1;
                }
            }
        }     
        return dp[N][M];
    }

    public static void minDistance(String word1, String word2) {
        int n= word1.length(), m= word2.length();
        int[][] dp= new int[n+ 1][m+ 1];
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }
        System.out.println(minDistance_tabu(word1, word2, n, m, dp));
        display2D(dp);
    }

    // leetcode 72 followup **** del, rep, ins cost will be different *********************
    public static int minDistance_followUp_tabu(String s1, String s2, int N, int M, int[][] dp) {
        for(int n= 0; n<= N; n++){
            for(int m= 0; m<= M; m++){
                if(n== 0 || m== 0){
                    dp[n][m]=  n!= 0? n: m;
                    continue;
                }
        
                if(s1.charAt(n- 1)== s2.charAt(m- 1)){
                    dp[n][m]= dp[n- 1][m- 1];
                }
                else{
                    dp[n][m]= Math.min( Math.min(dp[n- 1][m]+ 5, dp[n- 1][m- 1]+ 3), dp[n][m- 1]+ 2);
                }
            }
        }     
        return dp[N][M];
    }
    public static void minDistance_followUp(String word1, String word2) {
        int n= word1.length(), m= word2.length();
        int[][] dp= new int[n+ 1][m+ 1];
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }
        // int[] cost= {2, 5, 3};
        System.out.println(minDistance_followUp_tabu(word1, word2, n, m, dp));
        display2D(dp);
    }

    //leetcode 115
    public static void numDistinct(String s1, String s2) {
        int n= s1.length(), m= s2.length();
        int[][] dp=new int[n+ 1][m+ 1];
        // for(int[] d: dp){
        //     Arrays.fill(d, -1);
        // }
        System.out.println(numDistinct_tabu(s1, s2, n, m, dp));
        display2D(dp);
    }

    public static int numDistinct(String s1, String s2, int n, int m, int[][] dp){
        if(m== 0 || n== 0){
            return dp[n][m]= m== 0? 1: 0;
        }
        if(dp[n][m]!= -1){
            return dp[n][m];
        }

        int a= numDistinct(s1, s2, n- 1, m, dp);
        int b= numDistinct(s1, s2, n- 1, m- 1, dp);

        if(s1.charAt(n- 1)== s2.charAt(m- 1)){
            return dp[n][m]= a+ b;
        }
        else{
            return dp[n][m]= a;
        }
    }

    public static int numDistinct_tabu(String s1, String s2, int N, int M, int[][] dp){
        for(int n= 0; n<= N; n++){
            for(int m= 0; m<= M; m++){
                if(m== 0 || n== 0){
                    dp[n][m]= m== 0? 1: 0;
                    continue;
                }
               
                if(s1.charAt(n- 1)== s2.charAt(m- 1)){
                    dp[n][m]= dp[n- 1][m]+ dp[n- 1][m- 1];
                }
                else{
                    dp[n][m]= dp[n- 1][m];
                }
            }
        }
        return dp[N][M];
    }

    // leetcode 583
    public int minDistance_(String text1, String text2) {
        int n= text1.length(), m= text2.length();
       int[][] dp= new int[n+ 1][m+ 1];

       int ans= longestCommonSubsequence_tabu(text1, text2, n, m, dp); 
       return n+ m- 2*ans;
   }

   //leetcode 1035
    public static void maxUncrossedLines(int[] nums1, int[] nums2) {
        int n= nums1.length, m= nums2.length;
        int[][] dp= new int[n+ 1][m+ 1];
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }
        System.out.println(maxUncrossedLines_tabu(nums1, nums2, n, m, dp));
        display2D(dp);
    }

    public static int maxUncrossedLines_memo(int[] nums1, int[] nums2, int n, int m, int[][] dp){
        if(n== 0 || m== 0){
            return dp[n][m]= 0;
        }
        if(dp[n][m]!= -1){
            return dp[n][m];
        }
        int a= maxUncrossedLines_memo(nums1, nums2, n- 1, m- 1, dp);
        int b= maxUncrossedLines_memo(nums1, nums2, n- 1, m, dp);
        int c= maxUncrossedLines_memo(nums1, nums2, n, m- 1, dp);

        if(nums1[n- 1]== nums2[m- 1]){
            return dp[n][m]= a+ 1;
        }
        else{
            return dp[n][m]= Math.max(b, c);
        }   
    }

    public static int maxUncrossedLines_tabu(int[] nums1, int[] nums2, int N, int M, int[][] dp){
        for(int n= 0; n<= N; n++){
            for(int m= 0; m<= M; m++){
                if(n== 0 || m== 0){
                    dp[n][m]= 0;
                    continue;
                }
        
                if(nums1[n- 1]== nums2[m- 1]){
                    dp[n][m]=  dp[n- 1][m- 1]+ 1;
                }
                else{
                    dp[n][m]= Math.max(dp[n- 1][m], dp[n][m- 1]);
                }   
            }
        }
        return dp[N][M];
    }

    //leetcode 5
    // Longest Palindromic Substring
    public static String longestPalindromicSubstring(String s){
        int n= s.length();
        String str= "";
        int[][] dp= new int[n][n];
  
        int count= 0;
        for(int gap= 0; gap< n; gap++){
            for(int i= 0, j= gap; j< n; i++, j++){
                if(gap== 0){
                    dp[i][j]= 1;
                }
                else if(gap== 1){
                    dp[i][j]= s.charAt(i)== s.charAt(j)? 2: 0;
                }
                else{
                    dp[i][j]= s.charAt(i)== s.charAt(j) && dp[i+ 1][j- 1]> 0? dp[i+ 1][j- 1]+ 2: 0;
                }

                if(dp[i][j]> str.length()){
                    str= s.substring(i, j+ 1);
                }
                
                count+= dp[i][j]> 0? 1: 0;
            }
        }
        return str;
    }

    // https://www.geeksforgeeks.org/longest-common-substring-dp-29/
    // Longest Common Substring

    public static int  longestCommonSubstring(String s1, String s2){
        int n= s1.length(), m= s2.length();
        int[][] dp= new int[n+ 1][m+ 1];

        int ans= -1;
        for(int i= 0; i<= n; i++){
            for(int j= 0; j<= m; j++){
                if(i== 0 || j== 0){
                    dp[i][j]= i== j? 1: 0;
                }
                else if(s1.charAt(i- 1)== s2.charAt(j- 1)){
                    dp[i][j]= dp[i- 1][j- 1]+ 1;
                }
                else{
                    dp[i][j]= 0;
                }
                ans= Math.max(ans, dp[i][j]);
            }
        }
        display2D(dp);
        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1
    // Count subsequences of type a^i, b^j, c^k 
    public static int fun(String s){
        int aCount= 0, bCount= 0, cCount= 0;

        for(int i= 0; i< s.length(); i++){
            char ch= s.charAt(i);
            if(ch== 'a'){
                aCount= (aCount+ (1+ aCount)% (int) (1e9+ 7))% (int) (1e9+ 7);
            }
            else if(ch== 'b'){
                bCount= (bCount + (aCount+ bCount)% (int) (1e9+ 7))% (int) (1e9+ 7);
            }
            else if(ch== 'c'){
                cCount=(cCount+ (bCount+ cCount)% (int) (1e9+ 7))% (int) (1e9+ 7);
            }
        }
        return cCount% (int) (1e9+ 7);
    }


    public static void main(String[] args){
        System.out.println(fun("abcabc"));
        
    }
}
