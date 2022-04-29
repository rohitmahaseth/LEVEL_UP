public class ProGrad{
    public static void main(String[] args) {
        int[] arr= {0, 2, 3, 3, 4, 0, 6, 6};
        arr= shuffle(arr);
        for(int i= 0; i< arr.length; i++){
            System.out.print(arr[i]+ " ");
        }
    }

    public static int[] shuffle(int[] arr){
        for(int i= 0; i< arr.length- 1; i++){
            if(i% 2== 0){
                if(arr[i]> arr[i+ 1]){
                    swap(arr, i, i+ 1);
                }
            }
            else{
                if(arr[i]< arr[i+ 1]){
                    swap(arr, i, i+ 1);
                }
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j){
        int temp= arr[i];
        arr[i]= arr[j];
        arr[j]= temp;
    }
}