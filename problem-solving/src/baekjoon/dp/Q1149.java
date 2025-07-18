package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/1149 - RGB거리(Silver)
Time: 112ms (Java 11)
Memory: 14488KB

💡 풀이 포인트
  - 같은 색이 연속될 수 없기 때문에, 이전 집에서 선택하지 않은 두 색 중 최솟값을 누적하며 계산한다.
  - 점화식 → dp[i][색] = 이전 집에서 선택하지 않은 두 색 중 최소값 + 현재 색 비용
*/

public class Q1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][3];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + r;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + g;
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + b;
        }

        System.out.print(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
        br.close();
    }
}
