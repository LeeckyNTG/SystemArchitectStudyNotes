package com.clover.leetcode;


/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LeetCode5 {

    public static void main(String[] args) {
        LeetCode5 code = new LeetCode5();
        String s1 = "cbbd";
        System.out.println(code.longestPalindrome(s1));
    }

    //两边扩散法
    public String longestPalindrome1(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            /**以 s[i] 为中心的最长回文子串*/
            String s1 = palindrome(s, i, i);
            /**以 s[i] 和 s[i+1] 为中心的最长回文子串*/
            String s2 = palindrome(s, i, i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }
    private String palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            /**向两边展开*/
            l--;
            r++;
        }
        /**返回以 s[l] 和 s[r] 为中心的最长回文串*/
        return s.substring(l + 1, r);
    }


    //动态规划法
    public String longestPalindrome(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


    //优化后的两边扩散法，在所有 Java 提交中击败了
    //100.00%
    //的用户
    private int offset = 0, end = 0, count = 0;
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) return s;
        longestPalindrome(s.toCharArray(), 0);
        return s.substring(offset, end + 1);
    }
    private void longestPalindrome(char[] cs, int index) {
        if (index >= cs.length - 1 || ((cs.length - index) << 1) + 1 <= count) return;
        int cur_start = index;
        int cur_end = index;

        // 相同字符串连续，则扩大中心
        while (cur_end < cs.length - 1 && cs[cur_end + 1] == cs[index]) cur_end++;

        index = cur_end;

        // 在此处中心扩散
        while (cur_start > 0 && (cur_end < cs.length - 1) && (cs[cur_end + 1] == cs[cur_start - 1])) {
            cur_end++;
            cur_start--;
        }

        if (cur_end - cur_start > end - offset) {
            offset = cur_start;
            end = cur_end;
            count = end - offset;
        }

        longestPalindrome(cs, index + 1);
    }

}
