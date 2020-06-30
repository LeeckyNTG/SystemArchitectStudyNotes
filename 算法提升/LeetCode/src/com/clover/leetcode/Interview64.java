package com.clover.leetcode;

/**
 * 面试题64
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class Interview64 {

    public static void main(String[] args) {
        Interview64 interview64 = new Interview64();
        System.out.println(interview64.sumNums2(4));
    }

    /**
     * 解法一：递归算法，利用&&中断递归
     */
    int sum = 0;

    public int sumNums(int n) {
        boolean bol = n > 1 && sumNums(n - 1) > 0;
        sum += n;
        return sum;
    }


    /**
     * 解法二：公式法，（n^2+n）/2,利用>>左移代替除法
     */
    public int sumNums2(int n) {
        return (int) (Math.pow(n, 2) + n) >> 1;
    }

}
