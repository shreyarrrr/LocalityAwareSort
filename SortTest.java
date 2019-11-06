/**
 *
 * These test cases randomly generate arrays with a set locality then allow testing of
 * locality-dependant sorting algorithms.
 *
 * Note that locality-dependent sorting algorithms must sort any array with locality less than
 * or equal to d, and must also not correctly sort any array with locality greater than d.
 *
 * @version 10/17/2019
 */

import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;
import java.util.Random;

public class SortTest {

    private static Random random;
    private final int ARRAY_LEN = 1000;
    private final int ARRAY_LOCALITY = 40;
    private Integer[] arr = new Integer[ARRAY_LEN];
    private int d;

    @Rule
    public Timeout globalTimeout = Timeout.seconds(4);

    @Before
    public void before() {
        long seed = System.currentTimeMillis();
        random = new Random(seed);

        for (int i = 0; i < ARRAY_LEN; i++) {
            arr[i] = i;
        }
    }

    @Test
    public void testLSelection1000Times() {
        boolean testsPassed = true;
        this.d = 40;

        for (int i = 0; i < 1000; i++) {
            testsPassed &= testLSelection();
        }

        assertTrue("LSelection did not correctly sort an array when it should have", testsPassed);

        testsPassed = true;
        this.d = 20;

        for (int i = 0; i < 1000; i++) {
            testsPassed &= testLSelection();
        }

        assertFalse("LSelection correctly sorted an array when it should not have", testsPassed);
    }

    @Test
    public void testLMerge1000Times() {
        boolean testsPassed = true;
        this.d = 40;

        for (int i = 0; i < 1000; i++) {
            testsPassed &= testLMerge();
        }

        assertTrue("LMerge did not correctly sort an array when it should have", testsPassed);

        testsPassed = true;
        this.d = 20;

        for (int i = 0; i < 1000; i++) {
            testsPassed &= testLMerge();
        }

        assertFalse("LMerge correctly sorted an array when it should not have", testsPassed);
    }

    @Test
    public void testLHeap1000Times() {
        boolean testsPassed = true;
        this.d = 40;

        for (int i = 0; i < 1000; i++) {
            testsPassed &= testLHeap();
        }

        assertTrue("LHeap did not correctly sort an array when it should have", testsPassed);

        testsPassed = true;
        this.d = 20;

        for (int i = 0; i < 1000; i++) {
            testsPassed &= testLHeap();
        }

        assertFalse("LHeap correctly sorted an array when it should not have", testsPassed);
    }

    private boolean testLSelection() {
        shuffleLocally(arr);
        LSelection.sort(arr, d);
        return Sort.isSorted(arr);
    }

    private boolean testLMerge() {
        shuffleLocally(arr);
        LMerge.sort(arr, d);
        return Sort.isSorted(arr);
    }

    private boolean testLHeap() {
        shuffleLocally(arr);
        LHeap.sort(arr, d);
        return Sort.isSorted(arr);
    }

    private void shuffleLocally(Integer[] arr) {
        assert (arr.length % ARRAY_LOCALITY == 0);
        for (int i = 0; i < ARRAY_LEN; i += ARRAY_LOCALITY) {
            shuffle(arr, i, i + ARRAY_LOCALITY);
        }
    }

    private static int uniform(int n) {
        if (n <= 0) throw new IllegalArgumentException("argument must be positive: " + n);
        return random.nextInt(n);
    }

    private static void shuffle(Integer[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            int r = i + uniform(hi-i);     // between i and hi-1
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
}