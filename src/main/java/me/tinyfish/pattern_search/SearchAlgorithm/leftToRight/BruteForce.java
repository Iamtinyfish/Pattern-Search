package me.tinyfish.pattern_search.SearchAlgorithm.leftToRight;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class BruteForce implements SearchAlgorithm {
    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();

        for (int j = 0; j <= text.length - pattern.length; ++j) {
            for (int i = 0; i < pattern.length && pattern[i] == text[j + i]; ++i) {
                if (i == pattern.length - 1) result.add(j);
            }
        }
        return result;
    }

//    public List<Integer> search(String text, String pattern) {
//        List<Integer> result = new ArrayList<>();
//
//        int t_len = text.length();
//        int p_len = pattern.length();
//
//        for (int i = 0; i <= t_len - p_len; ++i) {
//            if (text.substring(i, i + p_len).equals(pattern))
//                result.add(i);
//        }
//
//        return result;
//    }
}
