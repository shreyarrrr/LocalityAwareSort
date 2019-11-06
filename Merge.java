
/**
 * General Merge Sort
 *
 * @author roy112
 * @version 10/21/19
 *
 */
public class Merge extends Sort {
    /**
     * this class should not be instantiated
     */
    private static Comparable[] aux;
    private Merge() {}

    /**
     * sort the array
     * @param a - array
     */
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        mergeSort(a, 0, a.length - 1);
    }

    private static void mergeSort(Comparable[] a, int low, int high) {
        if (high <= low) return;
        int mid = low + (high - low)/2;
        mergeSort(a, low, mid);
        mergeSort(a, mid + 1, high);
        inOrder(a, low, mid, high);
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
