package programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/42898

public class WayToSchool {
    static final int MOD = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m + 1][n + 1];
        dp[0][1] = 1;

        for (int i = 0; i < puddles.length; i++) {
            dp[puddles[i][0]][puddles[i][1]] = -1;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }

                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
            }
        }

        return dp[m][n];
    }
}
