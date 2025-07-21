package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/1987 - 알파벳(Gold)
Time: 912ms (Java 11)
Memory: 15652KB

💡 풀이 포인트
  - DFS + 백트래킹: 모든 가능한 경로를 탐색하되, 조건에 맞지 않으면 되돌아가기
  - 방문 체크 최적화: 대문자 알파벳 26개 고정 → boolean[26] 배열로 O(1) 검색
  - 메모리 효율: HashSet 대신 boolean 배열 사용으로 성능 향상
*/

public class Q1987 {
    static int N, M;
    static char[][] arr;
    static boolean[] visited = new boolean[26];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = input.charAt(j);
                arr[i + 1][j + 1] = ch;
            }
        }

        dfs(1, 1, 1);
        System.out.println(maxCount);
        br.close();
    }

    private static void dfs(int x, int y, int count) {
        visited[arr[x][y] - 'A'] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx > 0 && nx <= N && ny > 0 && ny <= M && !visited[arr[nx][ny] - 'A']) {
                dfs(nx, ny, count + 1);
                visited[arr[nx][ny] - 'A'] = false;
            }
        }

        maxCount = Math.max(maxCount, count);
    }
}
