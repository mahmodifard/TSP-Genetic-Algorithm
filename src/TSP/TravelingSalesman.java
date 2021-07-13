package TSP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Created by IntelliJ IDEA.
 * User: Moori
 * Date: 7/30/11
 * Time: 4:15 PM
 */
public class TravelingSalesman extends JFrame implements Runnable {

    public boolean started;

    public static final int CITY_COUNT = 20;

    public City[] cities;

    public Integer[] numofCity;

    public TspChromosome[] chromosomes;

    public Map map = null;

    public JLabel status;

    private static final int POPULATION_SIZE = 1000;

    private static final int GENERATION_SIZE = 311;

    private static final int PERCENT_CROSSOVER = POPULATION_SIZE / 2;

    Thread worker = null;

    JButton b;

    public TravelingSalesman() throws HeadlessException {
        this.setSize(1500, 800);
        this.setVisible(true);
        if (map == null) {
            map = new Map(this);
            getContentPane().add(map, "Center");
            status = new JLabel("Starting UP");
            getContentPane().add(status, "South");
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b = new JButton("Restart");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        getContentPane().add(b, "North");
        start();

    }

    private void start() {
        cities = new City[CITY_COUNT];
        for (int i = 0; i < CITY_COUNT; i++) {

            cities[i] = new City(
                    (int) (Math.random() * (getBounds().width - 200)) + 100,
                    (int) (Math.random() * (getBounds().height - 200)) + 100);

        }

        started = true;
        chromosomes = new TspChromosome[POPULATION_SIZE];

        for (int i = 0; i < POPULATION_SIZE; i++) {
            chromosomes[i] = new TspChromosome(cities);

        }
        evaluateFitness();
        TspChromosome.sortChromosome(chromosomes, POPULATION_SIZE);
        best = chromosomes[0];
        status.setText(String.valueOf(best.fitness));
        map.update(getGraphics());

        if (worker != null)
            worker = null;
        worker = new Thread(this);

        worker.start();
    }

    double max = 0;
    double min = 10000000;
    double zigma = 0;

    void evaluateFitness() {
        max = 0;
        min = 10000000;
        zigma = 0;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            double sum = 0;
            for (int j = 0; j < cities.length; j++) {
                sum += cities[chromosomes[i].getCity(j)].proximity(cities[chromosomes[i].getCity(j + 1)]);
            }
            zigma += sum;
            if (max < sum) max = sum;
            if (min > sum) min = sum;
            chromosomes[i].fitness = sum;
        }
        /*  for (int i = 0; i < POPULATION_SIZE; i++) {
            chromosomes[i].fitness = 1-(chromosomes[i].fitness - min)/(max-min);

        }*/
        // return;
    }

    public void run() {
        for (int i = 0; i < GENERATION_SIZE; i++) {
            CrossOver();
        }
        map.update(getGraphics());

    }

    public TspChromosome mate(TspChromosome father, TspChromosome mother) {

        TspChromosome child = new TspChromosome(mother.genes.length);
        boolean[] bFather = new boolean[cities.length + 1];
        boolean[] bMother = new boolean[cities.length + 1];
        ArrayList<Integer> mamaList = new ArrayList<Integer>();
        ArrayList<Integer> papaList = new ArrayList<Integer>();

        int cutLengh = (int) (Math.random() * (mother.genes.length - 2));
        if ((cutLengh == 0) || (cutLengh == 1)) cutLengh = 2;
        for (int i = 0; i < mother.genes.length; i++) {
            mamaList.add(mother.getCity(i));
            papaList.add(father.getCity(i));

        }
        for (int i = 1; i < cutLengh; i++) {
            child.genes[i] = mother.getCity(i);
            bMother[mother.getCity(i)] = true;
        }
        for (int i = cutLengh; i <= (cities.length); i++) {
            if (bMother[father.getCity(i)]) {

                int index;//= mother.genes[(list.indexOf(father.genes[i]))];
                index = mamaList.indexOf(father.getCity(i));
                while (bMother[father.getCity(index)]) {
                    index = mamaList.indexOf(father.getCity(index));
                }

                child.genes[i] = father.genes[index];
                bFather[father.getCity(index)] = true;
                //   bMother[father.genes[index]] = true;
            } else
                child.genes[i] = father.getCity(i);
        }

        return child;

    }

    ArrayList<Integer> list;

    void selection() {
        list = new ArrayList<Integer>();
        int counter = 0;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            double temp = (chromosomes[i].fitness);
            //temp = Math.ceil((temp*temp*temp)*100) ;
            double t2 = temp;
            double makh = ((temp - min) / (max - min));
            temp = 1 - Math.abs(makh);
            for (int j = 0; j < temp; j++) {
                list.add(i);
                counter++;
            }
        }

    }

    public void CrossOver() {


        for (int i = 0; i < (.6 * POPULATION_SIZE); i++) {

            int index = (int) (Math.random() * POPULATION_SIZE-1);
            int index2 = (int) (Math.random() * POPULATION_SIZE);
            //      selection();


            TspChromosome cMother = chromosomes[i];
            TspChromosome cFather = chromosomes[index2];
            TspChromosome child = mate(cFather, cMother);
            chromosomes[index+1] = child;

        }
        evaluateFitness();
        TspChromosome.sortChromosome(chromosomes, POPULATION_SIZE);
        if (chromosomes[0].fitness < best.fitness)
            best = chromosomes[0];


    }

    TspChromosome best = null;

}
