package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/16953 - A → B(Silver)
Time: 92ms (Java 11)
Memory: 14172KB

💡 풀이 포인트
  - 역방향 탐색
*/

public class Q16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int count = 0;

        while (B > A) {
            if (B % 2 == 0) {
                B /= 2;
            } else if (B % 10 == 1) {
                B = (B - 1) / 10;
            } else {
                break;
            }
            count++;
        }

        System.out.println(B == A ? count + 1 : -1);
        br.close();
    }
}