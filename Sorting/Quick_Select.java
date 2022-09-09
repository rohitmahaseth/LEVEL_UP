public class Quick_Select {
    public static void main(String[] args) {
        int[] arr = { 2, 8, 1, 3, 6, 7, 5, 4 };
        int k = 3; // u have to find 4th largest number from the array

        int ans = quickSelect(arr, k - 1, 0, arr.length - 1);
        System.out.println(ans);

    }
    
    public static int quickSelect(int[] arr, int k, int lo, int hi) {
        
        int pi_idx = Partion(arr, arr[hi], lo, hi);
        while (pi_idx != k) {      
            if (pi_idx < k) {
                lo= pi_idx+ 1;
                pi_idx = Partion(arr, arr[hi], lo, hi);
                if (pi_idx > k)
                    hi = pi_idx- 1;
                else
                    lo = pi_idx+ 1;
            }
            else {
                hi= pi_idx- 1;
                pi_idx = Partion(arr, arr[hi], lo, hi);
                if(pi_idx> k)
                    hi = pi_idx- 1;
                else
                    lo = pi_idx + 1;
            }
        }
        return arr[pi_idx];
    }

    public static int Partion(int[] arr, int pi, int lo, int hi) {
        // 0- i- 1  ---> less than and equal to pi
        // i- j- 1  ---> greater than pi
        // j- end   ---> unknown

        int i= lo, j= lo;
        while (j <= hi) {
            if (arr[j] > pi) {
                j++;
            } else {
                swap(arr, i, j);
                i++;
                j++;
            }
        }
        return i - 1;
    }

    public static void swap(int[] arr, int i, int j){
        int temp= arr[i];
        arr[i]= arr[j];
        arr[j]= temp;
    }
    
    public static void display(int[] arr){
        for(int e: arr){
            System.out.print(e+ " ");
        }
    }
}
