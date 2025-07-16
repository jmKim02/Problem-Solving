package leetcode;

import java.util.Arrays;

/*
https://leetcode.com/problems/reorder-data-in-log-files/description/
Runtime: 3ms    | Beats: 89.43%
Memory: 44.5MB  | Beats: 95.39%
 */

public class ReorderData {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            int space1 = log1.indexOf(' ');
            int space2 = log2.indexOf(' ');

            char firstChar1 = log1.charAt(space1 + 1);
            char firstChar2 = log2.charAt(space2 + 1);

            boolean isDigit1 = firstChar1 >= '0' && firstChar1 <= '9';
            boolean isDigit2 = firstChar2 >= '0' && firstChar2 <= '9';

            // Both are letter-logs
            if (!isDigit1 && !isDigit2) {
                String content1 = log1.substring(space1 + 1);
                String content2 = log2.substring(space2 + 1);

                int contentCompare = content1.compareTo(content2);
                if (contentCompare != 0) {
                    return contentCompare;
                }

                String id1 = log1.substring(0, space1);
                String id2 = log2.substring(0, space2);
                return id1.compareTo(id2);
            }

            // Both are digit-logs (maintain relative order)
            if (isDigit1 && isDigit2) {
                return 0;
            }

            // One letter, one digit (letter first)
            return isDigit1 ? 1 : -1;
        });

        return logs;
    }
}
