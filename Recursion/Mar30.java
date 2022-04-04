
//NQueens
public class Mar30 {
    public static int queenCombinations(int tnq, boolean[] boxes, int idx, int qutn, String psf){
        if(qutn== tnq){
            System.out.println(psf);
            return 1;
        }

        int count= 0;
        for(int i= idx; i< boxes.length- 2+ qutn ; i++){
            count+= queenCombinations(tnq, boxes, i+ 1, qutn+ 1, psf+ "q"+ qutn+ "b"+ i+ "  ");
        }

        return count;
    }

    public static int queenPermutation(int tnq, boolean[] boxes, int qpsf, String psf){
        if(qpsf== tnq){
            System.out.println(psf);
            return 1;
        }

        int count= 0;
        for(int i= 0; i< boxes.length; i++){
            if(boxes[i]== false){
                boxes[i]= true;
                count+= queenPermutation(tnq, boxes, qpsf+ 1, psf+ "q"+ qpsf+ "b"+ i+ "  ");
                boxes[i]= false;
            }
        }
        return count;
    }

    public static int queenCombinations2D_1(int tnq, int n, int bno, int qpsf, String psf){
        if(qpsf== tnq){
            System.out.println(psf);
            return 1;
        }

        int count= 0;
        for(int i= bno; i< n* n; i++){
            int r= i/ n;
            int c= i% n;
            if(r>= 0 && r< n && c>= 0 && c< n){
                count+= queenCombinations2D_1(tnq, n, i+ 1, qpsf+ 1, psf+ "("+ r+ ","+ c+ ")"+ "  ");
            }
        }

        return count;
    }

    public static int queenPermutations2D_1(boolean[][] boxes, int tnq, int n, int qpsf, String psf){
        if(qpsf== tnq){
            System.out.println(psf);
            return 1;
        }
        
        int count= 0;
        for(int i= 0; i< n* n; i++){
            int r= i/ n;
            int c= i% n;
            if(r>= 0 && c>= 0 && r< n && c< n){
                if(boxes[r][c]== false){
                    boxes[r][c]= true;
                    count+= queenPermutations2D_1(boxes, tnq, n, qpsf+ 1, psf+ "");
                    boxes[r][c]= false;
                }
            }
        }

        return count;
    }

