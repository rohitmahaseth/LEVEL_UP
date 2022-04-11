public static void main (String[] args){
        Scanner scn= new Scanner(System.in);
        int n= scn.nextInt();
        int[] vol= new int[n];
        for(int i= 0; i< n; i++){
            vol[i]= scn.nextInt();
        }

        int o= scn.nextInt();
        int[] nvol= new int[o];
        for(int i= 0; i< o; i++){
            nvol[i]= scn.nextInt();
        }

        Arrays.sort(vol);

        int[] ans= sortIntersect(vol, nvol);
        for(int i= 0; i< ans.length; i++){
            System.out.println(ans[i]);
        }
    }

     //2nd Question
     public static int[] sortIntersect(int[] vol, int[] nvol){
        ArrayList<Integer> list= new ArrayList<>();
        for(int i= vol.length- 1; i>= 0; i--){
            for(int j= 0; j< nvol.length; j++){
                if(vol[i]== nvol[j]){
                    list.add(vol[i]);
                    nvol[j]= -1;
                }
            }
        }

        int[] ans= new int[list.size()];
        for(int i= 0; i< ans.length; i++){
            ans[i]= list.get(i);
        }

        return ans;
     }