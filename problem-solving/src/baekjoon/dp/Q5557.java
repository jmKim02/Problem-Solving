package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/5557 - 1학년(Gold)
Time: 100ms (Java 11)
Memory: 14204KB

💡 풀이 포인트
  - 2차원 DP: dp[위치][합] 형태로 상태 관리
  - 범위 제한: 중간 계산 결과가 0~20 범위 내에서만 유효
  - 마지막 처리: 등호 오른쪽은 연산 대상이 아니므로 dp[n-2][target] 확인
*/

public class Q5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[n - 1][21];
        dp[0][nums[0]] = 1;

        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j < 21; j++) {
                if (dp[i - 1][j] > 0) {
                    if (j + nums[i] <= 20) {
                        dp[i][j + nums[i]] += dp[i - 1][j];
                    }

                    if (j - nums[i] >= 0) {
                        dp[i][j - nums[i]] += dp[i - 1][j];
                    }
                }
            }
        }

        System.out.println(dp[n - 2][nums[n - 1]]);
        br.close();
    }
}
