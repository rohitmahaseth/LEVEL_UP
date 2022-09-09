import java.util.*;;

public class DP_2MayLeetcode {
    public static void display1D(int[] dp){
        for(int e: dp){
            System.out.print(e+ " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp){
        for(int[] d: dp){
            display1D(d);
        }
    }

    // leetcode 62
    public static int uniquePaths_memo(int er, int ec, int[][] dir, int[][] dp) {
        if(er== 0 && ec== 0){
            return dp[er][ec]= 1;
        }
        if(dp[er][ec]!= 0){
            return dp[er][ec];
        }
        int count= 0;
        for(int d= 0; d< dir.length; d++){
            int r= er+ dir[d][0];
            int c= ec+ dir[d][1];

            if(r>= 0 && c>= 0){
                count+= uniquePaths_memo(r, c, dir, dp);
            }
        }
        return dp[er][ec]= count;
    }

    public static int uniquePaths_tabu(int ER, int EC, int[][] dir, int[][] dp) {
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
        
                    if(r>= 0 && c>= 0){
                        count+= dp[r][c];
                    }
                }
                dp[er][ec]= count;
            }
        }
        return dp[ER][EC];
    }

    // leetcode 63
    public static int uniquePathsWithObstacles_memo(int[][] obstacleGrid, int er, int ec, int[][] dir, int[][] dp) {
        if(er== 0 && ec== 0){
            return dp[er][ec]= 1;
        }
        if(dp[er][ec]!= 0){
            return dp[er][ec];
        }
        int count= 0;
        for(int d= 0; d< dir.length; d++){
            int r= er+ dir[d][0];
            int c= ec+ dir[d][1];

            if(r>= 0 && c>= 0 && obstacleGrid[r][c]!= 1){
                count+= uniquePathsWithObstacles_memo(obstacleGrid, r, c, dir, dp);
            }
        }
        return dp[er][ec]= count;
    }

    public static int uniquePathsWithObstacles_tabu(int[][] obstacleGrid, int ER, int EC, int[][] dir, int[][] dp) {
        for(int er= 0; er<= ER; er++){
            for(int ec= 0; ec<= EC; ec++){
                if(obstacleGrid[er][ec]== 1){
                    dp[er][ec]= 0;
                    continue;
                }
                if(er== 0 && ec== 0){
                    dp[er][ec]= 1;
                    continue;
                }
            
                int count= 0;
                for(int d= 0; d< dir.length; d++){
                    int r= er+ dir[d][0];
                    int c= ec+ dir[d][1];
        
                    if(r>= 0 && c>= 0 && r<= ER && c<= EC && obstacleGrid[r][c]!= 1){
                        count+= dp[r][c];
                    }
                }
                dp[er][ec]= count;
            }
        }
        return dp[ER][EC];
    }

    // leetcode 396
    public static int maxRotateFunction(int[] nums) {
        int n= nums.length;
        int sum= 0, sumSoFar= 0, max= -1;
        for(int i= 0; i< n; i++){
            sum+= nums[i];
            sumSoFar+= i* nums[i];
        }

        max= sumSoFar;
        for(int i= 0; i< n; i++){
            sumSoFar= sumSoFar- sum + n* nums[i];
            max= Math.max(sumSoFar, max);
        }
        return max;
    }

    // leetcode 64
    public static int minPathSum_memo(int[][] grid, int er, int ec, int[][] dir, int[][] dp) {
        if(er== 0 && ec== 0)
            return dp[er][ec]= grid[er][ec];
        if(dp[er][ec]!= 0)
            return dp[er][ec];

        int min_sum= (int) 1e9;
        for(int d= 0; d< dir.length; d++){
            int r= er+ dir[d][0];
            int c= ec+ dir[d][1];

            if(r>= 0 && c>= 0){
                min_sum= Math.min(( minPathSum_memo(grid, r, c, dir, dp)), min_sum);
            }
        }
        return dp[er][ec]= min_sum+ grid[er][ec];
    }

    public static int minPathSum_tabu(int[][] grid, int ER, int EC, int[][] dir, int[][] dp) {
        for(int er= 0; er<= ER; er++){
            for(int ec= 0; ec<= EC; ec++){
                if(er== 0 && ec== 0){
                    dp[er][ec]= grid[er][ec];
                    continue;
                }  

                int min_sum= (int) 1e9;
                for(int d= 0; d< dir.length; d++){
                    int r= er+ dir[d][0];
                    int c= ec+ dir[d][1];

                    if(r>= 0 && c>= 0){
                        min_sum= Math.min(dp[r][c], min_sum);
                    }
                }
                dp[er][ec]= min_sum+ grid[er][ec];
            }
        }
        return dp[ER][EC];
    }
    

    // https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1/
    static int maxGold_tabu(int n, int m, int M[][], int[][] dp){
        for(int j= m; j>= 0; j--){
            for(int i= 0; i<= n; i++){
                if(j== m ){
                    dp[i][j]= M[i][j];
                }
                else if(i== 0){
                    dp[i][j]= M[i][j]+ Math.max(dp[i][j+ 1], dp[i+ 1][j+ 1]);
                }
                else if(i== n){
                    dp[i][j]= M[i][j]+ Math.max(dp[i][j+ 1], dp[i- 1][j+ 1]);
                }
                else{
                    dp[i][j]= M[i][j]+ Math.max(dp[i][j+ 1], Math.max(dp[i- 1][j+ 1], dp[i+ 1][j+ 1]));
                }
            }
        }

        int max= -1;
        for(int i= 0; i<= n; i++){
            max= Math.max(max, dp[i][0]);
        }
        return max;
    }

    public static int maxGold_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp, int[][] M){
        if(sc== ec){
            return dp[sr][sc]= M[sr][sc];
        }
        if(dp[sr][sc]!= -1){
            return dp[sr][sc];
        }

        int max= 0;
        for(int d= 0; d< dir.length; d++){
            int r= sr+ dir[d][0];
            int c= sc+ dir[d][1];

            if(r>= 0 && c>= 0 && r< dp.length && c< dp[0].length){
                max= Math.max(max, maxGold_memo(r, c, er, ec, dir, dp, M));
            }
        }
        return dp[sr][sc]= max+ M[sr][sc];
    }

    public static int  maxGold(int n, int m){
        int[][] dir= {{0, 1}, {-1, 1}, {1, 1}};
        int[][] dp= new int[n][m];
        int[][] M = {{1, 3, 1, 5},{2, 2, 4, 1},{5, 0, 2, 3},{0, 6, 1, 2}};
        
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }
        int max= 0;
        for(int r= 0; r< n; r++){
            max= Math.max(max, maxGold_memo(r, 0, n- 1, m- 1, dir, dp, M));
            System.out.print(max+ " ");
        }
        return max;
    }

    // https://www.geeksforgeeks.org/count-the-number-of-ways-to-divide-n-in-k-groups-incrementally/
    // Count the number of ways to divide N in k groups incrementally
    public static int totalWays(int N, int K, int idx, int count, int sum){
        if(count== K ){
            if(sum== N){
                return 1;
            }
            return 0;
        }
        int ways= 0;
        for(int i= idx; i<= N; i++){
            ways+= totalWays(N, K, i, count+ 1, sum+ i);
        }
        return ways;
    }

    public static int totalWays(int N, int K){
        return totalWays(N, K, 1, 0, 0);
    }


    public static void main(String[] args) {
        // int[][] dir= {{-1, 0}, {0, -1}};
        // int[][] grid= {{1,3,1},{1,5,1},{4,2,1}};
        // int[][] dp= new int[grid.length][grid[0].length];
        
        // System.out.println(minPathSum_tabu(grid, grid.length- 1, grid[0].length- 1, dir, dp));
        // display2D(dp);
        // int[][] M= {{1, 3, 3},{2, 1, 4},{0, 6, 4}};
        // int n= M.length, m= M[0].length;
        // int[][] dp= new int[n][m];
        // System.out.println(maxGold_tabu(n- 1, m- 1, M, dp));
        // System.out.println(maxGold(4, 4));
        System.out.println(totalWays(24, 5));
    }
}
