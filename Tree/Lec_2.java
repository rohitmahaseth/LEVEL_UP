import java.util.*;

public class Lec_2 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // printing a tree levelwise but in same line....
    public static void levelOrderSimple(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        while (que.size() != 0) {
            TreeNode rn = que.removeFirst();
            System.out.print(rn.val + " ");

            if (rn.left != null)
                que.addLast(rn.left);
            if (rn.right != null)
                que.addLast(rn.right);
        }
    }
    
    // printing a tree level wise but in different line
    public static void levelOrderLineWise_M1(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        LinkedList<TreeNode> childQue = new LinkedList<>();

        int level = 0;
        System.out.print("Level " + level + ": ");
        que.addLast(root);

        while (que.size() != 0) {
            TreeNode rn = que.removeFirst();
            System.out.print(rn.val + " ");

            if (rn.left != null)
                childQue.addLast(rn.left);
            if (rn.right != null)
                childQue.addLast(rn.right);

            if (que.size() == 0) {
                System.out.println();
                if (childQue.size() != 0)
                    System.out.print("Level " + (++level) + ": ");

                LinkedList<TreeNode> temp = childQue;
                childQue = que;
                que = temp;
            }

        }
    }
    
    // printing a tree level wise but in different line using deliminator(null);
    public static void levelOrderLineWise_M2(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        que.addLast(null);

        int level = 0;
        System.out.println("Level " + (++level) + " :");

        while (que.size() != 1) {
            TreeNode rn = que.removeLast();

            System.out.print(rn.val + " ");

            if (rn.left != null)
                que.addLast(rn.left);
            if (rn.right != null)
                que.addLast(rn.right);

            if (que.getFirst() == null) {
                System.out.println();
                System.out.println("Level " + (++level) + " :");
                que.addLast(que.removeLast());
            }
        }
    }
    
    // printing a tree level wise but in different line (best way) ****
    public static void levelOrderLineWise_M3(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        int level = 0;
        System.out.println("Level " + (++level) + " :");

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                TreeNode rn = que.removeFirst();

                System.out.print(rn.val + " ");

                if (rn.left != null)
                    que.addLast(rn.left);
                if (rn.right != null)
                    que.addLast(rn.right);
            }
            if (que.size() != 0) {
                System.out.println("Level " + (++level) + " :");
            }
        }
    }
    
    // 102. Binary Tree Level Order Traversal
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                TreeNode rn = que.removeFirst();
                if (level == ans.size())
                    ans.add(new ArrayList<>());

                ans.get(level).add(rn.val);

                if (rn.left != null)
                    que.addLast(rn.left);
                if (rn.right != null)
                    que.addLast(rn.right);
            }
            level++;
        }
        return ans;
    }
    
    public static void leftView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);

        while (que.size() != 0) {
            int size = que.size();
            System.out.print(que.getFirst().val + " ");

            while (size-- > 0) {
                TreeNode rn = que.removeFirst();

                if (rn.left != null)
                    que.addLast(rn.left);
                if (rn.right != null)
                    que.addLast(rn.right);
            }
        }
    }
    
    // 199. Right View of a binary tree
    public static List<Integer> rightView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        que.add(root);

        while (que.size() != 0) {
            int size = que.size();
            System.out.print(que.getFirst().val + " ");

            while (size-- > 0) {
                TreeNode rn = que.removeFirst();

                if (rn.right != null)
                    que.addLast(rn.right);

                if (rn.left != null)
                    que.addLast(rn.left);
            }
        }

        return list;
    }

    
    
    // Vertical View of a binary tree
    public static class VerticalPair {
        TreeNode node;
        int hl;

        VerticalPair(TreeNode node, int hl) {
            this.node = node;
            this.hl = hl;
        }
    }
    
    public static List<List<Integer>> verticalView(TreeNode root) {
        LinkedList<VerticalPair> que = new LinkedList<>();
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        int min = 1, max = -1;
        que.addLast(new VerticalPair(root, 0));

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                VerticalPair rp = que.removeFirst();

                min = Math.min(min, rp.hl);
                max = Math.max(max, rp.hl);

                hm.putIfAbsent(rp.hl, new ArrayList<>());
                hm.get(rp.hl).add(rp.node.val);

                if (rp.node.left != null)
                    que.addLast(new VerticalPair(rp.node.left, rp.hl - 1));
                if (rp.node.right != null)
                    que.addLast(new VerticalPair(rp.node.right, rp.hl + 1));
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        while (min <= max) {
            ans.add(hm.get(min++));
        }

        return ans;
    }
    
    // width of a tree
    // ans{minHL, maxHL}
    public static void width(TreeNode root, int hl, int[] ans) {
        if (root == null)
            return;

        ans[0] = Math.min(hl, ans[0]);
        ans[1] = Math.max(hl, ans[1]);

        width(root.left, hl - 1, ans);
        width(root.right, hl + 1, ans);
    }
    // Notes: Sometimes u are not allowed to use HashMap to find vertical view.....that time u have to find 
    // width of the tree as an alternative of HashMap....(think why)

    public static void main(String[] args) {
        
    }
}
