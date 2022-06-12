package me.tinyfish.pattern_search.SearchAlgorithm.leftToRight;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class NotSoNaive implements SearchAlgorithm {
    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();
        //if pattern[0] == pattern[1]
        int shiftIfNotEqual = 2; //pattern[1] != text[i+1]
        int shiftIfEqual = 1; //pattern[1] = text[i+1]

        //ìf pattern[0] != pattern[1]
        if (pattern[0] != pattern[1]) {
            shiftIfEqual = 2;
            shiftIfNotEqual = 1;
        }

        int i = 0;
        while (i <= text.length - pattern.length) {
            if (pattern[1] == text[i + 1]) {
                //compare 2 -> (pattern.length - 1)
                if (cmp(pattern,2,text,i + 2))
                    result.add(i);
                i += shiftIfEqual;
            } else
                i += shiftIfNotEqual;
        }
        return result;
    }
}
