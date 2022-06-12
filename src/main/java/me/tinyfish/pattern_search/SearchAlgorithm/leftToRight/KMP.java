package me.tinyfish.pattern_search.SearchAlgorithm.leftToRight;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class KMP implements SearchAlgorithm {
    private boolean isLog;

    public KMP(boolean isLog) {
        this.isLog = isLog;
    }

    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();
        int[] KMPNext = preKMP(pattern);
        int i = 0;
        int j = 0;
        while (j < text.length) {
            while (i > -1 && text[j] != pattern[i])
                i = KMPNext[i];
            i++;j++;
            if (i == pattern.length) {
                result.add(j-i);
                i = KMPNext[i];
            }
        }
        return result;
    }

    private int[] preKMP(char[] pattern) {
        int[] KMPNext = new int[pattern.length + 1];
        int i = 0;
        int j = KMPNext[0] = -1;
        while (i < pattern.length) {
            while (j > -1 && pattern[i] != pattern[j])
                j = KMPNext[j];
            i++;j++;
            if (pattern[i] == pattern[j])
                KMPNext[i] = KMPNext[j];
            else
                KMPNext[i] = j;
        }
        return KMPNext;
    }

    public boolean isLog() {
        return isLog;
    }

    public void setLog(boolean log) {
        isLog = log;
    }
}
