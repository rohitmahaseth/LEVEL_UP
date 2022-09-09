public class Quick_sort {
    public static void main(String[] args) {
        int[] arr = { 2, 8, 1, 3, 6, 7, 5, 4 };

        quickSort(arr, 0, arr.length - 1);
        display(arr);
    }
    
    public static void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int pivot = arr[hi];
        int pi = Partion(arr, pivot, lo, hi);
        quickSort(arr, lo, pi - 1);
        quickSort(arr, pi+ 1, hi);
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
