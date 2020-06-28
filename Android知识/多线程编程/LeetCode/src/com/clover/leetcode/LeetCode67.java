package com.clover.leetcode;

import java.util.Arrays;

/**
 * LeetCode题目67：二进制求和
 * <p>
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 */
public class LeetCode67 {

    public static void main(String[] args) {
        LeetCode67 code = new LeetCode67();

        String a = "1010", b = "1011";
        System.out.println(code.addBinary(a, b));
    }

    public String addBinary(String a, String b) {
        boolean flag = false;
        String result = "";
        int length = a.length() > b.length() ? a.length() : b.length();
        for (int i = 0; i < length; i++) {
            char ca = '0';
            if (a.length() - i >= 1) {
                ca = a.charAt(a.length() - i - 1);
            }
            char cb = '0';
            if (b.length() - i >= 1) {
                cb = b.charAt(b.length() - i - 1);
            }
            if (flag) {
                if (ca == '0' && cb == '0') {
                    result = '1' + result;
                    flag = false;
                }
                if (ca == '0' && cb == '1') {
                    result = '0' + result;
                    flag = true;
                }
                if (ca == '1' && cb == '0') {
                    result = '0' + result;
                    flag = true;
                }
                if (ca == '1' && cb == '1') {
                    result = '1' + result;
                    flag = true;
                }
            } else {
                if (ca == '0' && cb == '0') {
                    result = '0' + result;
                    flag = false;
                }
                if (ca == '0' && cb == '1') {
                    result = '1' + result;
                    flag = false;
                }
                if (ca == '1' && cb == '0') {
                    result = '1' + result;
                    flag = false;
                }
                if (ca == '1' && cb == '1') {
                    result = '0' + result;
                    flag = true;
                }
            }
        }

        if (flag) {
            result = '1' + result;
        }
        return result;
    }

}
