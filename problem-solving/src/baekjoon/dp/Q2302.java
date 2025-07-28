package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
https://www.acmicpc.net/problem/2302 - 극장 좌석(Gold)
Time: 104ms (Java 11)
Memory: 14048KB

💡 풀이 포인트
  - Fibonacci 점화식: dp[i] = dp[i-1] + dp[i-2]
    • i번째 사람이 자기 자리에 앉는 경우: dp[i-1]
    • i번째 사람이 (i-1)번 자리에 앉는 경우: dp[i-2]
  - VIP석을 기준으로 구간 분할 후 각 구간의 경우의 수를 곱셈
    • 더미 노드(0, N+1) 활용으로 경계 처리
    • 연속된 VIP석 처리를 위한 dp[0] = 1 설정
*/

public class Q2302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i = 0; i < M; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        list.add(N + 1);

        if (N == 1) {
            System.out.println(1);
            return;
        }

        if (N == 2) {
            System.out.println(2);
            return;
        }

        int result = 1;
        int[] dp = new int[N+1];
        dp[0] = 1; // 0 곱셈 방지 (연속된 VIP석 처리)
        dp[1] = 1;
        dp[2] = 2;

        // fibonacci
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        for (int i = 1; i < list.size(); i++) {
            int prev = list.get(i - 1);
            int next = list.get(i);
            int diff = next - prev - 1;

            result *= dp[diff];
        }

        System.out.print(result);
        br.close();
    }
}
