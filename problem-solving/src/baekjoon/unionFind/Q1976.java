package baekjoon.unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/1976 - 여행 가자(Gold)
Time: 164ms (Java 11)
Memory: 17448KB

💡 풀이 포인트
  - Union-Find로 연결된 도시들을 그룹화
     → 직접 연결된 도시들을 union()으로 같은 컴포넌트로 병합
     → 경로 압축으로 find() 연산 최적화 (O(α(n)) ≈ 상수시간)

  - 조기 종료로 성능 최적화
     → 불가능한 경로 발견 시 즉시 탐색 중단
*/

public class Q1976 {
    static int[] parent;

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[x] = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int connected = Integer.parseInt(st.nextToken());
                if (connected == 1) {
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] plan = new int[m];
        for (int i = 0; i < m; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        boolean possible = true;
        for (int i = 0; i < m - 1; i++) {
            if (find(plan[i]) != find(plan[i + 1])) {
                possible = false;
                break;
            }
        }

        System.out.println(possible ? "YES" : "NO");
    }
}