package com.clover.leetcode;


/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 * <p>
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class LeetCode567 {

    public static void main(String[] args) {
        LeetCode567 code = new LeetCode567();
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(code.checkInclusion(s1, s2));
    }

    public boolean checkInclusion(String s1, String s2) {

        return false;
    }

    private String [] getArrangements(String str){

        return null;
    }

}
