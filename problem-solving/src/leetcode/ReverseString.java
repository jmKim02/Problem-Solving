package leetcode;

/*
https://leetcode.com/problems/reverse-string/description/
Runtime: 0ms    | Beats: 100.00%
Memory: 45.8MB  | Beats: 82.67%
 */

class ReverseString {
    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < s.length / 2; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] =  temp;
        }
    }
}
