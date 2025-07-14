package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://www.acmicpc.net/problem/1260 - DFS와 BFS(Silver)
Time: 208ms (Java 11)
Memory: 20044KB

💡 풀이 포인트
  - DFS는 재귀로 구현, BFS는 큐 사용
  - 각 정점의 인접 노드를 오름차순 정렬하여 방문 순서를 보장
  - visited 배열은 DFS와 BFS 각각 따로 사용
*/

public class Q1260 {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];

        for(int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 0; i < N + 1; i++) {
            Collections.sort(graph.get(i));
        }

        dfs(V);

        sb.append("\n");
        visited = new boolean[N + 1];

        bfs(V);

        System.out.print(sb);
    }

    public static void dfs(int node) {
        visited[node] = true;
        sb.append(node).append(" ");

        for (int i = 0; i < graph.get(node).size(); i++) {
            int newNode = graph.get(node).get(i);

            if (!visited[newNode]) {
                dfs(newNode);
            }
        }
    }

    public static void bfs(int node) {
        visited[node] = true;
        sb.append(node).append(" ");

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);

        while(!queue.isEmpty()) {
            node = queue.poll();

            for (int i = 0; i < graph.get(node).size(); i++) {
                int newNode = graph.get(node).get(i);

                if (!visited[newNode]) {
                    visited[newNode] = true;
                    sb.append(newNode).append(" ");
                    queue.offer(newNode);
                }
            }
        }
    }
}
