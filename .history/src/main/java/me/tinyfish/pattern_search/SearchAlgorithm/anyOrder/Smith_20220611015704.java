package me.tinyfish.pattern_search.SearchAlgorithm.anyOrder;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Smith implements SearchAlgorithm {
    private static final int ASCII_SIZE = 256;

    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();
        /* Preprocessing */
        int[] bmBc = preBmBc(pattern);
        int[] qsBc = preQsBc(pattern);

        /* Searching */
        int i = 0;
        while (i <= text.length - pattern.length) {
            if (cmp(pattern, text, i))
                result.add(i);
            i += Math.max(bmBc[text[i + pattern.length - 1]],
                    qsBc[text[i + pattern.length]]);
        }
        return result;
    }

    private int[] preBmBc(char[] pattern) {
        int[] bmBc = new int[ASCII_SIZE];
        int i;
        for (i = 0; i < ASCII_SIZE; ++i)
            bmBc[i] = pattern.length;
        for (i = 0; i < pattern.length - 1; ++i)
            bmBc[pattern[i]] = pattern.length - i - 1;
        return bmBc;
    }

    private int[] preQsBc(char[] pattern) {
        int[] qsBc = new int[ASCII_SIZE];
        int i;
        for (i = 0; i < ASCII_SIZE; ++i)
            qsBc[i] = pattern.length + 1;
        for (i = 0; i < pattern.length - 1; ++i)
            qsBc[pattern[i]] = pattern.length - i;
        return qsBc;
    }

    private boolean cmp(char[] pattern, char[] text, int j) {
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] != text[j++]) {
                return false;
            }
        }
        return true;
    }
}
