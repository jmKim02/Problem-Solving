package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://www.acmicpc.net/problem/1461 - 도서관(Gold)
Time: 104ms (Java 11)
Memory: 14284KB

💡 풀이 포인트
  - 양수, 음수 분리 후 정렬
    • 양수는 내림차순 (큰 것부터)
    • 음수는 오름차순 (절댓값 큰 것부터)
  - M개씩 묶어서 처리
    • 각 그룹에서 가장 먼 곳까지만 가면 됨 (왕복 * 2)
  - 최대 거리 계산
    • 가장 먼 곳은 마지막에 가서 돌아오지 않음 (한 번만 빼기)
*/

public class Q1461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }

        Collections.sort(positive, Collections.reverseOrder());
        Collections.sort(negative);

        int maxDistance = 0;
        int result = 0;

        for (int i = 0; i < positive.size(); i += M) {
            result += (positive.get(i) * 2);
            maxDistance = Math.max(maxDistance, positive.get(i));
        }

        for (int i = 0; i < negative.size(); i += M) {
            result += Math.abs(negative.get(i) * 2);
            maxDistance = Math.max(maxDistance, Math.abs(negative.get(i)));
        }

        result -= maxDistance;
        System.out.println(result);
        br.close();
    }
}
