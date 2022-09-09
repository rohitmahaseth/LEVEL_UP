public class Basic_Sorting {

    public static void main(String[] args) {
        int[] arr = { 2, 8, 1, 3, 6, 7, 5, 4 };
        bubble_sort(arr);
        // selection_sort(arr);
        // Insertion_sort(arr);

        display(arr);
    }
    
    
    
    public static void Insertion_sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
                else {
                    break;
                }
            }
        }
    }
    
    public static void selection_sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int mi = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[mi] > arr[j])
                    mi = j;
            }
            
            swap(arr, mi, i);
        }

    }
    
    public static void bubble_sort(int[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }

    }
    
    public static void display(int[] arr) {
        for (int e : arr) {
            System.out.print(e+ " ");
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    
}
