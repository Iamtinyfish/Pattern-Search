package me.tinyfish.pattern_search.SearchAlgorithm.anyOrder;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class QuickSearch implements SearchAlgorithm {
    private static final int ASCII_SIZE = 256;

    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();
        /* Preprocessing */
        int[] qsBc = preQsBc(pattern);

        /* Searching */
        int i = 0;
        char c;
        while (i <= text.length - pattern.length) {
            c = text[i + pattern.length];
            if (cmp(pattern, text, i))
                result.add(i);
            i += qsBc[c];
        }
        return result;
    }

    public int[] preQsBc(char[] pattern) {
        int[] qsBc = new int[ASCII_SIZE];
        int i;
        for (i = 0; i < ASCII_SIZE; ++i)
            qsBc[i] = pattern.length + 1;
        for (i = 0; i < pattern.length - 1; ++i)
            qsBc[pattern[i]] = pattern.length - i;
        return qsBc;
    }
}
