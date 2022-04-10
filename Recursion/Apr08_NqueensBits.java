public class Apr08_NqueensBits{
    static boolean[] rows, cols, diags, antidiag;

    public static int Nqueens01(int n, int tnq, int idx, String psf){
        if(tnq== 0){         
            System.out.println(psf);
            return 1;
        }

        int count= 0;
        for(int i= idx; i< n* n; i++){
            int r= i/ n; 
            int c= i% n;

            if(!rows[r] && !cols[c] && !diags[r- c+ n- 1] && !antidiag[r+ c]){
                rows[r]= true;
                cols[c]= true;
                diags[r- c+ n- 1]= true;
                antidiag[r+ c]= true;

                count+= Nqueens01(n, tnq- 1, i+ 1, psf+ r+ "-"+ c+ ", ");

                rows[r]= false;
                cols[c]= false;
                diags[r- c+ n- 1]= false;
                antidiag[r+ c]= false;
            }
        }
        return count;
    }

    

    public static int Nqueens02(int n, int tnq, int bno, String psf) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int bidx = bno; bidx < n * n; bidx++) {
            int r = bidx / n, c = bidx % n;
            if ((row & (1 << r)) == 0 && (col & (1 << c)) == 0 && (diag & (1 << (r - c + n - 1))) == 0
                    && (adiag & (1 << (r + c))) == 0) {
                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));

                count += Nqueens02(n, tnq - 1, bidx + 1, psf + "(" + r + "," + c + ") ");

                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));
            }
        }
        return count;
    }

    static int row = 0, col = 0, diag = 0, adiag = 0;

    public static int Nqueens03(int n, int floor, String psf) {
        if (floor == n) {
            System.out.println(psf);
            return 1;
        }
        int count = 0, r= floor;
        for (int room = 0; room < n; room++) {
            int c= room;
            if ((row & (1 << r)) == 0 && (col & (1 << c)) == 0 && (diag & (1 << (r - c + n - 1))) == 0
                    && (adiag & (1 << (r + c))) == 0) {
                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));

                count += Nqueens03(n, floor+ 1, psf + "(" + r + "," + c + ") ");

                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));
            }
        }
        return count;
    }


    public static void main(String[] args) {
        // int tnq= 4;
        int n= 4;
        // rows= new boolean[n];
        // cols= new boolean[n];
        // diag= new boolean[n+ n- 1];
        // antidiag= new boolean[n+ n- 1];
        // System.out.println(Nqueens01(n, tnq, 0, ""));
        // System.out.println(Nqueens02(n, tnq, 0, ""));
        
        System.out.println(Nqueens03(n, 0, ""));
    }
}