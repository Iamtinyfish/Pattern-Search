package me.tinyfish.pattern_search.SearchAlgorithm.anyOrder;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;
import me.tinyfish.pattern_search.SearchAlgorithm.rightToLeft.BoyerMoore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TunedBoyerMoore implements SearchAlgorithm {

    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();

        /* Preprocessing */
        int[] bmBc = new BoyerMoore().preBmBc(pattern);
        char endCharPattern = pattern[pattern.length - 1];
        int shift = bmBc[endCharPattern];
        bmBc[endCharPattern] = 0;

        //text.length + pattern.length
        char[] newText = Arrays.copyOf(text,text.length + pattern.length);
        //extra length text = end character pattern
        Arrays.fill(newText,text.length,newText.length,endCharPattern);

        /* Searching */
        int i = 0, k;
        while (i < text.length) {
            k = bmBc[newText[i + pattern.length - 1]];
            while (k != 0) {
                i += k; k = bmBc[newText[i + pattern.length - 1]];
                i += k; k = bmBc[newText[i + pattern.length - 1]];
                i += k; k = bmBc[newText[i + pattern.length - 1]];
            }

            if (cmp(pattern, newText, i))
                result.add(i);
            i += shift;
        }
        return result;
    }
}
