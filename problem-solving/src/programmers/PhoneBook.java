package programmers;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42577 - 전화번호 목록

public class PhoneBook {
    // Time limit exceeded! - X
    public boolean solutionV1(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length; i++) {
            for (int j = i + 1; j < phone_book.length; j++) {
                if (phone_book[j].startsWith(phone_book[i])) {
                    return false;
                }
            }
        }

        return true;
    }

    // If the array is sorted, prefixes will always be adjacent! - O
    public boolean solutionV2(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}
