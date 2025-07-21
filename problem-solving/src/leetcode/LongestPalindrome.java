package leetcode;

/*
https://leetcode.com/problems/longest-palindromic-substring/
Runtime: 13ms   | Beats: 95.73%
Memory: 42.40MB | Beats: 70.65%
 */

public class LongestPalindrome {
    int left = 0;
    int maxLen = 0;

    public String longestPalindrome(String s) {
        int len = s.length();

        if (len < 2) return s;

        for(int i = 0; i < len - 1; i++) {
            findPalindrome(s, i, i + 1);
            findPalindrome(s, i, i + 2);
        }

        return s.substring(left, left + maxLen);
    }

    public void findPalindrome(String s, int l, int r) {
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        if (maxLen < r - l - 1) {
            maxLen = r - l - 1;
            left = l + 1;
        }
    }
}
