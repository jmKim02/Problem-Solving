package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/1520 - 내리막 길(Gold)
Time: 344ms (Java 11)
Memory: 36700KB

💡 풀이 포인트
  - DFS + DP: 단순 DFS는 시간초과 발생!
  - 목적지에 도달한 경우에는 1을 반환하고, 이미 방문한 위치의 경로 수는 DP 배열에 캐싱하여 재사용한다.
*/

public class Q1520 {
    static int N, M;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.print(dfs(0, 0));
        br.close();
    }

    private static int dfs(int x, int y) {
        if (x == N - 1 && y == M - 1) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] < map[x][y]) {
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y];
    }
}
