package me.tinyfish.pattern_search.SearchAlgorithm;

import java.util.List;

public interface SearchAlgorithm {
    List<Integer> search(char[] text, char[] pattern);

    default boolean cmp(char[] pattern, char[] text, int startTextPos) {
        if (text.length - startTextPos < pattern.length) return false;
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] != text[startTextPos++]) {
                return false;
            }
        }
        return true;
    }

    default boolean cmp(char[] pattern, int startPatternPos, char[] text, int startTextPos) {
        for (int i = startPatternPos; i < pattern.length; i++) {
            if (pattern[i] != text[startTextPos++])
                return false;
        }
        return true;
    }
}
