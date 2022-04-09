//bits

public class Mar31_bits{
    public static int leftshift(int x){
        return x<< 2;
    }

    public static int rightshift(int x){
        return x>> 2;
    }

    public static int setTrue(int num, int idx){
        int mask= 1<< idx;
        return num | mask;
    }

    public static int setFalse(int num, int idx){
        // int mask= 1<< idx;
        // return ~(~num | mask);

        //****kisi bit ko 1 ke sath & karne de bit unchanged rehta hai****
        int mask= ~(1<< idx);
        return num & mask;
    }

    public static boolean isEven(int n){
        //if last bit is 1 then n is odd and if last bit is 0 then n is even
        return (n & 1)== 0;
    }

    public static int multiplyBy2(int n, int pow){
       return (n<< pow);
    }

    public static int divideBy2(int n, int pow){
        return (n>> pow);
    }

    //leetcode 231
    public static boolean isPowerOfTwo(int n) {
        return (n> 0 && (n & (n-1))== 0);
    }

    //leetcode 342
    public static boolean isPowerOfFour(int n) {
        if (!isPowerOfTwo(n))
            return false;

        int count = 0;
        while (n != 0) {
            if ((n & 1) == 0)
                count++;
            n >>>= 1;
        }

        return (count & 1) == 0;
    }

    //leetcode 191
    

     
    public static void main(String[] args){
        System.out.println(isPowerOfFour(64));
    }
}