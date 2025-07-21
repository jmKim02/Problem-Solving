package leetcode;

import java.util.*;

/*
https://leetcode.com/problems/group-anagrams/description/
Runtime: 6ms    | Beats: 98.36%
Memory: 47.87MB | Beats: 68.66%
 */

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s: strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);

            String key = String.valueOf(chars);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
