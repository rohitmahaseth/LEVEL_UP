// recursion trees
public class Mar25 {
    public static int coinChangePermutation_IN(int[] coins, int tar, String psf){
        if(tar== 0){
            System.out.println(psf);
            return 1;
        }
    
        int count= 0;
        for(int i= 0; i< coins.length; i++){
            if(tar- coins[i]>= 0){
                count+= coinChangePermutation_IN(coins, tar- coins[i], psf+ coins[i]+ " ");
            }
        }
        return count;
    }

    public static int coinChangeCombination_IN(int[] coins, int tar, int idx, String psf){
        if(tar== 0){
            System.out.println(psf);
            return 1;
        }

        int count= 0;
        for(int i= idx; i< coins.length; i++){
            if(tar- coins[i]>= 0){
                count+= coinChangeCombination_IN(coins, tar- coins[i], i, psf+ coins[i]+ " ");
            }
        }
        return count;
    }

    public static int coinChangeCombination_SIN(int[] coins, int tar, int idx, String psf){
        if(tar== 0){
            System.out.println(psf);
            return 1;
        }

        int count= 0;
        for(int i= idx; i< coins.length; i++){
            if(tar- coins[i]>= 0){
                count+= coinChangeCombination_IN(coins, tar- coins[i], i+ 1, psf+ coins[i]+ " ");
            }
        }
        return count;
    }

    public static int coinChangePermutation_SIN(int[] coins, int tar, String psf){
        if(tar== 0){
            System.out.println(psf);
            return 1;
        }

        int count= 0;
        for(int i= 0; i< coins.length; i++){
            if(coins[i]> 0 && tar- coins[i]>= 0){
                int val= coins[i];
                coins[i]= -coins[i];
                count+= coinChangePermutation_SIN(coins, tar- val, psf+ val+ " ");
                coins[i]= -coins[i];
            }
        }
        return count;
    }

    // =================================================================

    public static int coinChangeCombination_SIN_sub(int[] coins, int tar, int idx, String psf){
        if(tar== 0 || idx== coins.length){
            if(tar== 0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }


        int count= 0;
        if(tar- coins[idx]>= 0){
            count+= coinChangeCombination_SIN_sub(coins, tar- coins[idx], idx+ 1, psf+ coins[idx]+ " ");
        }
        count+= coinChangeCombination_SIN_sub(coins, tar, idx+ 1, psf);

        return count;
    }
    
    public static int coinChangePermutation_SIN_sub(int[] coins, int tar, int idx, String psf){
        if(tar== 0 || idx== coins.length){
            if(tar== 0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        
    }

    public static int coinChangeCombination_IN_sub(int[] coins, int tar, int idx, String psf){
       if(tar== 0 || idx== coins.length){
           if(tar== 0){
               System.out.println(psf);
               return 1;
           }
           return 0;
       } 

       int count= 0;
       // not chosen
       count+= coinChangeCombination_IN_sub(coins, tar, idx+ 1, psf);

       // chosen
       while(tar>= 0){
           psf= psf+ coins[idx]+ " ";
           tar= tar- coins[idx];
           if(tar>= 0){
               count+= coinChangeCombination_IN_sub(coins, tar, idx+ 1, psf);
           }
        }           
        return count;
    }


    public static void main(String[] args){
        int[] coins= {2, 3, 5, 7};
        // System.out.println(coinChangePermutation_IN(coins, 10, ""));
        // System.out.println(coinChangeCombination_IN(coins, 10, 0, ""));
        //  System.out.println(coinChangeCombination_SIN(coins, 10, 0, ""));
        // System.out.println(coinChangePermutation_SIN(coins, 10, ""));
        // System.out.println(coinChangeCombination_SIN_sub(coins, 10, 0, ""));
        // System.out.println(coinChangeCombination_IN_sub(coins, 10, 0, ""));
        System.out.println(coinChangePermutation_SIN_sub(coins, 10, 0, ""));

    }

}
