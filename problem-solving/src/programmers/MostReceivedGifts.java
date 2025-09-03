package programmers;

import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/258712
// 2024 KAKAO WINTER INTERNSHIP - 가장 많이 받은 선물

/*
 * 현재 방식: HashMap 사용 (가독성 우선)
 * 대안 방식: 이름을 int 인덱스로 매핑 후 int[] 배열 사용 (성능 우선)
 *          - Map<String, Integer> nameToIndex로 매핑
 *          - int[][] giftRecord, int[] giftIndex, int[] nextMonthGifts
 */

public class MostReceivedGifts {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> giftIndex = new HashMap<>();
        Map<String, Map<String, Integer>> giftRecord = new HashMap<>();
        Map<String, Integer> nextMonthGifts = new HashMap<>();

        // 1. 사람 파싱해서 저장
        for (String friend : friends) {
            giftIndex.put(friend, 0);
            giftRecord.put(friend, new HashMap<>());
            nextMonthGifts.put(friend, 0);
        }

        // 2. 선물 주고 받은 내역 계산
        for (String gift : gifts) {
            String[] temp = gift.split(" ");
            String A = temp[0];
            String B = temp[1];

            giftRecord.get(A).put(B, giftRecord.get(A).getOrDefault(B, 0) + 1);

            giftIndex.put(A, giftIndex.get(A) + 1);
            giftIndex.put(B, giftIndex.get(B) - 1);
        }

        // 3. 다음달 선물 계산
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                String personA = friends[i];
                String personB = friends[j];

                int aToB = giftRecord.get(personA).getOrDefault(personB, 0);
                int bToA = giftRecord.get(personB).getOrDefault(personA, 0);

                if (aToB > bToA) {
                    nextMonthGifts.put(personA, nextMonthGifts.get(personA) + 1);
                } else if (bToA > aToB) {
                    nextMonthGifts.put(personB, nextMonthGifts.get(personB) + 1);
                } else {
                    int indexA = giftIndex.get(personA);
                    int indexB = giftIndex.get(personB);

                    if (indexA > indexB) {
                        nextMonthGifts.put(personA, nextMonthGifts.get(personA) + 1);
                    } else if (indexB > indexA) {
                        nextMonthGifts.put(personB, nextMonthGifts.get(personB) + 1);
                    }
                }
            }
        }

        int maxCount = 0;
        for (int count : nextMonthGifts.values()) {
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }
}
