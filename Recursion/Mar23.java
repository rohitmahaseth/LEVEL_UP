public class Mar23{
    public static void main(String[] args){
        // System.out.println(mazePath(0, 0, 2, 2, ""));
        // System.out.println(mazePath1(0, 0, 2, 2, dir, dirS, ""));

        int[][] dir= {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        String[] dirS= {"d", "l", "u", "r"};

        boolean[][] vis= new boolean[3][3];
        System.out.println(floodfill(0, 0, 2, 2, vis, dir, dirS, ""));

    }

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

}