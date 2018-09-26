/**
 * Implements various sorting algorithms.
 * 
 * @author (your name), Acuna, Sedgewick
 * @verison (version)
 */

public class PierceSorting {
     
    /**
     * Entry point for sample output.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Q1
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        //Integer[] a = {1, 3, 6, 7, 8, 9, 5, 4, 2};
        quicksortmid(a);
        //assert isSorted(a); //requires assertions enabled.
        show(a);
        
        //Q2
        String[] b = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        //Integer[] b = {1, 2, 6, 7, 9, 5, 4, 8, 3, 0, 10};
        mergesort(b);
        //assert isSorted(b);
        show(b);
    }

    public static void quicksortmid(Comparable[] a) {
        quicksortmid(a, 0, a.length - 1);
    }

    private static void quicksortmid(Comparable[] a, int min, int max) {
        //TODO: implement this.
        if (min < max) {
            // create partitions
            int partIndex = partitionMOT(a, min, max);

            // sort left partition
            quicksortmid(a, min, partIndex - 1);

            // sort the right partition
            quicksortmid(a, partIndex + 1, max);
        }
    }

    private static int partitionMOT(Comparable[] data, int min, int max) {
        Comparable partitionEl;
        int left, right;
        int middle = (min + max)/2;

        partitionEl = data[middle];
        swap(data, middle, min);
        left = min;
        right = max;

        while (left < right) {
            while (left < right && data[left].compareTo(partitionEl) <= 0) {
                left++;
            }

            while (data[right].compareTo(partitionEl) > 0) {
                right--;
            }

            if (left < right) {
                swap(data, left, right);
            }
        }

        swap(data, min, right);

        return right;
    }

    private static void swap(Comparable[] data, int index1, int index2) {
        Comparable temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }
    
    public static void mergesort(Comparable[] a) {
        //TODO: implement this.
        Comparable[] sortedA = mergesortRec(a);
        for (int i=0; i<a.length; i++) {
            a[i] = sortedA[i];
        }
    }

    public static Comparable[] mergesortRec(Comparable[] a) {
        if (a.length == 2) {
            if (a[0].compareTo(a[1]) > 0) {
                Comparable temp = a[0];
                a[0] = a[1];
                a[1] = temp;
            }

            return a;
        } else if (a.length == 1) {
            return a;
        }

        int min = 0;
        int max = a.length - 1;
        int mid = a.length / 2;


        int aStart = min;
        int aEnd = mid;
        int bStart = mid;
        int bEnd = max+1;
        int aSize = aEnd - aStart;
        int bSize = bEnd - bStart;
        Comparable half1[] = new Comparable[aSize];
        Comparable half2[] = new Comparable[bSize];
        int index1 = aStart;
        int index2 = bStart;

        while (index1 < aEnd) {
            half1[index1] = a[index1];
            index1++;
        }

        index1 = 0;

        while (index2 < bEnd) {
            half2[index1] = a[index2];
            index1++;
            index2++;
        }

        if (min < max) {
            half1 = mergesortRec(half1);
            half2 = mergesortRec(half2);
            a = merge(half1, half2);
        }

        return a;
    }

    public static Comparable[] merge(Comparable[] a, Comparable[] b) {
        int size = a.length + b.length;
        Comparable[] temp = new Comparable[size];

        int aFirst = 0, aLast = a.length - 1;
        int bFirst = 0, bLast = b.length - 1;
        int index = aFirst;

        while (aFirst <= aLast && bFirst <= bLast) {
            if (a[aFirst].compareTo(b[bFirst]) < 0) {
                temp[index] = a[aFirst];
                aFirst++;
            } else {
                temp[index] = b[bFirst];
                bFirst++;
            }
            index++;
        }

        while (aFirst <= aLast) {
            temp[index] = a[aFirst];
            aFirst++;
            index++;
        }

        while (bFirst <= bLast) {
            temp[index] = b[bFirst];
            bFirst++;
            index++;
        }

        return temp;
    }

    /**
     * Displays contents of array, space separated.
     * @author Sedgewick
     * @param a Array to display.
     */
    private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }
    
    /**
     * Checks if array is in sorted order.
     * @author Sedgewick
     * @param a Array to be checked.
     * @return Returns true if array is sorted.
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
    
    //See previous method.
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}