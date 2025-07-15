package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/11053 - 가장 긴 증가하는 부분 수열(Silver)
Time: 128ms (Java 11)
Memory: 14924KB

💡 풀이 포인트
  - Arrays.fill(dp, 1) → 모든 원소는 자기 자신만으로 길이 1의 부분 수열이 되므로 1로 초기화
  - 점화식 → dp[i] = Math.max(dp[i], dp[j] + 1);
  - 최대값 구하기 → Arrays.stream(dp).max()
    stream, for 루프, Math.max() 등 다양한 방법으로 구현 가능
*/

public class Q11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        System.out.print(Arrays.stream(dp).max().getAsInt());
        br.close();
    }
}
