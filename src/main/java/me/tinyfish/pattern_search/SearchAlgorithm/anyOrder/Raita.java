package me.tinyfish.pattern_search.SearchAlgorithm.anyOrder;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;
import me.tinyfish.pattern_search.SearchAlgorithm.rightToLeft.BoyerMoore;

import java.util.ArrayList;
import java.util.List;

public class Raita implements SearchAlgorithm {

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
            char first = pattern[0];

            if (pattern[0] == text[i]           // first
                    && pattern[pattern.length - 1] == text[i + pattern.length - 1] //last
                    && pattern[pattern.length / 2] == text[i + pattern.length / 2] //middle
                    && cmp(pattern, text, i))   //from 1 - pattern.length - 2
                result.add(i);
            i += bmBc[c];
        }
        return result;
    }
}
