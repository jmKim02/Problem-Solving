package programmers;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42748 - K번째수

public class KthNumber {
    public int[] solution(int[] array, int[][] commands) {
        int len = commands.length;
        int[] results = new int[len];

        for (int i = 0; i < len; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1];
            int k = commands[i][2] - 1;

            int[] subArrays = Arrays.copyOfRange(array, start, end);
            Arrays.sort(subArrays);
            results[i] = subArrays[k];
        }

        return results;
    }
}
