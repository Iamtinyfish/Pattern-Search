package me.tinyfish.pattern_search;

import me.tinyfish.pattern_search.SearchAlgorithm.anyOrder.*;
import me.tinyfish.pattern_search.SearchAlgorithm.leftToRight.MorrisPratt;
import me.tinyfish.pattern_search.SearchAlgorithm.leftToRight.NotSoNaive;
import me.tinyfish.pattern_search.SearchAlgorithm.leftToRight.RabinKarp;
import me.tinyfish.pattern_search.SearchAlgorithm.rightToLeft.BoyerMoore;
import me.tinyfish.pattern_search.SearchAlgorithm.rightToLeft.ZhuKataoka;
import me.tinyfish.pattern_search.SearchAlgorithm.specificOrder.SkipSearch;
import me.tinyfish.pattern_search.Utils.ShowExecutionTime;

public class Main {
    public static void main(String[] args) {
        String text = "gcatcgcagagagtatacagtacg";
        String pattern = "gcagagag";

//        char[] textChars = "AABAACAADAABAABA".toCharArray();
//        char[] patternChars = "AABA".toCharArray();

        PatternSearch searcher = new PatternSearch(new ZhuKataoka());

        System.out.println("Result: " + ShowExecutionTime.execute(text,pattern,searcher::search));
    }
}
