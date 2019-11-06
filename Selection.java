
/**
 * General Selection Sort
 *
 * @author roy112
 * @version 10/21/19
 *
 */
public class Selection extends Sort {
    /**
     * this class should not be instantiated
     */
    private Selection() {}

    /**
     * sort the array
     * @param a - array
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[minIndex])) {
                    minIndex = j;
                }
            }
            exch(a, i, minIndex);
        }
    }
}
