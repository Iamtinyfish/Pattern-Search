import me.tinyfish.pattern_search.PatternSearch;
import me.tinyfish.pattern_search.SearchAlgorithm.leftToRight.FiniteAutomata;
import me.tinyfish.pattern_search.Utils.ShowExecutionTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Pattern search test")
public class PatternSearchCharArrayInputTest {

    private final PatternSearch searcher = new PatternSearch(new FiniteAutomata());

    @Test
    @DisplayName("Test case 1:")
    void testCase1() {
        //input
        char[] text = "gcatcgcagagagtatacagtacg".toCharArray();
        char[] pattern = "gcagagag".toCharArray();

        String expected = "[5]";
        String actual = ShowExecutionTime.execute(text,pattern,searcher::search).toString();

        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test case 2:")
    void testCase2() {
        //input
        char[] text = "hieuabcxyzhieu".toCharArray();
        char[] pattern = "hieu".toCharArray();

        String expected = "[0, 10]";
        String actual = ShowExecutionTime.execute(text,pattern,searcher::search).toString();

        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test case 3:")
    void testCase3() {
        //input
        char[] text = "mnpdahabcdabcdjrabcdkjsda".toCharArray();
        char[] pattern = "abcd".toCharArray();

        String expected = "[6, 10, 16]";
        String actual = ShowExecutionTime.execute(text,pattern,searcher::search).toString();

        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test case 4:")
    void testCase4() {
        //input
        char[] text = "kljfkdgabcfpwrbcdiewrsfsf".toCharArray();
        char[] pattern = "abcd".toCharArray();

        String expected = "[]";
        String actual = ShowExecutionTime.execute(text,pattern,searcher::search).toString();

        Assertions.assertEquals(expected,actual);
    }
}
