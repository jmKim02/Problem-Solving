package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/17136 - 색종이 붙이기(Gold)
Time: 208ms (Java 11)
Memory: 20340KB

💡 풀이 포인트
  - 백트래킹 + 브루트포스
    → 모든 가능한 색종이 배치 시도
  - 가지치기 (Pruning)
    → 현재 사용한 색종이 수 >= 최솟값이면 중단
  - 그리디 전략
    → 큰 색종이(5x5)부터 작은 색종이(1x1) 순으로 시도
    → 큰 색종이를 우선 사용하면 전체 개수 최소화 가능
  - 색종이는 정사각형 영역 전체가 모두 1이어야 배치 가능
*/

public class Q17136 {
    static int[][] board = new int[10][10];
    static int[] paperCount = new int[6]; // Count of each paper size: 1x1, 2x2, 3x3, 4x4, 5x5
    static int minPapers = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtrack(0, 0, 0);

        System.out.println(minPapers == Integer.MAX_VALUE ? -1 : minPapers);

        br.close();
    }

    static void backtrack(int row, int col, int usedPapers) {
        // Pruning: if papers used so far >= current minimum, stop
        if (usedPapers >= minPapers) {
            return;
        }

        int[] next = findNext1(row, col);
        if (next == null) {
            minPapers = Math.min(minPapers, usedPapers);
            return;
        }

        int r = next[0];
        int c = next[1];

        // Try papers from 5x5 to 1x1 (larger papers first for efficiency)
        for (int size = 5; size >= 1; size--) {
            if (paperCount[size] >= 5) continue;

            if (canPlace(r, c, size)) {
                place(r, c, size, 0);
                paperCount[size]++;

                backtrack(0, 0, usedPapers + 1);

                place(r, c, size, 1);
                paperCount[size]--;
            }
        }
    }

    static int[] findNext1(int startRow, int startCol) {
        for (int i = startRow; i < 10; i++) {
            int j = (i == startRow) ? startCol : 0;
            for (; j < 10; j++) {
                if (board[i][j] == 1) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    static boolean canPlace(int row, int col, int size) {
        if (row + size > 10 || col + size > 10) {
            return false;
        }

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (board[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static void place(int row, int col, int size, int value) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                board[i][j] = value;
            }
        }
    }
}