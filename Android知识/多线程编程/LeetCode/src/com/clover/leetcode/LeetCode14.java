package com.clover.leetcode;


/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 */
public class LeetCode14 {

    public static void main(String[] args) {
        LeetCode14 code = new LeetCode14();
        String strs[] = new String[]{"flower", "flow", "flight"};
        System.out.println(code.longestCommonPrefix(strs));
    }

    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if (strs.length < 1) {
            result = "";
        }
        if (strs.length == 1) {
            result = strs[0];
        }
        if (strs.length > 1) {
            result = strs[0];
            for (int i = 1; i < strs.length; i++) {
                result = lcp(strs[i], result);
            }
        }
        return result;
    }

    private String lcp(String str, String result) {
        String cache = "";
        String str1 = "";
        String str2 = "";
        if (str.length() > result.length()) {
            str1 = result;
            str2 = str;
        } else {
            str1 = str;
            str2 = result;
        }
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                cache += str1.charAt(i);
            } else {
                break;
            }
        }
        return cache;
    }

}
