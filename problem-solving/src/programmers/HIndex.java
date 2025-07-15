package programmers;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42747 - H-Index

public class HIndex {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;

        for (int i = 0; i < len; i++) {
            int h = len - i;

            if (citations[i] >= h) {
                return h;
            }
        }

        return 0;
    }
}
