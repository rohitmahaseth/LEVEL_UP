public class Mar30 {
    public static void main(String[] args){
       boolean[][] boxes= new boolean[4][4];
       System.out.println(nqueen_01_Perm(boxes, 4, ""));
    }

    public static boolean isSafeToPlaceQueen(boolean[][] boxes, int r,int c){
        int[][] dir= {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int n= boxes.length;

        for(int i= 0; i< dir.length; i++){
            for(int j= 1; j<= n; j++){
                int row= r+ j* dir[i][0];
                int col= c+ j* dir[i][1];

                if(row>= 0 && col>= 0 && row< n && col< n && boxes[row][col]== true){
                    return false;
                }
            }
        }
        return true;
    }

    public static int nqueen_01_Perm(boolean[][] boxes, int tnq, String psf) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0, n = boxes.length;
        for (int bidx = 0; bidx < n * n; bidx++) {
            int r = bidx / n, c = bidx % n;
            if (!boxes[r][c] && isSafeToPlaceQueen(boxes, r, c)) {
                boxes[r][c] = true;
                count += nqueen_01_Perm(boxes, tnq - 1, psf + "(" + r + "," + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    
}
