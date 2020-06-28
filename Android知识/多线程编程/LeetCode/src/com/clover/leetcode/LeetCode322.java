package com.clover.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode题目322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * <p>
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 */
public class LeetCode322 {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 100;
        LeetCode322 code = new LeetCode322();
        System.out.println(code.coinChange(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {

        HashMap<Integer, Integer> cache = new HashMap();
        return dp(coins, amount, cache);

    }

    public int dp(int[] coins, int amount, Map<Integer, Integer> cache) {

        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = -1;
            if (!cache.isEmpty() && cache.containsKey(amount - coin)) {
                subProblem = cache.get(amount - coin);
            } else {
                subProblem = dp(coins, amount - coin, cache);
            }
            if (subProblem == -1)
                continue;
            result = result > subProblem ? (1 + subProblem) : result;
        }

        if (result != Integer.MAX_VALUE) {
            return result;
        } else {
            return -1;
        }
    }


}
