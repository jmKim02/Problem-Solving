package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/valid-palindrome/description/

class ValidPalindrome {
    /*
    Runtime: 2ms    | Beats: 98.49%
    Memory: 44.8MB  | Beats: 40.74%
     */
    public boolean isPalindromeV1(String s) {
        List<Character> chars = new ArrayList<>();

        // 1. Extract only alphanumeric characters and convert uppercase to lowercase
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                c += 32;
                chars.add(c);
            } else if (c >= 'a' && c <= 'z' || c >= '0' && c <= '9') {
                chars.add(c);
            }
        }

        // 2. Check if the list of characters forms a palindrome
        int size = chars.size();
        for (int i = 0; i < size / 2; i++) {
            if (chars.get(i) != chars.get(size - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    /*
    Runtime: 2ms    | Beats: 98.49%
    Memory: 42.8MB  | Beats: 90.57%
     */
    public boolean isPalindromeV2(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            } else if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            } else {
                if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                    return false;
                }

                start++;
                end--;
            }
        }

        return true;
    }
}
