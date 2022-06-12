package me.tinyfish.pattern_search.Utils;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.BiFunction;

public class ShowExecutionTime {
    public static List<Integer> execute(String text, String pattern, BiFunction<String,String, List<Integer>> function) {
        LocalTime start = LocalTime.now();
        List<Integer> result = function.apply(text,pattern);
        LocalTime end = LocalTime.now();
        System.out.println("\nExecute Time: " + start.until(end, ChronoUnit.NANOS) + " ns\n");
        return result;
    }

    public static List<Integer> execute(char[] text, char[] pattern, BiFunction<char[],char[],List<Integer>> function) {
        LocalTime start = LocalTime.now();
        List<Integer> result = function.apply(text,pattern);
        LocalTime end = LocalTime.now();
        System.out.println("\nExecute Time: " + start.until(end, ChronoUnit.NANOS) + " ns\n");
        return result;
    }
}
