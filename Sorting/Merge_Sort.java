public class Merge_Sort {
    public static void main(String[] args) {
        int[] arr = { 2, 8, 1, 3, 6, 7, 5, 4 };
        int[] ans = merge_sort(arr, 0, arr.length - 1);
        
        display(ans);
    }

    public static int[] merge_sort(int[] arr, int lo, int hi) {
        if (lo == hi) {
            int[] base = new int[1];
            base[0] = arr[lo];
            return base;
        }
        
        int mid = (lo + hi) / 2;
        int[] left = merge_sort(arr, lo, mid);
        int[] right = merge_sort(arr, mid + 1, hi);
        
        int[] ans = merge(left, right);
        return ans;
    }

    public static int[] merge(int[] a, int[] b) {
        int[] arr = new int[a.length + b.length];

        int i = 0, j = 0, idx = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                arr[idx] = a[i];
                idx++;
                i++;
            } else {
                arr[idx] = b[j];
                idx++;
                j++;
            }
        }

        while (i != a.length) {
            arr[idx] = a[i];
            idx++;
            i++;
        }

        while (j != b.length) {
            arr[idx] = b[j];
            idx++;
            j++;
        }

        return arr;
    }
    
    public static void display(int[] arr) {
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }
}
