/**
 * DoubleOrderedList testing area.
 * 
 * @author (your name), Acuna
 * @version (version)
 */
public class Driver {
    public static void main(String [] args) {
        DoubleOrderedList<Integer> list = new DoubleOrderedList<>();
        
        //RA: These are _extremely_ simple tests - do not use them when doing
        //    your writeup.
        
        list.add(23);
        list.add(24);
        list.add(16);
        list.add(3);
        list.add(7);
        list.add(17);
        list.add(9);
        list.add(13);
        list.add(14);
        list.add(1);

        System.out.println(list);
        
        list.remove(7);
        list.removeFirst();
        list.remove(17);
        list.removeLast();
        list.remove(14);
        list.removeLast();
        
        System.out.println(list);
        
        /* Test Results:
            1 3 7 9 13 14 16 17 23 24 
            3 9 13 16 
        */

        DoubleOrderedList <Integer> list2 = new DoubleOrderedList<>();

        list2.add(3);
        list2.add(1);
        System.out.println(list2);
        list2.add(4);
        list2.removeFirst();
        System.out.println(list2);
        list2.add(20);
        list2.add(33);
        System.out.println(list2);
        list2.remove(20);
        System.out.println(list2);
    }
}
