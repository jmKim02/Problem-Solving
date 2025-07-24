package programmers;

import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/42748 - 완주하지 못한 선수

public class NonFinisher {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> coms = new HashMap<>();

        for (String c : completion) {
            coms.put(c, coms.getOrDefault(c, 0) + 1);
        }

        for (String p : participant) {
            Integer count = coms.get(p);
            if (count == null || count == 0) {
                return p;
            }
            coms.put(p, count - 1);
        }

        return null;
    }
}
