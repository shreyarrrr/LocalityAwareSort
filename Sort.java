import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Provides library functions for sorting, and
 * contains the main method to drive all the code using CLI arguments
 * 
 * 
 * @version 2019-10-10
 */

public class Sort {
	
	private static enum Algorithm{Selection, Merge, Heap, Quick};
	
	/**
	 * 
	 * @param v
	 * @param w
	 * @return true if v is less than w
	 */
	public static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }
	
	/**
	 * 
	 * @param v
	 * @param w
	 * @return true if v is equal to w
	 */
	public static boolean equal(Comparable v, Comparable w) {
        return (v.compareTo(w) == 0);
    }
	
	/**
	 * 
	 * @param a
	 * @return true if array a is in sorted order
	 */
	public static boolean isSorted(Comparable[] a) {
		for (int i = 0; i < a.length-1; i++) {
			if (less(a[i+1],a[i]))
				return false;
		}
		return true;
	}
	
	/**
	 * print array to standard output<br>
	 * identify out of order elements
	 * @param a
	 */
	public static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
        	if (i > 0 && less(a[i],a[i-1]))
        		StdOut.println(a[i] + " <-- X");
        	else if (i < a.length-1 && less(a[i+1],a[i]))
        		StdOut.println(a[i] + " <-- X");
        	else
        		StdOut.println(a[i]);
        }
    }
	
	/**
	 * swap a[i] with a[j]
	 * @param A
	 * @param i
	 * @param j
	 */
	public static void exch(Object[] a, int i, int j) {
		Object tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	public static void main(String[] args) {
		// parse CLI arguments
		Algorithm alg = Algorithm.Selection;
		int d = -1;
		for (int i = 0; i < args.length; i++) {
			switch(args[i]) {
			case "-h" :
			case "--help" :
				StdOut.println("usage: Sort [options] [< data]");
				StdOut.println("-h,--help\tprint help and exit");
				StdOut.println("-l <locality>\tuse locality-aware sorting");
				StdOut.println("-a <algorithm>\tuse specified algorithm (default = selection)");
				StdOut.println("-f <file>\tread data from file (default = StdIn)");
				return;
			case "-l" :
				try {
					d = Integer.parseInt(args[++i]);
				} catch (NumberFormatException e) {
					StdOut.println("ERROR: locality ("+args[i]+") must be an integer.");
					return;
				}
				if (d < 0) {
					StdOut.println("ERROR: locality ("+d+") must be non-negative.");
					return;
				}
				break;
			case "-a" :
				switch(args[++i].toLowerCase()) {
				case "selection" :
				case "s" :
					alg = Algorithm.Selection;
					break;
				case "merge" :
				case "m" :
					alg = Algorithm.Merge;
					break;
				case "heap" :
				case "h" :
					alg = Algorithm.Heap;
					break;
				case "quick":
				case "q":
					alg = Algorithm.Quick;
					break;
				default :
					StdOut.println("ERROR: unknown algorithm ("+args[i]+").");
					return;
				}
				break;
			case "-f" :
				String file = args[++i];
				FileInputStream in = null;
				try {
					in = new FileInputStream(new File(file));
				} catch (FileNotFoundException e) {
					StdOut.println("ERROR: file ("+file+") not found.");
					return;
				}
				System.setIn(in);
				break;
			default :
				StdOut.println("ERROR: unknown option ("+args[i]+")");
				return;
			}
		}
		
		// read all data
		Comparable[] data = null;
		int[] int_data = StdIn.readAllInts();
		data = new Comparable[int_data.length];
		for (int i = 0; i < int_data.length; i++)
			data[i] = int_data[i];
		
		// run sort on data
		long t1 = System.nanoTime();
		if (d >= 0) {
			// use locality-aware algorithms
			switch(alg) {
			default:
			case Selection :
				LSelection.sort((Comparable[])data, d);
				break;
			case Merge :
				LMerge.sort((Comparable[])data, d);
				break;
			case Heap :
				LHeap.sort((Comparable[])data, d);
				break;
			}
		} else {
			// use general algorithms
			switch(alg) {
			default:
			case Selection :
				Selection.sort((Comparable[])data);
				break;
			case Merge :
				Merge.sort((Comparable[])data);
				break;
			case Heap :
				Heap.sort((Comparable[])data);
				break;
			}
		}
		long t2 = System.nanoTime();
		double millis = (t2-t1)/1000000.0;
		
		if (!Sort.isSorted((Comparable[])data)) {
			StdOut.print("Sort failed.");
			//Sort.show((Comparable[])data);
		} else {
			StdOut.printf("%.4f",millis);
		}
		
		System.setIn(System.in);
	}
}
