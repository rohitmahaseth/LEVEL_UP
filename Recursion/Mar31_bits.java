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

     // 136
     public int singleNumber(int[] nums) {
        int ans = 0;
        for (int ele : nums) {
            ans ^= ele;
        }

        return ans;

    }

    // 268
    public int missingNumber(int[] nums) {
        int n = nums.length, ans = n, i = 0;
        while (i < n) {
            ans ^= nums[i] ^ (i++);

        }
        return ans;
    }

    // 191

    public int hammingWeight_1(int n) {
        int count = 0, i = 0;
        while (i < 32) {
            if ((n & (1 << i)) != 0)
                count++;
            i++;
        }

        return count;
    }

    public int hammingWeight_2(int n) {
        int count = 0, i = 0;
        while (n != 0) {
            if ((n & 1) != 0)
                count++;
            n >>>= 1;
        }

        return count;
    }

    public int hammingWeight_3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n & (n - 1));
        }

        return count;
    }

    // 338
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;

    }
     
    public static void main(String[] args){
        System.out.println(isPowerOfFour(64));
    }
}