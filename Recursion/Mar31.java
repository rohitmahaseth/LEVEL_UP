//bits

public class Mar31{
    public static int leftshift(int x){
        return x<< 2;
    }

    public static int rightshift(int x){
        return x>> 2;
    }

    public static int setTrue(int num, int idx){
        int mask= 1;
        mask= mask<< idx;
        return num | mask;
    }

    public static int setFalse(int num, int idx){
        int mask=  1;
        mask= ~(mask<< idx);
        return num & mask;
    }

    public static int multiplyBy2(int n, int pow){
        return n<< pow;
    }

    public static int divideBy2(int n, int pow){
        return n>> pow;
    }

    public static boolean isEven(int n){
        return (n & 1)== 0;
    }

    //leetcode 231
    public boolean isPowerOfTwo(int n) {
        return (n> 0 && (n & (n-1))== 0);
    }

    //342
    public boolean isPowerOfFour(int n) {
        if(!isPowerOfTwo(n))
            return false;

        int count= 0;
        while(n!= 0){
            if((n & 1 )== 0){
                count++;
            }
            n >>>= 1;
        }
        return (count & 1)== 0;
    }
}


    
    public static void main(String[] args){
        System.out.println(isOdd(56));
    }
}