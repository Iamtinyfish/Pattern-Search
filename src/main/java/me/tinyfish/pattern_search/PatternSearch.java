package me.tinyfish.pattern_search;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.List;

public class PatternSearch {
    private SearchAlgorithm algorithm;

    public PatternSearch(SearchAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public List<Integer> search(char[] text,char[] pattern) {
        return algorithm.search(text, pattern);
    }

    public List<Integer> search(String text, String pattern) {
        return algorithm.search(text.toCharArray(), pattern.toCharArray());
    }

    public SearchAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(SearchAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}
