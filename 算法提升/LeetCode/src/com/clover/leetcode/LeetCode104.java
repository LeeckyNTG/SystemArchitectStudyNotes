package com.clover.leetcode;

/**
 * //给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * //
 * //
 * //
 * // 示例：
 * //二叉树：[3,9,20,null,null,15,7],
 * //
 * //     3
 * //   / \
 * //  9  20
 * //    /  \
 * //   15   7
 * //
 * //
 * // 返回其层次遍历结果：
 * //
 * // [
 * //  [3],
 * //  [9,20],
 * //  [15,7]
 * //]
 * //
 * // Related Topics 树 广度优先搜索
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class LeetCode104 {


    public static void main(String[] args) {

        LeetCode104 code = new LeetCode104();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        root.right = right;
        System.out.println(code.levelOrder(root));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
        }

        return res;
    }

}
