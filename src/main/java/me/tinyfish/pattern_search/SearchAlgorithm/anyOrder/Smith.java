package me.tinyfish.pattern_search.SearchAlgorithm.anyOrder;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;
import me.tinyfish.pattern_search.SearchAlgorithm.rightToLeft.BoyerMoore;

import java.util.ArrayList;
import java.util.List;

public class Smith implements SearchAlgorithm {
    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();
        /* Preprocessing */
        int[] bmBc = new BoyerMoore().preBmBc(pattern);//bad-character shift Horspool
        int[] qsBc = new QuickSearch().preQsBc(pattern);//bad-character shift Quick Search

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
}
