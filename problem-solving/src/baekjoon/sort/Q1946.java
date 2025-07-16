package baekjoon.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/1946 - 신입 사원(Silver)
Time: 864ms (Java 11)
Memory: 299140KB

💡 풀이 포인트
  - 일반적인 2차원 정렬 방식은 시간 초과 발생 가능성이 있음
  - 서류 순위 기준으로 정렬할 필요 없이 배열을 그대로 사용 가능 → ranks[doc] = interview;
  - 이전까지의 면접 성적 최소값만 유지하면 된다 → if (ranks[j] < minRank)
    → 서류 순위가 앞선 지원자 중 면접 성적 최솟값보다 현재 지원자의 면접 성적이 낮으면 선발 가능.
*/

public class Q1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] ranks = new int[N + 1];

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int doc =  Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());

                ranks[doc] = interview;
            }

            int count = 1;
            int minRank = ranks[1];
            for (int j = 2; j < N + 1; j++) {
                if (ranks[j] < minRank) {
                    minRank = ranks[j];
                    count++;
                }
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
