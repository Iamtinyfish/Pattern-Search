package me.tinyfish.pattern_search.SearchAlgorithm.leftToRight;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RabinKarp implements SearchAlgorithm {
    private boolean isLog;

    public RabinKarp() {
    }

    public RabinKarp(boolean isLog) {
        this.isLog = isLog;
    }

    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();
        int numberOfDigitsTextLength = String.valueOf(text.length).length();
        String logHashFormat = "%-" + (numberOfDigitsTextLength * 2 + 9) + "s " + "%-" + pattern.length + "s" + "   Hash = " + "%d";
        int patternHash = hash(pattern);
        //init text hash
        int textHash = hash(Arrays.copyOf(text,pattern.length));

        if (textHash == patternHash) result.add(0);

        if (isLog) {
            System.out.println("Text: " + String.copyValueOf(text));
            //
            System.out.format(logHashFormat, "Pattern: ", String.copyValueOf(pattern), patternHash);
            System.out.println();

            System.out.format(logHashFormat,
                    "Text[0.." + (pattern.length - 1) + "]:",
                    String.copyValueOf(Arrays.copyOf(text, pattern.length)),
                    textHash);
            System.out.println();
        }

        for (int i = 1; i <= text.length - pattern.length; ++i) {
            textHash = reHash(text[i-1], text[i + pattern.length - 1], textHash, pattern.length);
            if (isLog) {
                String subText = String.copyValueOf(Arrays.copyOfRange(text,i,i+pattern.length));
                System.out.format(logHashFormat,
                        "Text[" + i + ".." + (i + pattern.length - 1) + "]:",
                        subText,
                        textHash);
            }
            if (textHash == patternHash) {
                result.add(i);
                if (isLog) System.out.println(" = pattern hash");

            } else {
                if (isLog) System.out.println();
            }
        }
        return result;
    }

    private int hash(char[] chars) {
        int result = 0;
        for (int i = 0; i < chars.length; ++i) {
            result += chars[i] * Math.pow(2,chars.length - i - 1);
        }
        return result;
    }

    private int reHash(char oldStartChar,char newEndChar, int oldHash, int p_len) {
        return (int) ((oldHash - oldStartChar * Math.pow(2,p_len - 1)) * 2 + newEndChar);
    }

    public boolean isLog() {
        return isLog;
    }

    public void setLog(boolean log) {
        isLog = log;
    }
}