    public static boolean isSafe_Com(boolean[][] boxes, int r, int c){
        int[][] dir= {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int n= boxes.length;

        for(int d= 0; d< dir.length; d++){
            for(int rad= 1; rad< n; rad++){
                int row= r+ rad* dir[d][0];
                int col= c+ rad* dir[d][1];

                if(row>= 0 && col>= 0 && row< n && col < n){
                    if(boxes[row][col]== true){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static int NQueens_Combinations(boolean[][] boxes, int tnq, int qpsf, int bno, String psf){
        if(qpsf== tnq){
            System.out.println(psf);
            return 1;
        }
        int count= 0, n= boxes.length;
        for(int i= bno; i< n* n; i++){
            int r= i/ n;
            int c= i% n;
            if(boxes[r][c]== false && isSafe_Com(boxes, r, c)== true){
                boxes[r][c]= true;
                count+= NQueens_Combinations(boxes, tnq, qpsf+ 1, i+ 1, psf+ "("+ r+ ","+ c+  ")"+ "  ");
                boxes[r][c]= false;
            }
        }

        return count;
    }

    public static boolean isSafe_Per(boolean[][] boxes, int r, int c){
        int[][] dir= {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, 0}, {1, -1}};
        int n= boxes.length;

        for(int d= 0; d< dir.length; d++){
            for(int rad= 1; rad< n; rad++){
                int row= r+ rad* dir[d][0];
                int col= c+ rad* dir[d][1];

                if(row>= 0 && col>= 0 && row< n && col < n){
                    if(boxes[row][col]== true){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static int NQueens_Permutations(boolean[][] boxes, int tnq, int qpsf, String psf){
        if(qpsf== tnq){
            System.out.println(psf);
            return 1;
        }
        int count= 0, n= boxes.length;
        for(int i= 0; i< n* n; i++){
            int r= i/ n;
            int c= i% n;
            if(!boxes[r][c] && isSafe_Per(boxes, r, c)== true){
                boxes[r][c]= true;
                count+= NQueens_Permutations(boxes, tnq, qpsf+ 1, psf+ "("+ r+ ","+ c+  ")"+ "  ");
                boxes[r][c]= false;
            }
        }

        return count;
    }

    
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res=new ArrayList<>();
        int [][]board=new int[n][n];
        solveNQueens_(board,n,0,res,0);
        return res;
    }
    
    public void solveNQueens_(int [][]board,int n,int idx,List<List<String>> res,int qcount){
        if(n==qcount){
            addToList(board,n,res);
            return;
        }
        
        for(int i=idx;i<n*n;i++){
            int r=i/n;
            int c=i%n;
            
            if(isSafe(r,c,board,n)){
                board[r][c]=1;
                solveNQueens_(board,n,i+1,res,qcount+1);
                board[r][c]=0;
            }
        }
        
        
    }
    
    public boolean isSafe(int r, int c,int [][]board, int n){
        int [][]dir={{0,-1},{-1,-1},{-1,0},{-1,1}};
        
        for(int i=0;i<4;i++){
            for(int j=1;j<=n;j++){
                int nr=r+j*dir[i][0];
                int nc=c+j*dir[i][1];
                
                if(nr>=0 && nc>=0 && nr<n && nc< n){
                    if(board[nr][nc]==1){
                                    
                        return false;
                    }
                }else{
                    break;
                }
            }
        }        

        return true;
    }
    
    public void addToList(int [][]board,int n, List<List<String>> res){
        List<String> ls=new ArrayList<>();
        for(int i=0;i<n;i++){
            String s="";
            for(int j=0;j<n;j++){
                if(board[i][j]==1){
                    s+='Q';
                }else{
                    s+='.';
                }
            }
            
            ls.add(s);
        }
        
        res.add(ls);
    }

    public static void main(String[] args){      
        boolean[][] boxes= new boolean[4][4];
        // System.out.println(queenCombinations(3, boxes, 0, 0, ""));
        // System.out.println(queenPermutation(3, boxes, 0, ""));
        // System.out.println(queenCombinations2D_1(4, 4, 0, 0, ""));
        // System.out.println(queenPermutations2D_1(boxes, 4, 4, 0, ""));
        // System.out.println(NQueens_Combinations(boxes, 4, 0, 0, ""));
        // System.out.println(NQueens_Permutations(boxes, 4, 0, ""));
    }

    //workBreak leetcode
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set= new HashSet<>();
        for(String str: wordDict)
            set.add(str);

        return word_break(s, set);       
    }

    public static boolean word_break(String str, HashSet<String> set){
        if(str.length()== 0){
            return true;
        }

        for(int len= 1; len< str.length(); len++){
            String smallStr= str.substring(0, len);
            if(set.contains(smallStr)){
                if(word_break(str.substring(len), set))
                    return true;
            }
        }

        return false;
    }

    //sudoku solver
    //1st Method

    public void solveSudoku(char[][] board) {
        ArrayList<Integer> list= new ArrayList<>();
        int n= 9;
        for(int i= 0; i< n; i++){
            for(int j= 0; j< n; j++){
                if(board[i][j]== '.'){
                    list.add(i* n+ j);
                }
            }
        }
        
        sudokuSolver(board, list, 0);
    }
    
    private boolean sudokuSolver(char[][] board, ArrayList<Integer> list, int idx){
        if(idx== list.size()){
            return true;
        }
        
        int r= list.get(idx)/ 9;
        int c= list.get(idx)% 9;
        
        for(int num= 1; num<= 9; num++){
            if(isPossible(board, r, c, num)){
                board[r][c]= (char)('0'+ num);
                // System.out.print(num+ "  ");
                if(sudokuSolver(board, list, idx+ 1)){
                    return true;
                }
                board[r][c]= '.';
            }
        }
        
        return false;
    }
    
    private boolean isPossible(char[][] board, int row, int col, int num){
        //row
        for(int i= 0; i< 9; i++){
            if(board[i][col]== (char)('0'+ num)){
                return false;
            }
        }
        
        //col
        for(int i= 0; i< 9; i++){
            if(board[row][i]== (char)('0'+ num)){
                return false;
            }
        }
        
        row= (row/ 3)* 3;
        col= (col/ 3)* 3;
        
        for(int i= 0; i< 3; i++){
            for(int j= 0; j< 3; j++){
                if(board[row+ i][col+ j]- '0'== num){
                    return false;
                }
            }
        }      
        
        return true;
        
    }
    
}


//2nd Method
    public void solveSudoku(char[][] board) {
        ArrayList<Integer> list= new ArrayList<>();
        int n= 9;
        for(int i= 0; i< n; i++){
            for(int j= 0; j< n; j++){
                if(board[i][j]== '.'){
                    list.add(i* n+ j);
                }
            }
        }
        
        boolean ans= sudokuSolver(board, list, 0);
    }
    
    private boolean sudokuSolver(char[][] board, ArrayList<Integer> list, int idx){
        if(idx== list.size()){
            return true;
        }
        
        int r= list.get(idx)/ 9;
        int c= list.get(idx)% 9;
        
        for(int num= 1; num<= 9; num++){
            if(isPossible(board, r, c, num)){
                board[r][c]= (char)('0'+ num);
                if(sudokuSolver(board, list, idx+ 1)){
                    return true;
                }
                board[r][c]= '0';
            }
        }
        
        return false;
    }
    
    private boolean isPossible(char[][] board, int row, int col, int num){
        int[][] dir= {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        
        for(int i= 0; i< dir.length; i++){
            for(int rad= 1; rad<= 9; rad++){
                int r= row+ rad* dir[i][0];
                int c= col+ rad* dir[i][1];
                if(r>= 0 && c>= 0 && r< 9 && c< 9){
                    if(board[r][c]== (char)('0'+ num) ){
                        return false;
                    }
                }   
                else
                    break;
            }
        }
        
        int i= 0;
        if(row>= 0 && row<= 2){
            i= 0;
        }
        else if(row>= 3 && row<= 5){
            i= 3;
        }
        else if(row>= 6 && row<= 8){
            i= 6;
        }
        
        int j= 0;
        if(col>= 0 && col<= 2){
            j= 0;
        }
        else if(col>= 3 && col<= 5){
            j= 3;
        }
        else if(col>= 6 && col<= 8){
            j= 6;
        }
        
        for(int n= i; n<= i+ 2; n++){
            for(int m= j; m<= j+ 2; m++){
                if(board[n][m]== (char) ('0'+ num)){
                    return false;
                }                   
            }
        }
        
        return true;
        
    }
    
    
    
}
