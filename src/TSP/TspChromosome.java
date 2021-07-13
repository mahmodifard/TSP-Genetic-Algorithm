package TSP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Moori
 * Date: 8/3/11
 * Time: 4:10 PM
 */
public class TspChromosome {
    int[] genes;
    double fitness;

    public TspChromosome(City[] cities) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < cities.length; i++) {
            list.add(i);
        }
        genes = new int[cities.length + 1];
        genes[0] = 0;
        genes[cities.length] = 0;
        list.remove(0);
        for (int i = 1; i < cities.length; i++) {
            genes[i] = list.remove(((int) (Math.random() * list.size())));
        }
        // System.out.println();

    }

    public TspChromosome() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public TspChromosome(int length) {
        genes = new int[length];
    }

    public int getCity(int i) {
        return genes[i];  //To change body of created methods use File | Settings | File Templates.
    }

    public static void sortChromosome(TspChromosome[] chromosomes, int lenght) {
        TspChromosome cTemp;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < lenght - 1; i++) {
                if (chromosomes[i].fitness > chromosomes[i + 1].fitness) {
                    cTemp = chromosomes[i];
                    chromosomes[i] = chromosomes[i + 1];
                    chromosomes[i + 1] = cTemp;
                    swapped = true;
                }


            }
        }
    }
}
