
/**
 * Locality-Aware Merge Sort
 *
 * @author roy112
 * @version 10/17/19
 *
 */
public class LMerge extends Sort {
    /**
     * this class should not be instantiated
     */
    private static Comparable[] aux;
    private LMerge() {}

    /**
     * sort the array
     * @param a - array
     * @param d - locality
     */
    public static void sort(Comparable[] a, int d) {
        aux = new Comparable[a.length];
        mergeSort(a, 0, a.length - 1, d);
    }

    private static void mergeSort(Comparable[] a, int low, int high, int d) {
        if (high <= low) return;
        int mid = low + (high - low)/2;
        mergeSort(a, low, mid, d);
        mergeSort(a, mid + 1, high, d);
        int newLow = 0;
        if (low < mid - d)
            newLow = mid - d + 1;
        else
            newLow = low;
        int newHigh = 0;
        if (high > mid + d)
            newHigh = mid + d;
        else
            newHigh = high;
        inOrder(a, newLow, mid, newHigh);
    }

    private static void inOrder(Comparable[] a, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++)
            aux[k] = a[k];

        for (int k = low; k <= high; k++)
            if (i > mid)
                a[k] = aux[j++];
            else if (j > high)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
    }
}
