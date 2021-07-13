package GeneticSystem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Javad Mahmodifard
 * Date: 7/28/11
 * Time: 11:04 PM
 */
public class SolveSamp1 extends ProblemSolve implements Serializable {


    public SolveSamp1(int countofPopulation, double creossPercend, double mutationPercend, int countofGen, int countOfNucleotide, int countofExon,
                      int sRange, int eRange) {
        super(countofPopulation, creossPercend, mutationPercend, countofGen, countOfNucleotide, countofExon);
        this.sRange = sRange;
        this.eRange = eRange;
        for (int i = 0; i < _countofPopulation; i++) {
            Chromosome ch = new Chromosome(_countofGen, _countofExon, _countofNucleotide);
            ch.createGens(_countofExon, countOfNucleotide, sRange, eRange);
            for (int j = 0; j < _countofGen; j++) {
                ch._gGens.get(j).whatEpistasis();

            }
            evaluateFitness(ch);
            this._population.add(ch);
        }
        findMinimumFitness();
        findMaximumFitness();
        normalizeAndScalingFitness();
        whoisBest();

        calculatingAverage();

        _lastGeneration = _population;
    }

    public class bestInfo {
        public double fittness;
        public double value;
        public double result;

        @Override
        public String toString() {
            String str = "MaxFit = " + String.valueOf(fittness) + "\n" +
                    "Values = " + String.valueOf(value) + "\n" +
                    "Result = " + String.valueOf(result);
            return str;
        }
    }

    @Override
    public Object getBestOfChromosome() {
        bestInfo b = new bestInfo();
        b.fittness = _goodAnswer.get_fitness();
        b.value = _goodAnswer.value;
        b.result = _goodAnswer.result;
        return b;
    }

    @Override
    protected void evaluateFitness(Chromosome chromosome) {
        int size = _countofNucleotide;
        String str1 = "";
        String str2 = "";
        for (int i = 0; i < size; i++) {
            str1 += chromosome._gGens.get(0)._epistasis._nucleotides.get(i).get_iValue();
            //    str2 += chromosome._gGens.get(1)._epistasis._nucleotides.get(i).get_iValue();
        }
        int i = Integer.parseInt(str1, 2);
        //  int j = Integer.parseInt(str2, 2);

        double solve =(1/(1+Math.exp(.9)));// -(1 / 1000) * i * (i - 1023) + 5 * Math.sin(i / 8) * Math.cos(i / 19)+100;
        // double solve = -(1 / 1000) * i * j * (i - 1023) + (j / (j + 1023)) + 5 * Math.tan(i / 8) * Math.cos(i / 19) + 5 * Math.sin((j * j) / 8) * Math.cos(j / 19);

        chromosome.value = i;
        chromosome.result = solve;
        chromosome.set_fitness(solve);
    }

    private void evaluateFitness(ArrayList<Chromosome> population) {
        for (Chromosome chromosome : population) {
            evaluateFitness(chromosome);
        }
    }

    @Override
    public void start(int loop) {
        for (int l = 0; l < loop; l++) {


            if (l == 2)
                System.out.println();
            // Selection Mechanism  - Step 1
            Selection();
            // Crossing Over  - Step 2
            crossOver();
            // Tranfer to Next Generation   - Step 3
            transmit();
            // Muataion the Generation  - Step 4
            mutation();
            // Evalute
            //     evaluateFitness(_population);
            findMinimumFitness();
            findMaximumFitness();

            whoisBest();
            normalizeAndScalingFitness();

            calculatingAverage();

            _lastGeneration = _population;


        }
    }


}
