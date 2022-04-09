//Recursion Trees Questions
import java.util.*;

public class Mar28_recTreesQues{

    //leetcode 17
    public static void main(String[] ags){
        String digits= "23";
        if(digits.length()== 0){
            ArrayList<String> ba= new ArrayList<>();
            System.out.println(ba);
        }
        
        String[] codes= {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};        
        ArrayList<String> ans= new ArrayList<>();
        letterCombination(codes, digits, ans, 0, "");
        System.out.println(ans);
    }  
        
    public static int letterCombination(String[] codes, String digits, ArrayList<String>ans, int idx, String psf){
        if(idx== digits.length()){
            ans.add(psf);
            return 1;
        }
        
        int val= digits.charAt(idx)- '0';
        String code= codes[val];
        int count= 0;
        for(int i= 0; i<code.length(); i++){
            count+= letterCombination(codes, digits, ans, idx+ 1, psf+ code.charAt(i));
        }
        
        return count;        
    }

    // 47. Permutations II  
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans= new ArrayList<>();
        List<Integer> rans= new ArrayList<>();
        Arrays.sort(nums);
        Permutations_2(nums, ans, rans);
        return ans;
    }
    
    private int Permutations_2(int[] nums, List<List<Integer>> ans, List<Integer> rans){
        if(rans.size()== nums.length){
            List<Integer> ba= new ArrayList<>(rans);
            ans.add(ba);
            return 1;
        }
        
        int count= 0, pre= -11;
        for(int i= 0; i< nums.length; i++){
            int val= nums[i];
            rans.add(val);
            if(pre!= nums[i] && nums[i]!= -11){
                nums[i]= -11;
                count+= Permutations_2(nums, ans, rans);
                nums[i]= val;
            }
            rans.remove(rans.size()- 1);
            pre= nums[i];
        }
        
        return count;
    }

    // 40. Combination Sum II   
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans= new ArrayList<>();
        List<Integer> rans= new ArrayList<>();
        Arrays.sort(candidates);
        Combination_Sum(candidates, target, 0, ans, rans);
        return ans;        
    }
    
    private int Combination_Sum(int[] candidates, int tar, int idx, List<List<Integer>> ans, List<Integer> rans){
        
        if(tar== 0){
            List<Integer> ba= new ArrayList<>(rans);
            ans.add(ba);
            return 1;
        }
        
        int count= 0;
        int pre= -1;
        for(int i= idx; i< candidates.length; i++){
            
            if(tar- candidates[i]>= 0 && pre!= candidates[i]){
                pre= candidates[i];
                rans.add(candidates[i]);
                count+= Combination_Sum(candidates, tar- candidates[i], i+ 1, ans, rans);
                rans.remove(rans.size()- 1);
            } 
        }
        
        return count;
    }

    //39. Combination Sum
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans= new ArrayList<>();
        List<Integer> rans= new ArrayList<>();
        Combination_Sum_(candidates, target, 0, ans, rans);
        return ans;        
    }
    
    private int Combination_Sum_(int[] candidates, int tar, int idx, List<List<Integer>> ans, List<Integer> rans){
        
        if(tar== 0){
            List<Integer> ba= new ArrayList<>(rans);
            ans.add(ba);
            return 1;
        }
        
        int count= 0;
        for(int i= idx; i< candidates.length; i++){
            if(tar- candidates[i]>= 0){
                rans.add(candidates[i]);
                count+= Combination_Sum_(candidates, tar- candidates[i], i, ans, rans);
                rans.remove(rans.size()- 1);
            } 
        }
        
        return count;
    }
    
    // 46. Permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans= new ArrayList<>();
        List<Integer> rans= new ArrayList<>();
        Permutations(nums, ans, rans);
        return ans;
    }
    
    private int Permutations(int[] nums, List<List<Integer>> ans, List<Integer> rans){
        if(rans.size()== nums.length){
            List<Integer> ba= new ArrayList<> (rans);
            ans.add(ba);
            return 1;
        }
        
        int count= 0;
        for(int i= 0; i< nums.length; i++){
            if(nums[i]!= -11){
                int val= nums[i];
                rans.add(nums[i]);
                nums[i]= -11;
                count+= Permutations(nums, ans, rans);
                nums[i]= val;
                rans.remove(rans.size()- 1);
            }            
        }
        return count;
    }

    // 322. Coin Change
    public int coinChange(int[] coins, int amount) {
        int ans= coin_change(coins, amount, 0);
        return ans== (int)1e9 ? -1: ans;
    }
    
    private int coin_change(int[] coins, int amount, int idx){
        if(amount== 0){
            return 0;
        }
        
        int min_coin= (int)1e9;
        for(int i= idx; i< coins.length; i++){
            if(amount- coins[i]>= 0){
               min_coin= Math.min(min_coin, coin_change(coins, amount- coins[i], idx)+ 1);             
            }                        
        }
        
        return min_coin;
    }

    // 518. Coin Change 2
    public int change(int amount, int[] coins) {
        int tc= coin_change(amount, coins, 0);
        return tc;
    }
    
    private int coin_change(int amount, int[] coins, int idx){
        if(amount== 0){
            return 1;
        }
        
        int count= 0;
        for(int i= idx; i< coins.length; i++){
            if(amount- coins[i]>= 0){
                count+= coin_change(amount- coins[i], coins, i);
            }            
        }
        return count;
    }

}
