public class DP_27Apr2Pointer{
    /*
        * 1. Faith
        * 2. Tree Diagram
        * 3. Rscursion
        * 4. Rscursion -> Memoization
        * 5. Obervation
        * 6. Memoization -> Tabulation after observation
        * 7. Optimization
     */

    public static void display1D(int[] dp){
        for(int ele: dp){
            System.out.print(ele+ " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp){
        for(int[] d: dp){
            display1D(d);
        }
    }

    public static int fibo_memo(int n, int[] dp){
        if(n== 0 || n== 1)
            return dp[n]= n;
        if(dp[n]!= 0)
            return dp[n];

        return dp[n]= fibo_memo(n- 1, dp)+ fibo_memo(n- 2, dp);
    }

    public static int fibo_tabu(int N, int[] dp){
        for(int n= 0; n<= N; n++){
            if(n== 0 || n== 1){
                dp[n]= n;
                continue;
            }               
            dp[n]= dp[n- 1]+ dp[n- 2]; 
        }
        return dp[N];
    }

    public static int fibo_opt(int n){
        int i= 0, j= 1;
        for(int k= 0; k< n; k++){
            int sum= i+ j;
            i= j;
            j= sum;
        }
        return i;
    }

    // leetcode 1137
    public static int tribonacci_memo(int n, int[] dp){
        if(n<= 2){
            return dp[n]= n<= 1? n: 1;
        }
        if(dp[n]!= 0){
            return dp[n];
        }

        return dp[n]= tribonacci_memo(n- 1, dp)+ tribonacci_memo(n- 2, dp)+ tribonacci_memo(n- 3, dp);
    }

    public static int tribonacci_tabu(int N, int[] dp){
        for(int n= 0; n<= N; n++){
            if(n<= 2){
                dp[n]= n<= 1? n: 1;
                continue;
            }
    
            dp[n]= dp[n- 1]+ dp[n- 2]+ dp[n- 3];
        }
        return dp[N];
    }

    public static int tribonacci_opt(int n){
        int i= 0, j= 1, k= 1;
        for(int t= 0; t< n; t++){
            int sum= i+ j+ k;
            i= j;
            j= k;
            k= sum;
        }
        return i;
    }

    // leetcode 70
    public static int climbStairs(int n) {
        if(n== 0 || n== 1){
            return 1;
        }
        int ans= 0;
        // 1 ka jump
        ans+= climbStairs(n- 1);

        // 2 ka jump
        ans+= climbStairs(n- 2);

        return ans;
    }

    public static int climbStairs_memo(int n, int[] dp) {
        if(n== 0 || n== 1)
            return 1;
        if(dp[n]!= 0)
            return dp[n];

        return dp[n]= climbStairs_memo(n- 1, dp)+ climbStairs_memo(n- 2, dp);
    }

    public static int climbStairs_tabu(int N, int[] dp) {
        for(int n= 0; n<= N; n++){
            if(n== 0 || n== 1){
                dp[n]=  1;
                continue;
            }

            dp[n]= dp[n- 1]+ dp[n- 2];
        }
        return dp[N];
    }

    // leetcode 746
    public int minCostClimbingStairs(int[] cost, int n) {
        if(n<= 1)
            return cost[n];

        return (int) Math.max(minCostClimbingStairs(cost, n- 1), minCostClimbingStairs(cost, n- 2))+ cost[n];
    }

    public static int minCostClimbingStairs_memo(int[] cost, int n, int[] dp) {
        if(n<= 1)
            return dp[n]= cost[n];
        if(dp[n]!= 0)
            return dp[n];

        return dp[n]= (int) Math.min(minCostClimbingStairs_memo(cost, n- 1, dp), minCostClimbingStairs_memo(cost, n- 2, dp))+ 
                            (n== cost.length? 0: cost[n]);
    }

    public static int minCostClimbingStairs_tabu(int[] cost, int N, int[] dp) {
        for(int n= 0; n<= N; n++){
            if(n<= 1){
                dp[n]= cost[n];
                continue;
            }            
            dp[n]= (int) Math.min(dp[n- 1], dp[n- 2]) + (n== cost.length? 0: cost[n]);
        }
        return dp[N];
    }

    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    public static long countFriendsPairings(int n){
        if(n<= 0)
            return 1;
        
        return countFriendsPairings(n- 1) + (n- 1)* countFriendsPairings(n- 2);
    }

    public static long countFriendsPairings_memo(int n, long[] dp){
        if(n<= 1)
            return dp[n]= 1;
        if(dp[n]!= 0)
            return dp[n];

        long single= countFriendsPairings_memo(n- 1, dp);
        long pairUp= (n- 1)* countFriendsPairings_memo(n- 2, dp);
         
        return dp[n]= single + pairUp;
    }

    public static long countFriendsPairings_tabu(int N, long[] dp){
        long mod= (long) 1e9 + 7;
        for(int n= 0; n<= N; n++){
            if(n<= 1){
                dp[n]= 1;
                continue;
            }

            long single= dp[n- 1]% mod;
            long pairUp= (n- 1)* dp[n- 2]% mod;
            
            dp[n]= (single + pairUp)% mod;
        }  
        return dp[N];     
    }

    public static int mazePath_memo(int er, int ec, int[][] dir, int[][] dp){
        if(er== 0 && ec== 0){
            return dp[er][ec]= 1;
        }
        if(dp[er][ec]!= 0){
            return dp[er][ec];
        }

        int path= 0;
        for(int d= 0; d< dir.length; d++){
            int r= er+ dir[d][0];
            int c= ec+ dir[d][1];

            if(r>= 0 && c>= 0){
                path+= mazePath_memo(r, c, dir, dp);
            }
        }
        return dp[er][ec]= path;
    }

    public static int mazePath_tabu(int ER, int EC, int[][] dir, int[][] dp){
        for(int er= 0; er<= ER; er++){
            for(int ec= 0; ec<= EC; ec++){
                if(er== 0 && ec== 0){
                    dp[er][ec]= 1;
                    continue;
                }
        
                int path= 0;
                for(int d= 0; d< dir.length; d++){
                    int r= er+ dir[d][0];
                    int c= ec+ dir[d][1];
        
                    if(r>= 0 && c>= 0 && r< dp.length && c< dp[0].length){
                        path+= dp[r][c];
                    }
                }
                dp[er][ec]= path;
            }
        }
        return dp[ER][EC];
    }

    public static int mazePathJump_memo(int er, int ec, int[][] dir, int[][] dp){
        if(er== 0 && ec== 0){
            return dp[er][ec]= 1;
        }
        if(dp[er][ec]!= 0){
            return dp[er][ec];
        }
        int count= 0;
        for(int d= 0; d< dir.length; d++){
            for(int j= 1; j<= Math.max(er, ec); j++){
                int r= er+ j* dir[d][0];
                int c= ec+ j* dir[d][1];

                if(r>= 0 && c>= 0){
                    count+= mazePathJump_memo(r, c, dir, dp);
                }
            }
        }
        return dp[er][ec]= count;
    }

    public static int mazePathJump_tabu(int ER, int EC, int[][] dir, int[][] dp){
        for(int er= 0; er<= ER; er++){
            for(int ec= 0; ec<= EC; ec++){
                if(er== 0 && ec== 0){
                    dp[er][ec]= 1;
                    continue;
                }
                int count= 0;
                for(int d= 0; d< dir.length; d++){     
                    int r= er+ dir[d][0];
                    int c= ec+ dir[d][1];
    // ******** note down this trick of doing jumps questions **************************
                    while(r>= 0 && c>= 0 && r<= dp.length && c<= dp[0].length){
                        count+= dp[r][c];
                        r+= dir[d][0];
                        c+= dir[d][1];
                    }  
                }
                dp[er][ec]= count;
            }
        }
        return dp[ER][EC];
    }

    public static void mazePath(){
        int n= 3, m= 3;
        int er= n- 1, ec= m- 1;
        int[][] dir= {{-1, 0}, {-1, -1}, {0, -1}};
        int[][] dp= new int[n][m];
        System.out.println(mazePathJump_tabu(er, ec, dir, dp));
        display2D(dp);
    }
   

    public static void main(String[] args){
        // int[] cost= {1,100,1,1,1,100,1,1,100,1};
        // int[] dp= new int[cost.length+ 1];
        // System.out.println(minCostClimbingStairs_tabu(cost, cost.length, dp));
        // display1D(dp);
        // int n= 4;
        // long[] dp= new long[n+ 1];
        // System.out.println(countFriendsPairings_tabu(n, dp));
        mazePath();
    }
}