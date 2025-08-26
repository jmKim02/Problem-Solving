package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
https://www.acmicpc.net/problem/1644 - 소수의 연속합(Gold)
Time: 188ms (Java 11)
Memory: 26892KB

💡 풀이 포인트
  - 에라토스테네스의 체로 N 이하의 소수를 O(N log log N) 시간에 구하기
  - 투 포인터 기법으로 연속된 소수의 합을 O(P) 시간에 계산 (P = 소수 개수)
*/

public class Q1644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 1. Get all primes up to N using Sieve of Eratosthenes
        List<Integer> primes = Eratosthenes(N);

        // 2. Count ways to represent N as sum of consecutive primes using two pointers
        int result = countPrimeSums(N, primes);

        System.out.println(result);
        br.close();
    }

    private static List<Integer> Eratosthenes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];

        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    private static int countPrimeSums(int N, List<Integer> primes) {
        int count = 0;
        int sum = 0;
        int left = 0;

        for (int right = 0; right < primes.size(); right++) {
            sum += primes.get(right);

            while (sum > N && left <= right) {
                sum -= primes.get(left++);
            }

            if (sum == N) {
                count++;
            }
        }

        return count;
    }
}
