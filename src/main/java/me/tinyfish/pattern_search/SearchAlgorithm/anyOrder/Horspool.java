package me.tinyfish.pattern_search.SearchAlgorithm.anyOrder;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;
import me.tinyfish.pattern_search.SearchAlgorithm.rightToLeft.BoyerMoore;

import java.util.ArrayList;
import java.util.List;

public class Horspool implements SearchAlgorithm {

    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();
        /* Preprocessing */
        int[] bmBc = new BoyerMoore().preBmBc(pattern);

        /* Searching */
        int i = 0;
        char c;
        while (i <= text.length - pattern.length) {
            c = text[i + pattern.length - 1];
            if (pattern[pattern.length - 1] == c && cmp(pattern, text, i))
                result.add(i);
            i += bmBc[c];
        }
        return result;
    }
}
