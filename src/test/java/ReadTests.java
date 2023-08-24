import com.kanayaya.sort_it.read.OrderChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ReadTests {
    @Test
    public void testCorrectSequenceChecker() {
        List<String> correctLines = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");

        OrderChecker<String> correct = new OrderChecker<String>(correctLines, Comparator.naturalOrder());

        String previous = "";
        for (String line : correct) {
            boolean condition = line.compareTo(previous) > 0;
            Assertions.assertTrue(condition);
            previous = line;
        }
    }

    @Test
    public void testIncorrectSequenceThrows() {
        List<String> incorrectLines = Arrays.asList("a", "b", "c", "d", "a", "b", "g", "h", "a", "j");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            for (String s :
                    new OrderChecker<String>(incorrectLines, Comparator.naturalOrder())) {
            }
        });
    }

    @Test
    public void testIncorrectSequenceFiltering() {
        List<String> incorrectLines = Arrays.asList("a", "b", "c", "d", "a", "b", "g", "h", "a", "j");
        List<String> results = new ArrayList<>();
        for (Iterator<String> iterator = new OrderChecker<String>(incorrectLines, Comparator.naturalOrder()).iterator(); iterator.hasNext(); ) {
            try {
                String s = iterator.next();
                results.add(s);
            } catch (IllegalStateException e) {}
        }
        String previous = "";
        for (String line : results) {
            boolean condition = line.compareTo(previous) > 0;
            Assertions.assertTrue(condition);
            previous = line;
        }
    }

}
