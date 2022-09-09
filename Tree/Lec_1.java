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
    
    public boolean rootToNodePath(TreeNode node, int data, List<TreeNode> list) {
        if (node == null)
            return false;
        if (node.val == data) {
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

        rootToNodePath(root, p.val, left);
        rootToNodePath(root, q.val, right);

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
        List<TreeNode> list= new ArrayList<>();
        rootToNodePath(root, target.val, list);

        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode block = null;

        for (int i = 0; i < list.size(); i++) {
            printKDown(list.get(i), k - i, block, ans);
            block = list.get(i);
        }

        return ans;
    }
}