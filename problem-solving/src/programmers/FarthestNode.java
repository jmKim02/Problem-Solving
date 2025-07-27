package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/49189 - 가장 먼 노드

public class FarthestNode {
    List<List<Integer>> graph = new ArrayList<>();
    boolean[] visited;

    public int solution(int n, int[][] edge) {
        visited = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edge.length; i++) {
            graph.get(edge[i][0]).add(edge[i][1]);
            graph.get(edge[i][1]).add(edge[i][0]);
        }

        return bfs(1, n);
    }

    public int bfs(int v, int len) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{v, 0});
        visited[v] = true;

        int[] depthCount = new int[len + 1];
        int maxDepth = 0;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curDepth = cur[1];

            depthCount[curDepth]++;
            maxDepth = Math.max(maxDepth, curDepth);

            for (int w : graph.get(cur[0])) {
                if (!visited[w]) {
                    queue.offer(new int[]{w, curDepth + 1});
                    visited[w] = true;
                }
            }
        }

        return depthCount[maxDepth];
    }
}
