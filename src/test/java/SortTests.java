import com.kanayaya.sort_it.sort.Dispencer;
import com.kanayaya.sort_it.sort.Sequencer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Random;

public class SortTests {
    private final Random random = new Random();
    private final Dispencer<String> ndispencer = new Dispencer<String>(Comparator.naturalOrder());
    private final Dispencer<String> rdispencer = new Dispencer<String>(Comparator.reverseOrder());
    @Test
    public void testDispencer() {

        for (int i = 0; i < 100; i++) {
            String first = getRandomString();
            String second = getRandomString();

            Assertions.assertSame(ndispencer.apply(first, second), max(first, second));
            Assertions.assertSame(rdispencer.apply(first, second), min(first, second));
        }
    }

    private String getRandomString() {
        byte[] sbytes = new byte[8];
        random.nextBytes(sbytes);
        return new String(sbytes);
    }

    @Test
    public void testSequencer() {

        String initial = getRandomString();
        Sequencer<String> nseq = new Sequencer<>(ndispencer, initial);
        Sequencer<String> rseq = new Sequencer<>(rdispencer, initial);

        String min = initial;
        String max = initial;

        for (int i = 0; i < 100; i++) {
            String s = getRandomString();

            Assertions.assertSame(nseq.apply(s), min(max, s));
            max = max(max, s);

            Assertions.assertSame(rseq.apply(s), max(min, s));
            min = min(min, s);
        }
    }

    private static <T extends Comparable<T>> T min(T first, T second) {
        return nullCheck(first, second, first.compareTo(second) < 0);
    }
    private static <T extends Comparable<T>> T max(T first, T second) {
        return nullCheck(first, second, first.compareTo(second) > 0);
    }
    private static <T extends Comparable<T>> T nullCheck(T first, T second, boolean condition) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        return condition ? first : second;
    }
}
