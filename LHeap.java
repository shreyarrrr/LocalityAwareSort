
/**
 * Locality-Aware Heap Sort
 *
 * @author roy112
 * @version 10/21/19
 *
 */
public class LHeap extends Sort {
    /**
     * this class should not be instantiated
     */
    private LHeap() {}

    /**
     * sort the array
     * @param a - array
     * @param d - locality
     */
    public static void sort(Comparable[] a, int d) {
        int n = a.length;

        for (int i = 0; i < n / 2; i++) {
            exch(a, i, n - 1 - i);
        }
        for (int count = 0; count < n; count++) {
            int max = 0;
            if (count < n - d)
                max = d + 1;
            else
                max = n - count;
            Comparable [] temp = new Comparable[max];
            for (int i = count; i < count + max; i++)
                temp[i - count] = a[i];

            for (int i = (d + 1) / 2 - 1; i >= 0; i--)
                heapify(temp, max, i);

            for (int i = count; i < count + max; i++)
                a[i] = temp[i - count];
        }
        for (int i = 0; i < n / 2; i++) {
                exch(a, i, n - 1 - i);
        }
    }
    static void heapify(Comparable arr[], int n, int i)
    {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < n && less(arr[largest],arr[l]))
            largest = l;

        if (r < n && less(arr[largest],arr[r]))
            largest = r;

        if (largest != i) {
            exch(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
}
