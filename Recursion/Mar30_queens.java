import java.util.*;
//NQueens
public class Mar30_queens {
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

    //sudoku solver leetcode
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

//2nd method
    boolean [][] rows= new boolean[10][10];
    boolean [][] cols= new boolean[10][10];
    boolean [][][] mat= new boolean[3][3][10];
    public void solveSudoku02(char[][] board) {
        ArrayList<Integer> list= new ArrayList<>();
        int n= board.length;
        for(int i= 0; i< n; i++){
            for(int j= 0; j< n; j++){
                if(board[i][j]== '.'){
                    list.add(i* n+ j);
                }
                else{
                    int num= (int)(board[i][j]- '0');
                    rows[i][num]= cols[j][num]= mat[i/ 3][j/ 3][num]= true;
                }
            }
        }
        
        sudokuSolver02(board, list, 0);
    }
    
    public boolean sudokuSolver02(char[][] board, ArrayList<Integer> list, int idx){
        int n= board.length;
        if(idx== list.size()){
            return true;
        }
        
        int r= list.get(idx)/ n;
        int c= list.get(idx)% n;
        
        for(int num= 1; num<= 9; num++){
            if(!rows[r][num] && !cols[c][num] && !mat[r/ 3][c/ 3][num]){
                rows[r][num]= cols[c][num]= mat[r/ 3][c/ 3][num]= true;
                
                board[r][c]= (char) (num+ '0');
                if(sudokuSolver02(board, list, idx+ 1)){
                    return true;
                }
                board[r][c]= '.';
                
                rows[r][num]= cols[c][num]= mat[r/ 3][c/ 3][num]= false;
            }
        }
        
        return false;
    }  

    //3rd method (using bit)
    int[] row= new int[10];
    int[] col= new int[10];
    int[][] mats= new int[3][3];
    public void solveSudoku03(char[][] board) {
        ArrayList<Integer> list= new ArrayList<>();
        int n= board.length;
        for(int i= 0; i< n; i++){
            for(int j= 0; j< n; j++){
                if(board[i][j]== '.'){
                    list.add(i* n+ j);
                }
                else{
                    int num= board[i][j]- '0';
                    int mask= 1<< num;
                    row[i]^= mask;
                    col[j]^= mask;
                    mats[i/ 3][j/ 3]^= mask;
                }
            }
        }
        
        sudokuSolver03(board, list, 0);
    }
    
    public boolean sudokuSolver03(char[][] board, ArrayList<Integer> list, int idx){
        int n= board.length;
        if(idx== list.size()){
            return true;
        }
        
        int r= list.get(idx)/ n;
        int c= list.get(idx)% n;
        
        for(int num= 1; num<= 9; num++){
            int mask= 1<< num;
            if((row[r] & mask)== 0 && (col[c] & mask)== 0 && (mats[r/ 3][c/ 3] & mask)== 0){
                
                row[r]^= mask;
                col[c]^= mask;
                mats[r/ 3][c/ 3]^= mask;
                
                board[r][c]= (char) (num+ '0');
                if(sudokuSolver03(board, list, idx+ 1)){
                    return true;
                }
                board[r][c]= '.';
                
                
                row[r]^= mask;
                col[c]^= mask;
                mats[r/ 3][c/ 3]^= mask;
            }
        }
        
        return false;
    }

//cryto Matic

    static String str1 = "send", str2 = "more", str3 = "money";
    static boolean[] isNumUsed = new boolean[10];
    static int[] mapping= new int[26];

    private static int stringToNum(String str){
        int res= 0;
        for(int i= 0; i< str.length(); i++){
            int val= mapping[str.charAt(i)- 'a'];
            res= res* 10+ val;
        }

        return res;
    }
    
    public static boolean isValidMapping(){
        if(mapping[str1.charAt(0)- 'a']== 0){
            return false;
        }

        if(mapping[str2.charAt(0)- 'a']== 0){
            return false;
        }

        if(mapping[str2.charAt(0)- 'a']== 0){
            return false;
        }

        int num1= stringToNum(str1);
        int num2= stringToNum(str2);
        int num3= stringToNum(str3);

        return (num1+ num2== num3);

    }

    public static int crypto(String str, int idx) {
        if (idx == str.length()) {
            if (isValidMapping()) {
                for(int i= 0; i< str.length(); i++){
                    char ch= str.charAt(i);
                    System.out.print(ch+ "="+ mapping[ch- 'a']+ ", ");
                }
                System.out.println();
                return 1;
            }

            return 0;
        }

        int count = 0;
        char ch= str.charAt(idx);
        for (int num = 0; num <= 9; num++) {
            if (!isNumUsed[num]) {
                isNumUsed[num] = true;
                mapping[ch- 'a']= num;

                count += crypto(str, idx + 1);
                
                mapping[ch- 'a']= 0;
                isNumUsed[num] = false;
            }

        }
        return count;
    }

    public static void crypto() {
        String str = str1 + str2 + str3;
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a']++;
        }

        str = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0)
                str += (char) (i + 'a');
        }

        if (str.length() > 10)
            return;

        crypto(str, 0);
    }

    public static void main(String[] args){      
        // boolean[][] boxes= new boolean[4][4];
        // System.out.println(queenCombinations(3, boxes, 0, 0, ""));
        // System.out.println(queenPermutation(3, boxes, 0, ""));
        // System.out.println(queenCombinations2D_1(4, 4, 0, 0, ""));
        // System.out.println(queenPermutations2D_1(boxes, 4, 4, 0, ""));
        // System.out.println(NQueens_Combinations(boxes, 4, 0, 0, ""));
        // System.out.println(NQueens_Permutations(boxes, 4, 0, ""));
        crypto();
    }
 
}
