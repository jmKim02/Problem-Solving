package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/42579 - 베스트앨범

public class BestAlbum {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<int[]>> genreMap = new HashMap<>();
        Map<String, Integer> genreSum = new HashMap<>();

        // 1. Store song into by genre + calculate total plays per genre
        for (int i = 0; i < genres.length; i++) {
            genreMap.computeIfAbsent(genres[i], k -> new ArrayList<>())
                    .add(new int[]{i, plays[i]});

            genreSum.put(genres[i], genreSum.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 2. Sort genres by total plays in descending order
        List<String> sortedGenres = genreSum.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // 3.List to store result
        List<Integer> result = new ArrayList<>();

        // 4. Process each genre
        for (String genre : sortedGenres) {
            List<int[]> songs = genreMap.get(genre);

            songs.sort((s1, s2) -> {
                return s2[1] - s1[1];
            });

            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                result.add(songs.get(i)[0]);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
