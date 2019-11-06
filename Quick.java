
/**
 * General Quick Sort
 *
 * @author roy112
 * @version 10/21/19
 *
 */
public class Quick extends Sort {
    /**
     * this class should not be instantiated
     */
    private Quick() {}

    /**
     * sort the array
     * @param a - array
     */
    public static void sort(Comparable[] a) {
        //StdRandom.shuffle(a);
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(Comparable[] a, int low, int high) {
        if (high <= low)
            return;
        int j = partition(a, low, high);
        quickSort(a, low, j - 1);
        quickSort(a, j + 1, high);
    }
    private static int partition(Comparable[] a, int low, int high) {
        int i = low;
        int j = high + 1;
        Comparable v = a[low];
        while(true) {
            while (less(a[++i], v)) {
                if (i == high)
                    break;
            }
            while (less(v, a[--j])) {
                if (j == low)
                    break;
            }
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, low, j);
        return j;
    }
}