package baekjoon.unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/4195 - 친구 네트워크(Gold)
Time: 716ms (Java 11)
Memory: 75812KB

💡 풀이 포인트
  - Union-Find 자료구조로 실시간 그룹 병합 및 크기 조회
  - parent: 각 노드의 부모를 저장 (루트는 자기 자신을 가리킴)
  - size: 각 그룹의 크기 저장 (루트 노드만 유효한 값 보유)
  - 경로 압축(Path Compression): find 시 만난 모든 노드를 루트에 직접 연결
  - Union by Size: 작은 트리를 큰 트리에 병합하여 트리 높이 최소화
  - 시간복잡도: O(F × α(N)) ≈ O(F) - 거의 선형 시간
*/

public class Q4195 {
    static Map<String, String> parent;
    static Map<String, Integer> size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T --> 0) {
            int F = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            size = new HashMap<>();

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if (!parent.containsKey(f1)) {
                    parent.put(f1, f1);
                    size.put(f1, 1);
                }
                if (!parent.containsKey(f2)) {
                    parent.put(f2, f2);
                    size.put(f2, 1);
                }

                sb.append(union(f1, f2)).append("\n");
            }
        }

        System.out.print(sb);
        br.close();
    }

    private static int union(String f1, String f2) {
        String rootF1 = find(f1);
        String rootF2 = find(f2);

        if (rootF1.equals(rootF2)) {
            return size.get(rootF1);
        }

        if (size.get(rootF1) < size.get(rootF2)) {
            parent.put(rootF1, rootF2);
            size.put(rootF2, size.get(rootF2) + size.get(rootF1));
            return size.get(rootF2);
        } else {
            parent.put(rootF2, rootF1);
            size.put(rootF1, size.get(rootF1) + size.get(rootF2));
            return size.get(rootF1);
        }
    }

    private static String find(String f) {
        if (!parent.get(f).equals(f)) {
            parent.put(f, find(parent.get(f)));
        }
        return parent.get(f);
    }
}
