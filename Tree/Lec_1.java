import java.util.*;
import java.io.*;

public class Lec_1 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public int size(TreeNode node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    // edge wise then for null return -1 and for node wise return 0
    public int height(TreeNode node) {
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public int max(TreeNode node) {
        if (node == null)
            return -(int) 1e9;

        int lmv = max(node.left);
        int rmv = max(node.right);

        return Math.max(Math.max(lmv, rmv), node.val);
    }
    
    public boolean find(TreeNode node, int data) {
        if (node == null)
            return false;
        if (node.val == data)
            return true;

        return find(node.left, data) || find(node.right, data);
    }
    
    public boolean rootToNodePath(TreeNode node, TreeNode data, List<TreeNode> list) {
        if (node == null)
            return false;
        if (node == data) {
            list.add(node);
            return true;
        }

        boolean res = rootToNodePath(node.left, data, list) || rootToNodePath(node.right, data, list);
        if (res)
            list.add(node);

        return res;
    }
    
    // alternate way
    public ArrayList<TreeNode> rootToNodePath(TreeNode node, int data) {
        if (node == null) {
            ArrayList<TreeNode> base = new ArrayList<>();
            return base;
        }

        if (node.val == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<TreeNode> left = rootToNodePath(node.left, data);
        if (left.size() != 0) {
            left.add(node);
            return left;
        }

        ArrayList<TreeNode> right = rootToNodePath(node.right, data);
        if (right.size() != 0) {
            right.add(node);
            return right;
        }

        return new ArrayList<>();
    }
    
    // 236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> left = new ArrayList<>();
        ArrayList<TreeNode> right = new ArrayList<>();

        rootToNodePath(root, p, left);
        rootToNodePath(root, q, right);

        int i = left.size() - 1;
        int j = right.size() - 1;

        TreeNode LCA = null;

        while (i >= 0 && j >= 0) {
            if (left.get(i) != right.get(j))
                break;

            LCA = left.get(i);

            i--;
            j--;
        }

        return LCA;
    }

    public void printKDown(TreeNode node, int depth, TreeNode block, List<Integer>ans){
        if (node == null || depth < 0 || node== block)
            return;

        if (depth == 0) {
            ans.add(node.val);
            return;
        }
        
        printKDown(node.left, depth - 1, block, ans);
        printKDown(node.right, depth- 1, block, ans);     
    }
    
    // 863 
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<TreeNode> list = new ArrayList<>();
        rootToNodePath(root, target, list);

        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode block = null;

        for (int i = 0; i < list.size(); i++) {
            printKDown(list.get(i), k - i, block, ans);
            block = list.get(i);
        }
        return ans;
    }

    // better solution
    public int distanceK2(TreeNode node, TreeNode target, int k, List<Integer> ans) {
        if (node == null)
            return -1;
        if (node == target) {
            printKDown(node, k, null, ans);
            return 1;
        }

        int lans = distanceK2(node.left, target, k, ans);
        if (lans != -1) {
            printKDown(node, k - lans, node.left, ans);
            return lans + 1;
        }
        
        int rans = distanceK2(node.right, target, k, ans);
        if (rans != -1){
            printKDown(node, k - lans, node.right, ans);
            return rans + 1;
        }

        return -1;       
    }
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        distanceK2(root, target, k, ans);
        return ans;
    }

    
    public int rootToNodeDistance(TreeNode root, TreeNode data) {
        if (root == null)
            return -1;
        if (root == data)
            return 0;

        int lans = rootToNodeDistance(root.left, data);
        if (lans != -1)
            return lans + 1;

        int rans = rootToNodeDistance(root.right, data);
        if (lans != -1)
            return rans + 1;

        return -1;
    }
    

    // 543
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return -1;

        int leftTreeDia = diameterOfBinaryTree(root.left);
        int rightTreeDia = diameterOfBinaryTree(root.right);

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(Math.max(leftTreeDia, rightTreeDia), (leftHeight + rightHeight + 2));
    }

    // Optimize version {dia, hei}
    public int[] diameterOfBinaryTree_02(TreeNode root) {
        if (root == null)
            return new int[] { -1, -1 };

        int[] leftAns = diameterOfBinaryTree_02(root.left);
        int[] rightAns = diameterOfBinaryTree_02(root.right);

        int[] ans = new int[2];
        ans[0] = Math.max(Math.max(leftAns[0], rightAns[0]), leftAns[1] + rightAns[1] + 2);
        ans[1] = Math.max(leftAns[1], rightAns[1]) + 1;

        return ans;
    }
    
    // last method
    int maxDia = 0;

    public int diameterOfBinaryTree_03(TreeNode root) {
        if (root == null)
            return -1;
        
        int lh = diameterOfBinaryTree_03(root.left);
        int rh = diameterOfBinaryTree_03(root.right);

        maxDia = Math.max(maxDia, (lh + rh + 2));

        return Math.max(lh, rh)+ 1;
    }

    //112
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && targetSum == root.val)
            return true;

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
    
    //113
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        pathSum1(root, targetSum, new ArrayList<>() ,ans);
        return ans;
    }
    
    public void pathSum1(TreeNode root, int targetSum, List<Integer> list, List<List<Integer>> ans) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            if (targetSum == root.val) {
                List<Integer> temp = new ArrayList<>(list);
                temp.add(root.val);
                ans.add(temp);
            }
            return;
        }

        list.add(root.val);
        pathSum1(root.left, targetSum - root.val, list, ans);
        pathSum1(root.right, targetSum - root.val, list, ans);
        list.remove(list.size() - 1);
    }
    
    //https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
    int maxPathSum(TreeNode root){ 
        max_RootToLeafSum(root, root);
        return maxSum;
    } 
    
    int maxSum = Integer.MIN_VALUE;

    public int max_RootToLeafSum(TreeNode root, TreeNode fRoot) {
        if (root == null)
            return Integer.MIN_VALUE;

        if (root.left == null && root.right == null) {
            return root.val;
        }

        int leftSum = max_RootToLeafSum(root.left, fRoot);
        int rightSum = max_RootToLeafSum(root.right, fRoot);

        if (root == fRoot) {
            if (root.left == null || root.right == null) {
                int temp = 0;
                temp += root.left == null ? 0 : leftSum;
                temp += root.right == null ? 0 : rightSum;
                temp += root.val;

                maxSum = Math.max(maxSum, temp);
            }
        }

        if (root.left != null && root.right != null)
            maxSum = Math.max(maxSum, (leftSum + rightSum + root.val));

        return Math.max(leftSum, rightSum) + root.val;
    }

    //98. Validate Binary Search Tree
    long pre= -(long) 1e13;
    
    public boolean isValidBST(TreeNode root) {        
        return isValidBST_(root);
    }
    
    public boolean isValidBST_(TreeNode root) {
        if (root == null)
            return true;

        if (!isValidBST_(root.left))
            return false;
        ;

        if (root.val <= pre)
            return false;
        pre = root.val;

        if (!isValidBST_(root.right))
            return false;

        return true;
    }
    
    // alternate method (Validate Binary Search Tree)
    public class BSTPair{
        boolean isBST= true;
        long max= -(long) 1e13;
        long min= (long) 1e13;
        
        BSTPair(boolean isBST, int max, int min){
            this.isBST= isBST;
            this.max= max;
            this.min= min;
        }
        
        BSTPair(){
            
        }
    }
    
    public boolean _isValidBST(TreeNode root) {
        return _isValidBST_(root).isBST;
    }
    
    public BSTPair _isValidBST_(TreeNode root){
        if(root== null) return new BSTPair();
        
        BSTPair ans= new BSTPair();
        
        BSTPair left= _isValidBST_(root.left);
        BSTPair right= _isValidBST_(root.right);
         
        ans.isBST= left.isBST== true && left.max< root.val && right.isBST== true && right.min>root.val;
        if(!ans.isBST)
            return ans;
        
        ans.max= Math.max(right.max, root.val);
        ans.min= Math.min(left.min, root.val);
        
        return ans;
    }
    
    


}