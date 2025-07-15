package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/11724 - 연결 요소의 개수(Silver)
Time: 568ms (Java 11)
Memory: 141640KB

💡 풀이 포인트
  - 방문하지 않은 노드들에 대해 BFS 반복
*/

public class Q11724 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N + 1; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        visited = new boolean[N + 1];
        count = 0;

        for (int i = 1; i < N + 1; i++) {
            if (!visited[i]) {
                bfs(i);
                count++;
            }
        }

        System.out.print(count);
        br.close();
    }

    public static void bfs(int start) {
        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int node : graph.get(cur)) {
                if (!visited[node]) {
                    visited[node] = true;
                    q.offer(node);
                }
            }
        }
    }
}
