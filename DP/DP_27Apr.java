public class DP_27Apr{

    // leetcode 1137
    public static int tribonacci(int n) {
        // if(n<= 1){
        //     return n;
        // }
        // else if(n== 2){
        //     return 1;
        // }
        
        // return tribonacci(n- 1)+ tribonacci(n- 2)+ tribonacci(n- 3);
        // int[] dp= new int[n+ 1];
        return tri_tab_opt(n);
    }
        
    //     public int tri_mem(int n, int[] dp){
    //         if(n<= 2){
    //             return dp[n]= (n== 0 ? 0: 1);
    //         }
            
    //         if(dp[n]!= 0){
    //             return dp[n];
    //         }
            
    //         return dp[n]= tri_mem(n- 1, dp)+ tri_mem(n- 2, dp)+ tri_mem(n- 3, dp);
    //     }
        
    //     public int tri_tab(int N, int[] dp){
    //         for(int n= 0; n<= N; n++){
    //             if(n<= 2){
    //                 dp[n]= (n== 0 ? 0: 1);
    //                 continue;
    //             }
    //             dp[n]= dp[n- 1]+ dp[n- 2]+ dp[n- 3];
    //         }
    //         return dp[N];
    //     }
        
    public static int tri_tab_opt(int N){
        int a= 0, b= 1, c= 1;
        for(int n= 0; n< N; n++){
            int sum= a+ b+ c;
            a= b;
            b= c; 
            c= sum;
        }
        return a;
    }
    
// leetcode 746
    public int minCostClimbingStairs(int[] cost) {
        // int[] dp= new int[cost.length+ 1];
        // return minCostClimbingStairs_mem(cost, cost.length, dp);
        // return minCostClimbingStairs_tab(cost, cost.length, dp);
        return minCostClimbingStairs_tab_opt(cost);
    }
    
//     public int minCostClimbingStairs(int[] cost, int n){
//         if(n<= 1)
//             return cost[n];
        
//         int temp= (n== cost.length? 0: cost[n]);
//         return temp+ Math.min( minCostClimbingStairs(cost, n- 1), minCostClimbingStairs(cost, n- 2) );
//     }
    
//     public int minCostClimbingStairs_mem(int[] cost, int n, int[] dp){
//         if(n<= 1)
//             return dp[n]= cost[n];
//         if(dp[n]!= 0)
//             return dp[n];
        
//         int temp= (n== cost.length? 0: cost[n]);
//         return dp[n]= temp+ Math.min( minCostClimbingStairs_mem(cost, n- 1, dp), minCostClimbingStairs_mem(cost, n- 2, dp) );
//     }
    
//     public int minCostClimbingStairs_mem(int[] cost, int n, int[] dp){
//         if(n<= 1)
//             return dp[n]= cost[n];
//         if(dp[n]!= 0)
//             return dp[n];
        
//         int temp= (n== cost.length? 0: cost[n]);
//         return dp[n]= temp+ Math.min( minCostClimbingStairs_mem(cost, n- 1, dp), minCostClimbingStairs_mem(cost, n- 2, dp) );
//     }
    
    // public int minCostClimbingStairs_tab(int[] cost, int N, int[] dp){
    //     for(int n= 0; n<= N; n++){
    //         if(n<= 1){
    //             dp[n]= cost[n];
    //             continue;
    //         }
    //         int temp= (n== cost.length? 0: cost[n]);
    //         dp[n]= temp+ Math.min( dp[n- 1], dp[n- 2] );                
    //     }
    //     return dp[N];
    // }
    
    public int minCostClimbingStairs_tab_opt(int[] cost){
        int N= cost.length;
        int a= cost[0], b= cost[1];
        for(int n= 2; n<= N; n++){
            int temp= (n== cost.length? 0: cost[n]);
            int next= temp+ Math.min(a, b);
            a= b;
            b= next;
        }
        return b;
    }

    public static void main(String[] args){
        // System.out.println(tribonacci(7));

    }
        
}