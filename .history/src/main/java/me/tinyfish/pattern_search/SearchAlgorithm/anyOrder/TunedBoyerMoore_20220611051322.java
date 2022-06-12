package me.tinyfish.pattern_search.SearchAlgorithm.anyOrder;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TunedBoyerMoore implements SearchAlgorithm {
    private static final int ASCII_SIZE = 256;

    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();

        /* Preprocessing */
        int[] bmBc = preBmBc(pattern);
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

    private boolean cmp(char[] pattern, char[] text, int startPos) {
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] != text[startPos++]) {
                return false;
            }
        }
        return true;
    }

    //order number of char from right
    private int[] preBmBc(char[] pattern) {
        int[] bmBc = new int[ASCII_SIZE];
        int i;
        for (i = 0; i < ASCII_SIZE; ++i)
            bmBc[i] = pattern.length;
        for (i = 0; i < pattern.length - 1; ++i)
            bmBc[pattern[i]] = pattern.length - i - 1;
        return bmBc;
    }
}
