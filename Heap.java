
/**
 * General Heap Sort
 *
 * @author roy112
 * @version 10/17/19
 *
 */
public class Heap extends Sort {
    /**
     * this class should not be instantiated
     */
    private Heap() {}

    /**
     * sort the array
     * @param a - array
     */

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(a, n, i);
        for (int j=0; j < n; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("\n");
        for (int i=n-1; i>=0; i--) {
            exch(a, 0, i);
            for (int j=0; j < n; j++) {
                System.out.print(a[j] + " ");
            }
            System.out.println(",");
            heapify(a, i, 0);
            for (int j=0; j < n; j++) {
                System.out.print(a[j] + " ");
            }
            System.out.println();
        }
    }
    private static void heapify(Comparable arr[], int n, int i)
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
    public static void main(String []args) {
        Comparable [] a = {8, 6, 9, 3, 2, 1};
        int d = 3;
        sort(a);
    }
}
