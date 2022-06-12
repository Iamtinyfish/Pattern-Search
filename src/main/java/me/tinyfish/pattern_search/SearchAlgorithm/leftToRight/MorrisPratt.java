package me.tinyfish.pattern_search.SearchAlgorithm.leftToRight;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class MorrisPratt implements SearchAlgorithm {

    private boolean isLog;

    public MorrisPratt() {
        isLog = false;
    }

    public MorrisPratt(boolean isLog) {
        this.isLog = isLog;
    }

    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();
        int[] MPNext = preMP(pattern);
        int i = 0;
        int j = 0;
        while (j < text.length) {
            while (i > -1 && text[j] != pattern[i])
                i = MPNext[i];
            i++;j++;
            if (i == pattern.length) {
                result.add(j-i);
                i = MPNext[i];
            }
        }
        return result;
    }

    private int[] preMP(char[] pattern) {
        int[] MPNext = new int[pattern.length + 1];
        int i = 0;
        int j = MPNext[0] = -1;
        while (i < pattern.length) {
            while (j > -1 && pattern[i] != pattern[j]) {
//                System.out.println("j = " + j + ", mpnext[j] = " + MPNext[j]);
                j = MPNext[j];
            }
            MPNext[++i] = ++j;
        }
        return MPNext;
    }

    public boolean isLog() {
        return isLog;
    }

    public void setLog(boolean log) {
        isLog = log;
    }
}
