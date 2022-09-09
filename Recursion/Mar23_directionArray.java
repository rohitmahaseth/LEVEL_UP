import java.util.*;
public class Mar23_directionArray{

    //without dir array
    public static int mazePath(int sr, int sc, int er, int ec, String psf){
        if(sr== er && sc== ec){
            System.out.println(psf);
            return 1;
        }

        int count= 0;
        if(sc+ 1<= ec){
            count+= mazePath(sr, sc+ 1, er, ec, psf+ "h");
        }
        if(sr+ 1<= er){
            count+= mazePath(sr+ 1, sc, er, ec, psf+ "v");
        }
        if(sc+ 1<= ec && sr+ 1<= er){
            count+= mazePath(sr+ 1, sc+ 1, er, ec, psf+ "d");
        }

        return count;
    }

    //with dir array
    public static int mazePath1(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf){
        if(sr== er && sc== ec){
            System.out.println(psf);
            return 1;
        }

        int count= 0;
        for(int d= 0; d< dir.length; d++){
            int r= sr+ dir[d][0];
            int c= sc+ dir[d][1];
            
            if(r<= er && c<= ec){
                count+= mazePath1(r, c, er, ec, dir, dirS, psf+ dirS[d]);
            }
        }

        return count;
    }

    public static int mazePathJumps(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf){
        if(sr== er && sc== ec){
            System.out.println(psf);
            return 1;
        }

        int count= 0;
        for(int i= 0; i< dir.length; i++){
            for(int j= 1; j<= Math.max(dr,dc); j++){
                int r= sr+ j* dir[i][0];
                int c= sc+ j* dir[i][1];

                if(r>= 0 && c>= 0 && r<= er && c<= ec){
                    count+= mazePathJumps(r, c, er, ec, dir, dirS, psf+ dirS[i]+ j);
                }
                else{
                    break; //further jumps is going to be higher than this jumps so no need to look for them
                }
            }
        }
        return count;
    }
    
