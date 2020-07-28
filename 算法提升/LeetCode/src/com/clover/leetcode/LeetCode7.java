package com.clover.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode题目7：整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 */
public class LeetCode7 {

    public static void main(String[] args) {
        LeetCode7 code = new LeetCode7();
        int data = 1534236469;
        System.out.println(code.reverse(data));
    }

    public int reverse(int x) {
        try {
            if(x >= 0){
                return Integer.parseInt(new StringBuffer(x+"").reverse().toString());
            }else{
                return -Integer.parseInt(new StringBuffer((x+"").substring(1,(x+"").length())).reverse().toString());
            }
        }catch (Exception e){
            return 0;
        }
    }

}
