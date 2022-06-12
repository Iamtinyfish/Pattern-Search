package me.tinyfish.pattern_search.SearchAlgorithm;

import java.util.*;
import java.util.stream.Collectors;

public class FiniteAutomata implements SearchAlgorithm{
    private boolean isLog;

    public FiniteAutomata() {
        isLog = false;
    }

    public FiniteAutomata(boolean isLog) {
        this.isLog = isLog;
    }

    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        if (isLog) {
            System.out.println("Text: " + String.copyValueOf(text));
            System.out.println("Pattern: " + String.copyValueOf(pattern));
        }

        //Preprocess phase
        //get distinct chars in text
        List<Character> distinctChars =  String.copyValueOf(text)
                .chars()
                .distinct()
                .mapToObj(c -> ((char) c))
                .collect(Collectors.toList());
        int[][] F = efficientlyBuildAutomata(pattern, distinctChars);
        if (isLog) printFTable(pattern, F, distinctChars);
//        this.F = buildAutomata();
//        printFTable();

        //Search phase
        List<Integer> result = new ArrayList<>();
        List<Integer> listStateOfText = new ArrayList<>();
        int finalStatesNum = pattern.length;
        int state = 0;
        for (int i = 0; i < text.length; ++i) {
            state = F[state][distinctChars.indexOf(text[i])];
            listStateOfText.add(state);
            if (state == finalStatesNum)
                result.add(i - finalStatesNum + 1); // get start index
        }
        if (isLog) {
            System.out.println("\nSEARCH PHASE:");
            System.out.println(text);
            System.out.println(listStateOfText.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(", ", "")
            );
        }
        return result;
    }

    public int getNextState(char[] pattern, int state, char nextChar) {
        if (state < pattern.length && nextChar == pattern[state])
            return state + 1;

        for (int nextState = state; nextState > 0; --nextState) {
            if (pattern[nextState - 1] == nextChar) {
                int i = 2;
                int j = 1;
                while(i <= nextState) {
                    if (pattern[nextState - i] != pattern[state - j])
                        break;
                    ++i;
                    ++j;
                }
                if (i == nextState + 1) return nextState;
            }
        }

        return 0;
    }

    public int[][] buildAutomata(char[] pattern, List<Character> distinctChars) {
        //COLUMN
        int numOfChars = distinctChars.size();    //number of columns = number of distinct chars in text

        //ROW
        int numOfStates = pattern.length + 1;     //number of rows = number of states = length of pattern + 1

        //F TABLE
        int[][] F = new int[numOfStates][numOfChars];
        for (int state = 0; state < numOfStates; ++state) {
            for (int col = 0; col < numOfChars; ++col) {
                F[state][col] = getNextState(pattern,state, distinctChars.get(col));
            }
        }

        return F;
    }

    public int[][] efficientlyBuildAutomata(char[] pattern, List<Character> distinctChars) {
        //COLUMN
        int numOfChars = distinctChars.size();    //number of columns = number of distinct chars in text

        //ROW
        int numOfStates = pattern.length + 1;     //number of rows = number of states = length of pattern + 1

        //F TABLE
        int[][] F = new int[numOfStates][numOfChars];
        F[0][distinctChars.indexOf(pattern[0])] = 1;   //state 0 -> 1
        int lps = 0;
        for (int state = 1; state < numOfStates; ++state) {
            //copy row lps -> row this state
            System.arraycopy(F[lps], 0, F[state], 0, numOfChars);

            if (state < pattern.length) {   // not final row
                F[state][distinctChars.indexOf(pattern[state])] = state + 1;
                lps = F[lps][distinctChars.indexOf(pattern[state])];
            }
        }
        return F;
    }

    private void printFTable(char[] pattern, int[][] F, List<Character> distinctChars) {

        int numOfStates = pattern.length + 1;
        System.out.println("\nPREPROCESS PHASE: F table");
        System.out.println(distinctChars.);
        for (int i = 0; i < numOfStates; ++i) {
            System.out.print(i + " ");
            System.out.print(Arrays.toString(F[i]));
            if (i < numOfStates - 1) System.out.println(" " + pattern[i]);
        }
        System.out.println();
    }

    public boolean isLog() {
        return isLog;
    }

    public void setLog(boolean log) {
        isLog = log;
    }

    @Override
    public List<Integer> search(String text, String pattern) {
        return search(text.toCharArray(), pattern.toCharArray());
    }
}
