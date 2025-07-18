package leetcode;

import java.util.*;

// https://leetcode.com/problems/most-common-word/description/

public class MostCommonWord {
    /*
    Runtime: 10ms    | Beats: 90.59%
    Memory: 43.3MB   | Beats: 26.75%
    */
    public String mostCommonWordV1(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> counts = new HashMap<>();

        String[] words = paragraph.replaceAll("\\W+", " ").toLowerCase().split(" ");

        for (String word : words) {
            if (!ban.contains(word)) {
                counts.put(word, counts.getOrDefault(word, 0) + 1);
            }
        }

        int max = 0;
        String maxWord = "";
        for (Map.Entry<String, Integer> w : counts.entrySet()) {
            if (max < w.getValue()) {
                maxWord = w.getKey();
                max = w.getValue();
            }
        }

        return maxWord;
    }

    /*
    Runtime: 8ms    | Beats: 95.56%
    Memory: 42.3MB  | Beats: 99.22%
    */
    public String mostCommonWordV2(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> counts = new HashMap<>();

        int max = 0;
        String maxWord = "";

        StringBuilder word = new StringBuilder();
        paragraph = paragraph.toLowerCase() + ".";

        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                word.append(c);
            } else if (word.length() > 0) {
                String w = word.toString();
                if (!ban.contains(w)) {
                    int newCount = counts.getOrDefault(w, 0) + 1;
                    counts.put(w, newCount);
                    if (newCount > max) {
                        max = newCount;
                        maxWord = w;
                    }
                }
                word.setLength(0); // reset builder
            }
        }

        return maxWord;
    }
}
