package baekjoon.bruteforce;

import java.io.*;
import java.util.*;

/*
https://www.acmicpc.net/problem/12100 - 2048(Easy)(Gold)
Time: 208ms (Java 11)
Memory: 26828KB

💡 풀이 포인트
  - 백트래킹 + DFS
    → 최대 5번의 이동을 모든 경우의 수로 시도 (4^5 = 1024가지)
    → 각 depth에서 4방향(상하좌우) 모두 탐색

  - 이동 가능성 체크
    → isSameBoard(): 실제로 보드가 변했을 때만 다음 depth로 진행

  - 블록 이동 및 합치기 로직
    → 3단계 처리: ①빈 공간 제거 → ②같은 값 합치기 → ③결과 배치
    → 한 번 합친 블록은 재합치 불가: 합친 후 배열을 앞으로 당겨서 처리
    → 방향별 구현: 각 방향마다 시작점과 순회 방향이 다름

  - 매 단계 최댓값 갱신
    → depth 5에서만 확인하지 않고 모든 단계에서 updateMaxValue() 호출
    → 5번 이동하지 않아도 최댓값이 나올 수 있음
*/

public class Q12100 {
    static int N;
    static int maxValue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(board, 0);
        System.out.println(maxValue);
    }

    static void dfs(int[][] board, int depth) {
        updateMaxValue(board);

        if (depth == 5) {
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int[][] newBoard = move(board, dir);
            if (!isSameBoard(board, newBoard)) {
                dfs(newBoard, depth + 1);
            }
        }
    }

    static void updateMaxValue(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxValue = Math.max(maxValue, board[i][j]);
            }
        }
    }

    static boolean isSameBoard(int[][] board1, int[][] board2) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board1[i][j] != board2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 방향에 따른 이동 (0:상, 1:하, 2:좌, 3:우)
    static int[][] move(int[][] board, int direction) {
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        switch (direction) {
            case 0: // 상
                moveUp(newBoard);
                break;
            case 1: // 하
                moveDown(newBoard);
                break;
            case 2: // 좌
                moveLeft(newBoard);
                break;
            case 3: // 우
                moveRight(newBoard);
                break;
        }

        return newBoard;
    }

    static void moveUp(int[][] board) {
        for (int j = 0; j < N; j++) {
            int[] temp = new int[N];
            int idx = 0;

            for (int i = 0; i < N; i++) {
                if (board[i][j] != 0) {
                    temp[idx++] = board[i][j];
                }
            }

            for (int i = 0; i < idx - 1; i++) {
                if (temp[i] == temp[i + 1]) {
                    temp[i] *= 2;
                    for (int k = i + 1; k < idx - 1; k++) {
                        temp[k] = temp[k + 1];
                    }
                    temp[idx - 1] = 0;
                    idx--;
                }
            }

            for (int i = 0; i < N; i++) {
                board[i][j] = (i < idx) ? temp[i] : 0;
            }
        }
    }

    static void moveDown(int[][] board) {
        for (int j = 0; j < N; j++) {
            int[] temp = new int[N];
            int idx = 0;

            for (int i = N - 1; i >= 0; i--) {
                if (board[i][j] != 0) {
                    temp[idx++] = board[i][j];
                }
            }

            for (int i = 0; i < idx - 1; i++) {
                if (temp[i] == temp[i + 1]) {
                    temp[i] *= 2;
                    for (int k = i + 1; k < idx - 1; k++) {
                        temp[k] = temp[k + 1];
                    }
                    temp[idx - 1] = 0;
                    idx--;
                }
            }

            for (int i = N - 1; i >= 0; i--) {
                int tempIdx = N - 1 - i;
                board[i][j] = (tempIdx < idx) ? temp[tempIdx] : 0;
            }
        }
    }

    static void moveLeft(int[][] board) {
        for (int i = 0; i < N; i++) {
            int[] temp = new int[N];
            int idx = 0;

            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    temp[idx++] = board[i][j];
                }
            }

            for (int j = 0; j < idx - 1; j++) {
                if (temp[j] == temp[j + 1]) {
                    temp[j] *= 2;
                    for (int k = j + 1; k < idx - 1; k++) {
                        temp[k] = temp[k + 1];
                    }
                    temp[idx - 1] = 0;
                    idx--;
                }
            }

            for (int j = 0; j < N; j++) {
                board[i][j] = (j < idx) ? temp[j] : 0;
            }
        }
    }

    static void moveRight(int[][] board) {
        for (int i = 0; i < N; i++) {
            int[] temp = new int[N];
            int idx = 0;

            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    temp[idx++] = board[i][j];
                }
            }

            for (int j = 0; j < idx - 1; j++) {
                if (temp[j] == temp[j + 1]) {
                    temp[j] *= 2;
                    for (int k = j + 1; k < idx - 1; k++) {
                        temp[k] = temp[k + 1];
                    }
                    temp[idx - 1] = 0;
                    idx--;
                }
            }

            for (int j = N - 1; j >= 0; j--) {
                int tempIdx = N - 1 - j;
                board[i][j] = (tempIdx < idx) ? temp[tempIdx] : 0;
            }
        }
    }
}