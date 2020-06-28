package com.clover.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode题目1：两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 */
public class LeetCode1 {

    public static void main(String[] args) {
        LeetCode1 leetCode1 = new LeetCode1();
        int[] nums = {2, 7, 11, 15};
        System.out.println(Arrays.toString(leetCode1.twoSum(nums, 9)));
    }

    /**
     * 遍历法
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 一遍哈希表
     * 将nun当做值存入map中，用target-nums[i]的值作为key查找是否存在
     */
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
