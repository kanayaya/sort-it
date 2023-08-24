import com.kanayaya.sort_it.read.OrderChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ReadTests {
    @Test
    public void testSequenceChecker() {
        List<String> correctLines = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
        String[] incorrectLines = new String[] {
                "a", "b", "c", "d", "a", "b", "g", "h", "a", "j"
        };

        OrderChecker<String> correct = new OrderChecker<String>(correctLines, Comparator.naturalOrder());

        String previous = "";
        for (String line : correct) {
            boolean condition = line.compareTo(previous) > 0;
            Assertions.assertTrue(condition);
            previous = line;
        }


    }
}
