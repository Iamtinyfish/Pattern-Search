package me.tinyfish.pattern_search.SearchAlgorithm.rightToLeft;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class BoyerMoore implements SearchAlgorithm {
    private static final int ASCII_SIZE = 256;
    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();
        /* Preprocessing */
        int[] bmGs = preBmGs(pattern);
        int[] bmBc = preBmBc(pattern);

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
                j += Math.max(bmGs[i], bmBc[text[i + j]] - pattern.length + 1 + i);
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

    private int[] suffixes(char[] pattern) {
        int[] suff = new int[pattern.length];
        int f = 0;
        suff[pattern.length - 1] = pattern.length;
        int g = pattern.length - 1;
        for (int i = pattern.length - 2; i >= 0; --i) {
            if (i > g && suff[i + pattern.length - 1 - f] < i - g)
                suff[i] = suff[i + pattern.length - 1 - f];
            else {
                if (i < g) g = i;
                f = i;
                while (g >= 0 && pattern[g] == pattern[g + pattern.length - 1 - f])
                    --g;
                suff[i] = f - g;
            }
        }
        return suff;
    }
    
    private int[] preBmGs(char[] pattern) {
        int i, j;
        int[] bmGs = new int[pattern.length];
        int[] suff = suffixes(pattern);
        for (i = 0; i < pattern.length; ++i)
            bmGs[i] = pattern.length;
        j = 0;
        for (i = pattern.length - 1; i >= 0; --i)
            if (suff[i] == i + 1)
                for (; j < pattern.length - 1 - i; ++j)
                    if (bmGs[j] == pattern.length)
                        bmGs[j] = pattern.length - 1 - i;
        for (i = 0; i <= pattern.length - 2; ++i)
            bmGs[pattern.length - 1 - suff[i]] = pattern.length - 1 - i;
        return bmGs;
    }

}
