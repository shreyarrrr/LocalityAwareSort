
/**
 * Locality-Aware Selection Sort
 *
 * @author roy112
 * @version 10/17/19
 *
 */
public class LSelection extends Sort {
    /**
     * this class should not be instantiated
     */
    private LSelection() {}

    /**
     * sort the array
     * @param a - array
     * @param d - locality
     */
    public static void sort(Comparable[] a, int d) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            int max = 0;

            for (int j = i + 1; j < Math.min(i + 2 + d, a.length); j++) {
                if (less(a[j], a[minIndex])) {
                    minIndex = j;
                }
            }
            exch(a, i, minIndex);
        }
    }
}
