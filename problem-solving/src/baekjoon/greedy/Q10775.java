package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://www.acmicpc.net/problem/10775 - 공항(Gold)
Time: 212ms (Java 11)
Memory: 27556KB

- 첫 시도: 단순 그리디(매번 반복문 탐색) → 시간 초과

💡 풀이 포인트
  - 그리디 전략: 가능한 범위에서 가장 큰 번호 게이트에 도킹
  - Union-Find 최적화: 다음 사용 가능한 게이트를 O(α(n)) 시간에 찾기
    → parent[i] = i번 게이트부터 시작해서 사용 가능한 다음 게이트 번호
    → 게이트 사용 시 parent[i] = i-1로 갱신 (다음 후보로 연결)

  🎯 백준은 탐색이 중단되어야 하는 경우 입력예시에 있는 값들을 모두 입력하지 않아도 된다!
*/

public class Q10775 {
    static int[] parent;

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];
        for (int i = 0; i < G + 1; i++) {
            parent[i] = i;
        }

        int dockedPlanes = 0;

        for (int i = 0; i < P; i++) {
            int gi = Integer.parseInt(br.readLine());

            // Find the largest available gate <= gi
            int availableGate = find(gi);

            // If no gate is available (returns 0), close the airport
            if (availableGate == 0) {
                break;
            }

            parent[availableGate] = availableGate - 1;
            dockedPlanes++;
        }

        System.out.println(dockedPlanes);
        br.close();
    }
}