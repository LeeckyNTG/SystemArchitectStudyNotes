package com.clover.leetcode;

/**
 * LeetCode [12]整数转罗马数字
 * <p>
 * //罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * //
 * // 字符          数值
 * //I             1
 * //V             5
 * //X             10
 * //L             50
 * //C             100
 * //D             500
 * //M             1000
 * //
 * // 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + I
 * //I 。
 * // 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
 * // 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * // I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * // X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * // C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * //
 * //
 * // 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * //
 * // 示例 1:
 * //
 * // 输入: 3
 * //输出: "III"
 * //
 * // 示例 2:
 * //
 * // 输入: 4
 * //输出: "IV"
 * //
 * // 示例 3:
 * //
 * // 输入: 9
 * //输出: "IX"
 * //
 * // 示例 4:
 * //
 * // 输入: 58
 * //输出: "LVIII"
 * //解释: L = 50, V = 5, III = 3.
 * //
 * //
 * // 示例 5:
 * //
 * // 输入: 1994
 * //输出: "MCMXCIV"
 * //解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * // Related Topics 数学 字符串
 * // 👍 378 👎 0
 */
public class LeetCode12 {

    public static void main(String[] args) {
        LeetCode12 code = new LeetCode12();
        System.out.println(code.intToRoman(58));
    }

    public String intToRoman(int num) {
        int I = 1;
        int V = 5;
        int X = 10;
        int L = 50;
        int C = 100;
        int D = 500;
        int M = 1000;

        String result = "";
        if (num / M >= 1) {
            int m = num / M;
            switch (m) {
                case 1:
                    result += "M";
                    break;
                case 2:
                    result += "MM";
                    break;
                case 3:
                    result += "MMM";
                    break;
            }
            num = num % M;
        }

        if (num / D >= 1) {
            if (num >= 900) {
                result += "CM";
                num = num - 900;
            } else {
                result += "D";
                num = num - 500;
            }
        }

        if (num / C >= 1) {
            int c = num / C;
            switch (c) {
                case 1:
                    result += "C";
                    break;
                case 2:
                    result += "CC";
                    break;
                case 3:
                    result += "CCC";
                    break;
                case 4:
                    result += "CD";
                    break;
            }
            num = num % C;
        }

        if (num / L >= 1) {

            if (num >= 90) {
                result += "XC";
                num = num - 90;
            } else {
                result += "L";
                num = num - 50;
            }
        }

        if (num / X >= 1) {
            int x = num / X;
            switch (x) {
                case 1:
                    result += "X";
                    break;
                case 2:
                    result += "XX";
                    break;
                case 3:
                    result += "XXX";
                    break;
                case 4:
                    result += "XL";
                    break;
            }
            num = num % X;
        }


        if (num / V >= 1) {

            if (num >= 9) {
                result += "IX";
                num = num - 9;
            } else {
                result += "V";
                num = num -5;
            }
        }

        if (num / I >= 1) {
            int i = num / I;
            switch (i) {
                case 1:
                    result += "I";
                    break;
                case 2:
                    result += "II";
                    break;
                case 3:
                    result += "III";
                    break;
                case 4:
                    result += "IV";
                    break;
            }
        }

        return result;


    }
}
