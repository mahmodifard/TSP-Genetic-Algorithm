import GeneticSystem.SolveSamp1;
import TSP.TravelingSalesman;
import com.sun.org.apache.bcel.internal.generic.IADD;

import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: javad
 * Date: 10/17/11
 * Time: 8:13 PM
 */
public class Main {
    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        TravelingSalesman tsp = new TravelingSalesman();
       long l2 = System.currentTimeMillis();

        System.out.println("l2 = " + ((l2 - l1)));



     /*   long l1 = System.currentTimeMillis();
        int countOfGen = 2;
        int[] nucleotideCount = new int[countOfGen];
        nucleotideCount[0] = 14;
        nucleotideCount[1] = 10;
        SolveSamp1 samp1 = new SolveSamp1(100, .5, .02, 2, 24, 1, 0, 2);
        samp1.start(2);
        SolveSamp1.bestInfo b = (SolveSamp1.bestInfo) samp1.getBestOfChromosome();
        System.out.println(b);

        long l2 = System.currentTimeMillis();

        System.out.println("l2 = " + ((l2 - l1)));*/
    }
}
