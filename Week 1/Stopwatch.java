/**
 * A utility class to measure the running time (wall clock) of a program.
 * @author Sedgewick
 * @author Wayne
 * @version 1.0
 */
public class Stopwatch { 

    private final long start;

    /**
     * Initializes a new stopwatch.
     */
    public Stopwatch() {
        start = System.currentTimeMillis();
    } 

    /**
     * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
     *
     * @return elapsed CPU time (in seconds) since the stopwatch was created
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    public static void main(String[] args)
    {
       int[] a = {23, 32, 5, 103, 12, 10, 45, 3, 4, 5, 23, 23, 32, 5, 103, 52, 134, 45, 3, 4, 45, 234, 12, 10, 45};
       Stopwatch stopwatch = new Stopwatch();
       //System.out.println(ThreeSum.count(a));
       double time = stopwatch.elapsedTime();
       System.out.println("elapsed time " + time);
    }
} 