    public static int floodfill(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS, String psf){
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        vis[sr][sc] = true;
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c]) {
                count += floodfill(r, c, er, ec, vis, dir, dirS, psf + dirS[d]);
            }
        }
        vis[sr][sc] = false;
        return count;
    }

    public static int floodFillJump(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS, String psf){
        if(sr== er && sc== ec){
            System.out.println(psf);
            return 1;
        }

        vis[sr][sc]= true;
        int count= 0;
        for(int d= 0; d< dir.length; d++){
            // why I am getting wrong answer for this "rad+ sr<= er && rad+ sc<= ec" but getting right for "rad <= Math.max(er, ec)"
            for(int rad= 1; rad <= Math.max(er, ec); rad++){
                int r= sr+ rad* dir[d][0];
                int c= sc+ rad* dir[d][1];

                if(r>= 0 && c>= 0 && r<= er && c<= ec ){
                    if(!vis[r][c])
                        count+= floodFillJump(r, c, er, ec, vis, dir, dirS, psf+ dirS[d]+ rad);
                }
                else{
                    break;
                }
            }
        }
        vis[sr][sc]= false;
        return count;
    }

    public int uniquePaths(int m, int n) {
        if(m== 1 && n== 1){
            return 1;
        }
        
        if(m== 0 || n== 0){
                return 0;
        }
        
        int right= uniquePaths(m, n- 1);
        int down= uniquePaths(m- 1, n);
    
        return right+ down;        
      }
   
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m= obstacleGrid.length;
        int n= obstacleGrid[0].length;
        
        if(obstacleGrid[0][0]== 1 || obstacleGrid[m- 1][n- 1]== 1){
            return 0;
        }
        
        int[][] dir= {{0, 1}, {1, 0}};
        
        return uniquePaths2(0, 0, m-1, n- 1, obstacleGrid, dir);
    }
    
    public static int uniquePaths2(int sr, int sc, int er, int ec, int[][] obstacleGrid, int[][] dir){
        if(sr== er && sc== ec){
            return 1;
        }
        
        int count= 0;
        for(int d= 0; d< dir.length; d++){
            int r= sr+ dir[d][0];
            int c= sc+ dir[d][1];
            
            if(r<= er && c<= ec && obstacleGrid[r][c]== 0){
                count+= uniquePaths2(r, c, er, ec, obstacleGrid, dir);
            }
        }
        
        return count;
    }
    
    public static ArrayList<String> findPath(int[][] m, int n) {
        // Your code here
        if(m[0][0]== 0 || m[n- 1][n- 1]== 0){
            ArrayList<String> ba= new ArrayList<>();
            ba.add("-1");
            return ba;
        }
        
        int[][] dir= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        String[] dirS= {"U", "D", "L", "R"};
        
       return rat_Maze(0, 0, n- 1, n- 1, dir, dirS, m, "");
    }
    
    public static ArrayList<String> rat_Maze(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, int[][] m, String psf){
        if(sr== er && sc== ec){
            ArrayList<String> ba= new ArrayList<>();
            ba.add(psf);
            return ba;
        }
        
        ArrayList< String> Myans= new ArrayList<>();
        m[sr][sc]= 0;
        for(int d= 0; d< dir.length; d++){
            int r= sr+ dir[d][0];
            int c= sc+ dir[d][1];
            
            if(r>= 0 && c>= 0 && r<= er && c<= ec && m[r][c]== 1){
                ArrayList<String> ans= rat_Maze(r, c, er, ec, dir, dirS, m, psf+ dirS[d]);
                
                for(int i= 0; i< ans.size(); i++){
                    Myans.add(ans.get(i));
                }
            }
            
        }
        m[sr][sc]= 1;
        
        return Myans;
    }

    int shortestDistance(int N, int M, int A[][], int X, int Y) {
        // code here
        if(A[0][0]== 0 || A[X][Y]== 0){
            return -1;
        }
        int[][] dir= {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        
        return shorDis(0, 0, A, X, Y, dir);
    }
    
    public static int shorDis(int sr, int sc, int[][] A, int X, int Y, int[][] dir){
        if(sr== X && sc== Y){
            return 0;
        }
        
        A[sr][sc]= 0;
        int min= (int) 1e9; 
        for(int d= 0; d< dir.length; d++){
            int r= sr+ dir[d][0];
            int c= sc+ dir[d][1];
            
            if(r>= 0 && c>= 0 && r<= A.length- 1 && c<= A[0].length- 1 && A[r][c]== 1){
                int val= shorDis(r, c, A, X, Y, dir);
                if(val+ 1< min){
                    min= val+ 1;
                }
            }
        }
        A[sr][sc]= 1;
        return min;
    }    
    
    public static class pairSP{
        int len= (int)1e9;
        String str= "";
    }

    public static pairSP shortestPath(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, boolean[][] mat){
        if(sr== er && sc== ec){
            pairSP base= new pairSP();
            base.len= 0;

            return base;
        }
        
        pairSP ans= new pairSP();
        mat[sr][sc]= true;
        for(int d= 0; d< dir.length; d++){
            int r= sr+ dir[d][0];
            int c= sc+ dir[d][1];

            if(r>= 0 && c>= 0 && r<= er && c<= ec && mat[r][c]== false){
                pairSP val= shortestPath(r, c, er, ec, dir, dirS, mat);
                if(val.len!= (int)1e9 && val.len+ 1< ans.len){
                    ans.len= val.len+ 1;
                    ans.str= val.str+ dirS[d];
                }
            }
        }
        mat[sr][sc]= false;
        return ans;
    }

      public static void main(String[] args){
        // System.out.println(mazePath(0, 0, 2, 2, ""));
        // System.out.println(mazePath1(0, 0, 2, 2, dir, dirS, ""));
        // System.out.println(mazePathJumps(0, 0, 2, 2, dir, dirS, ""));
        // System.out.println(floodfill(0, 0, 2, 2, vis, dir, dirS, ""));
        // System.out.println(floodFillJump(0, 0, 2, 2, vis, dir, dirS, ""));
        
        int[][] dir= {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        String[] dirS= {"d", "r", "u", "l"};
        boolean[][] vis= new boolean[4][4];

        pairSP val= shortestPath(0, 0, 3, 3, dir, dirS, vis);
        System.out.println(val.len+ " "+ val.str);

    }

}


