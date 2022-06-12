package me.tinyfish.pattern_search.SearchAlgorithm.rightToLeft;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class ZhuKataoka implements SearchAlgorithm {
    private static final int ASCII_SIZE = 256;
    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();
        /* Preprocessing */
        int[] bmGs = new BoyerMoore().preBmGs(pattern);
        int[][] ztBc = preZtBc(pattern);

        /* Searching */
        int j = 0;
        while (j <= text.length - pattern.length) {
            int i;
            for (i = pattern.length - 1; i >= 0 && pattern[i] == text[i + j]; --i);
            if (i < 0) {
                result.add(j);
                j += bmGs[0];
            }
            else
                j += Math.max(bmGs[i],
                        ztBc[text[j + pattern.length - 2]][text[j + pattern.length - 1]]);
        }
        return result;
    }

    private int[][] preZtBc(char[] pattern) {
        int[][] ztBc = new int[ASCII_SIZE][ASCII_SIZE];
        for (int i = 0; i < ASCII_SIZE; ++i)
            for (int j = 0; j < ASCII_SIZE; ++j)
                ztBc[i][j] = pattern.length;
        for (int i = 0; i < ASCII_SIZE; ++i)
            ztBc[i][pattern[0]] = pattern.length - 1;
        for (int i = 1; i < pattern.length - 1; ++i)
            ztBc[pattern[i - 1]][pattern[i]] = pattern.length - 1 - i;
        return ztBc;
    }
}
