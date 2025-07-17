package programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/42842 - 카펫

public class Carpet {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;

        // Height must be at least 3
        for (int col = 3; col <= Math.sqrt(total); col++) {
            if (total % col == 0) {
                int row = total / col;

                // According to the problem, width >= height
                if (row >= col) {
                    int yCount = (row - 2) * (col - 2);
                    int bCount = total - yCount;

                    if (bCount == brown && yCount == yellow) {
                        return new int[]{row, col};
                    }
                }
            }
        }

        return null;
    }
}
