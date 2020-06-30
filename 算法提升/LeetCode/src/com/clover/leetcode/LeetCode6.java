package com.clover.leetcode;


/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */
public class LeetCode6 {

    public static void main(String[] args) {
        LeetCode6 code = new LeetCode6();
        String s = "LEETCODEISHIRING";
        System.out.println(code.convert(s, 3));
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int length = numRows > s.length() ? s.length() : numRows;
        String[] rows = new String[length]; // 防止s的长度小于行数
        for (int i = 0; i < rows.length; i++) {
            rows[i] = "";
        }
        int curRow = 0;
        boolean goingDown = false;
        for (int i = 0; i < s.length(); i++) {
            rows[curRow] += s.charAt(i);
            if (curRow == 0 || curRow == (numRows - 1)) { // 当前行curRow为0或numRows -1时，箭头发生反向转折
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }
        String result = "";
        for (String row : rows) {
            result += row;
        }
        return result;
    }

}
