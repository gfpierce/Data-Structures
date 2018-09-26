import java.util.Random;
import java.util.Arrays;

public class PierceNonUniform {
    public static Integer[] randomizeArray(Integer[] array) {
        Random rgen = new Random();

        for(int i=0; i<array.length; i++) {
            int position = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[position];
            array[position] = temp;
        }

        return array;
    }

    public static Integer[] halfOnes(int size) {
        int splitSize = size/2;
        Integer[] onesArray = new Integer[size];
        for (int i=0; i<splitSize; i++) {
            onesArray[i] = 0;
        }
        for (int j=splitSize; j<size; j++) {
            onesArray[j] = 1;
        }

        onesArray = randomizeArray(onesArray);
        return onesArray;
    }

    public static Integer[] remainders(int size) {
        Integer[] remaindersArray = new Integer[size];
        int splitSize = size/2;
        int currentNumber = 0;
        int position = 0;

        while (splitSize !=0) {
            for (int j=0; j<splitSize; j++) {
                if (position>size) {
                    remaindersArray = randomizeArray(remaindersArray);
                    return remaindersArray;
                }
                else {
                    remaindersArray[position] = currentNumber;
                    position++;
                }
            }
            splitSize = splitSize/2;
            currentNumber++;
        }

        if (splitSize == 0) {
            remaindersArray[position] = currentNumber;
        }

        remaindersArray = randomizeArray(remaindersArray);
        return remaindersArray;
    }

    public static Integer[] randoms(int size) {
        Integer[] randomsArray = new Integer[size];
        int splitSize = size/2;
        Random rgen = new Random();

        for (int i=0; i<splitSize; i++) {
            randomsArray[i] = 0;
        }
        for (int j=splitSize; j<size; j++) {
            randomsArray[j] = rgen.nextInt();
        }

        randomsArray = randomizeArray(randomsArray);
        return randomsArray;
    }

    public static void main(String[] args) {
        Integer[] a = halfOnes(4096);
        Stopwatch stopwatch = new Stopwatch();
        InsertionSort.sort(a);
        double time = stopwatch.elapsedTime();
        System.out.println("Elapsed Time: " + time);
    }

    /*
        For selection sort, I think the half and half array will be O(n^2), the remainders array will be O(n^2),
        and the randoms array will also be O(n^2). This is because the performance of selection sort is consistently
        at O(n^2), and its performance gets worse with larger lists.

        For insertion sort, I think the half and half array will be O(n), the remainders array will be O(n^2), and the
        randoms array will be O(n^2). The worst case for insertion sort is O(n^2), and I think the remainders and
        randoms array will be there because it requires more comparisons to sort the array, as the potential for
        difference in numbers is much higher than the half and half array. The half and half array is O(n) because
        it will only ever have to compare between 0 and 1.

        Selection Sort:
        Elapsed Time for half/half at 2048 elements: 0.013
        Elapsed Time for half/half at 4096 elements: 0.029
        Doubling Formula Power: 1.15754

        Elapsed Time for remainders at 2048 elements: 0.014
        Elapsed Time for remainders at 4096 elements: 0.032
        Doubling Formula Power: 1.19265

        Elapsed Time for randoms at 2048 elements: 0.016
        Elapsed Time for randoms at 4096 elements: 0.041
        Doubling Formula Power: 1.35755

        Insertion Sort:
        Elapsed Time for half/half at 2048 elements: 0.021
        Elapsed Time for half/half at 4096 elements: 0.035
        Doubling Formula Power: 0.73696

        Elapsed Time for remainders at 2048 elements: 0.026
        Elapsed Time for remainders at 4096 elements: 0.032
        Doubling Formula Power: 0.29956

        Elapsed Time for randoms at 2048 elements: 0.028
        Elapsed Time for randoms at 4096 elements: 0.035
        Doubling Formula Power: 0.321928
     */
}