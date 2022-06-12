import me.tinyfish.pattern_search.PatternSearch;
import me.tinyfish.pattern_search.SearchAlgorithm.leftToRight.MorrisPratt;
import me.tinyfish.pattern_search.Utils.ShowExecutionTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Pattern search test")
public class PatternSearchStringInputTest {
    private final PatternSearch searcher = new PatternSearch(new MorrisPratt());

    @Test
    @DisplayName("Test case 1:")
    void testCase1() {
        //input
        String text = "gcatcgcagagagtatacagtacg";
        String pattern = "gcagagag";

        String expected = "[5]";
        String actual = ShowExecutionTime.execute(text,pattern,searcher::search).toString();

        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test case 2:")
    void testCase2() {
        //input
        String text = "hieuabcxyzhieu";
        String pattern = "hieu";

        String expected = "[0, 10]";
        String actual = ShowExecutionTime.execute(text,pattern,searcher::search).toString();

        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test case 3:")
    void testCase3() {
        //input
        String text = "mnpdahabcdabcdjrabcdkjsda";
        String pattern = "abcd";

        String expected = "[6, 10, 16]";
        String actual = ShowExecutionTime.execute(text,pattern,searcher::search).toString();

        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test case 4:")
    void testCase4() {
        //input
        String text = "kljfkdgabcfpwrbcdiewrsfsf";
        String pattern = "abcd";

        String expected = "[]";
        String actual = ShowExecutionTime.execute(text,pattern,searcher::search).toString();

        Assertions.assertEquals(expected,actual);
    }
}
