package com.clover.leetcode;

/**
 * LeetCode题目9：回文数
 * <p>
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 */
public class LeetCode9 {

    public static void main(String[] args) {
        LeetCode9 code = new LeetCode9();
        int data = -121;
        System.out.println(code.isPalindrome(data));
    }

    public boolean isPalindrome(int x) {
        String data1 = x + "";
        String data2 = new StringBuilder(data1).reverse().toString();
        if (data1.equals(data2)) {
            return true;
        } else {
            return false;
        }
    }
}
