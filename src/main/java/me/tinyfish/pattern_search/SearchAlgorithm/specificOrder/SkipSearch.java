package me.tinyfish.pattern_search.SearchAlgorithm.specificOrder;

import me.tinyfish.pattern_search.SearchAlgorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class SkipSearch implements SearchAlgorithm {
    private static final int ASCII_SIZE = 256;
    
    @Override
    public List<Integer> search(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();

        /* Preprocessing */
        List<List<Integer>> z = preProcessing(pattern);

        /* Searching */
        List<Integer> bucket;
        for(int j = pattern.length - 1; j < text.length ; j += pattern.length) {
            bucket = z.get(text[j]); //bucket for char text[j]
            if (bucket == null) continue; //text[j] not appear in pattern

            for (int pos : bucket) {
                if (cmp(pattern,text,j - pos))
                    result.add(j - pos);
            }
        }

        return result;
    }

    private List<List<Integer>> preProcessing(char[] pattern) {
        List<Integer> bucket;
        List<List<Integer>> z = new ArrayList<>(ASCII_SIZE);
        //fill z size = ASCII_SIZE
        for (int i = 0; i < ASCII_SIZE; ++i) z.add(null);

        for(int i = 0; i < pattern.length; ++i){
            if (z.get(pattern[i]) != null) {
                bucket = z.get(pattern[i]); //get bucket for char pattern[i]
                bucket.add(i);              //push next pos to bucket
                z.set(pattern[i],bucket);   //update bucket
            } else {
                bucket = new ArrayList<>(); //init bucket
                bucket.add(i);
                z.set(pattern[i],bucket);
            }
        }

        return z;
    }
}
