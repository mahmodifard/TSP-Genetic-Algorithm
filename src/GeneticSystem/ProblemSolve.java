package GeneticSystem;

import com.sun.corba.se.spi.copyobject.ReflectiveCopyException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Moori
 * Date: 7/28/11
 * Time: 10:18 PM
 */
public abstract class ProblemSolve implements Serializable {
    protected int _countofPopulation;
    protected double _creossPercend;
    protected double _mutationPercend;
    protected int _countofGen;
    protected int _countofExon;
    protected int _countofNucleotide;
    protected int[] _countofNucleotides;
    protected int sRange, eRange;
    public int indexofMaxFitness;
    public double maxFitness;

    protected Chromosome _goodAnswer;

    protected ArrayList<Chromosome> _population;
    protected ArrayList<Chromosome> _lastGeneration;
    protected ArrayList<Chromosome> _nextGeneration;

    protected ArrayList<Double> _averageofGenerations;
    protected ArrayList<Double> _goodofGeneration;
    protected ArrayList<Double> _badofGeneration;

    public ProblemSolve(int countofPopulation, double creossPercend, double mutationPercend, int countofGen, int countofNucleotide, int countOfExon) {

        this._countofNucleotide = countofNucleotide;
        this._countofExon = countOfExon;
        this._goodAnswer = new Chromosome(countofGen, countOfExon, _countofNucleotide);
        this._countofGen = countofGen;
        this._countofPopulation = countofPopulation;
        this._creossPercend = creossPercend;
        this._mutationPercend = mutationPercend;
        this._population = new ArrayList<Chromosome>();
        this._badofGeneration = new ArrayList<Double>();
        this._goodofGeneration = new ArrayList<Double>();
        this._averageofGenerations = new ArrayList<Double>();
        this._population = new ArrayList<Chromosome>();
        this._lastGeneration = new ArrayList<Chromosome>();
        this._nextGeneration = new ArrayList<Chromosome>();

    }

    private double getOutlet() {
        double max = findMaximumFitness();
        double min = findMinimumFitness();

        return (max - min);
    }

    protected void normalizeAndScalingFitness() {

        double max = findMaximumFitness();
        double min = findMinimumFitness();

        double outlet;
        outlet = (max - min);

        for (int i = 0; i < _countofPopulation; i++) {
            _population.get(i).set_fitness((Math.abs(_population.get(i).get_fitness() - min) / outlet));

        }

        /*double sum = 0;
        for (int i = 0; i < _countofPopulation; i++) {
            sum += Math.abs(_population.get(i).get_fitness());
        }
        if (sum == 0)
            sum = 1;
        for (int i = 0; i < _countofPopulation; i++) {
            _population.get(i).set_fitness(((int)(_population.get(i).get_fitness()) / sum));

        }*/
    }

    protected void calculatingAverage() {
        double sum = 0;
        for (int i = 0; i < _countofPopulation; i++) {
            sum += _population.get(i).get_fitness();
        }
        _averageofGenerations.add((sum / _countofPopulation));
    }

    protected double findMaximumFitness() {
        double maxFitness = _population.get(0).get_fitness();
        int index = 0;
        for (int i = 1; i < _countofPopulation; i++) {
            if (maxFitness < _population.get(i).get_fitness()) {
                maxFitness = _population.get(i).get_fitness();
                index = i;
            }
        }
        _goodofGeneration.add(maxFitness);
/*        if (_goodAnswer.get_fitness() < maxFitness) {

            try {
                _goodAnswer = (Chromosome) _population.get(index).copy();
            } catch (ReflectiveCopyException e) {
                e.printStackTrace();
            }
        }*/
        this.indexofMaxFitness = index;
        this.maxFitness = maxFitness;
        return maxFitness;
    }

    protected double findMinimumFitness() {
        double minFitness = _population.get(0).get_fitness();
        int index = 0;
        for (int i = 1; i < _countofPopulation; i++) {
            if (minFitness > _population.get(i).get_fitness()) {
                minFitness = _population.get(i).get_fitness();
                index = i;
            }
        }
        _badofGeneration.add(minFitness);
        return minFitness;
    }

    public void Selection() {
        // در اینجا ما به اندازه امتیاز هر عضو به آن یک عددی را اختصاص می دهیم که در قرعه کسشی بتشئ
        ArrayList<Integer> list = new ArrayList<Integer>();
        int counter = 0;
        for (int i = 0; i < _countofPopulation; i++) {
            double temp = (_population.get(i).get_fitness());
            temp = Math.ceil((temp*temp*temp)*100) ;
            for (int j = 0; j < temp; j++) {
                list.add(i);
                counter++;
            }
        }
        Random random = new Random();
        int x = 0;
        int who = 0;
        for (int i = 0; i < _countofPopulation; i++) {
            x = Math.abs(random.nextInt(counter));
            who = list.get(x);
            _nextGeneration.add(_population.get(who));
        }
        _population.clear();
    }

    protected void transmit() {
        for (Chromosome chromosome : _nextGeneration) {
            evaluateFitness(chromosome);
            _population.add(chromosome);
        }
    }

    protected void crossOver() {
        int persend = (int) Math.ceil(_creossPercend * _countofPopulation);
        Random rand = new Random();
        ArrayList<Integer> temps = new ArrayList<Integer>();
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 0; i < persend; i += 2) {

            temp1 = Math.abs(rand.nextInt(_countofPopulation));
            temp2 = Math.abs(rand.nextInt(_countofPopulation));

            temps.add(temp1);
            temps.add(temp2);

            Chromosome chromosome = new Chromosome(_countofGen, _countofExon, _countofNucleotide);
            chromosome.intilizeGens(_countofExon, _countofNucleotide);

            Chromosome chromosome2 = new Chromosome(_countofGen, _countofExon, _countofNucleotide);
            chromosome2.intilizeGens(_countofExon, _countofNucleotide);

            for (int j = 0; j < _countofGen; j++) {
                chromosome._gGens.get(j).whatEpistasis();
                chromosome2._gGens.get(j).whatEpistasis();
            }


            int sizeofNucl = _countofNucleotide;
            int lenght = Math.abs(rand.nextInt(sizeofNucl));
            int xx;

            for (int j = 0; j < lenght; j++) {
                for (int k = 0; k < _countofGen; k++) {
                    xx = _nextGeneration.get(temp1)._gGens.get(k)._epistasis._nucleotides.get(j).get_iValue();
                    chromosome._gGens.get(k)._epistasis._nucleotides.get(j).set_iValue(xx);

                }


            }
            for (int j = lenght; j < sizeofNucl; j++) {
                for (int k = 0; k < _countofGen; k++) {
                    xx = _nextGeneration.get(temp2)._gGens.get(k)._epistasis._nucleotides.get(j).get_iValue();
                    chromosome._gGens.get(k)._epistasis._nucleotides.get(j).set_iValue(xx);

                }


            }

            lenght = Math.abs(rand.nextInt(sizeofNucl));
            for (int j = sizeofNucl - 1; j > lenght; j--) {
                for (int k = 0; k < _countofGen; k++) {
                    xx = _nextGeneration.get(temp1)._gGens.get(k)._epistasis._nucleotides.get(j).get_iValue();
                    chromosome2._gGens.get(k)._epistasis._nucleotides.get(j).set_iValue(xx);

                }


            }
            for (int j = lenght; j >= 0; j--) {
                for (int k = 0; k < _countofGen; k++) {
                    xx = _nextGeneration.get(temp2)._gGens.get(k)._epistasis._nucleotides.get(j).get_iValue();
                    chromosome2._gGens.get(k)._epistasis._nucleotides.get(j).set_iValue(xx);

                }


            }

            evaluateFitness(chromosome);
            evaluateFitness(chromosome2);
            _population.add(chromosome);
            _population.add(chromosome2);

        }
        for (Integer temp : temps) {
            try {
                _nextGeneration.remove((int) temp);
            } catch (Exception e) {

            }
        }

    }

    protected void mutation() {

        int persend = (int) Math.ceil(_mutationPercend * _countofPopulation);
        Random rand = new Random();
        int x;
        for (int i = 0; i < persend; i++) {
            x = Math.abs(rand.nextInt(_countofPopulation));
            int x2 = Math.abs(rand.nextInt((int) Math.ceil(_countofNucleotide / 2)));
            for (int j = 0; j < x2; j++) {
                int index = Math.abs(rand.nextInt(_countofNucleotide));
                int val = sRange + rand.nextInt((eRange - sRange));
                for (int k = 0; k < _countofGen; k++) {
                    _population.get(x)._gGens.get(k)._epistasis._nucleotides.get(index).set_iValue(val);

                }
            }
            evaluateFitness(_population.get(x));
        }

    }

    protected void whoisBest() {
        if (_goodAnswer.get_fitness() <= maxFitness) {
            try {
                _goodAnswer = (Chromosome) _population.get(indexofMaxFitness).copy();
                _goodAnswer.set_fitness(this.maxFitness);
            } catch (ReflectiveCopyException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract Object getBestOfChromosome();

    protected abstract void evaluateFitness(Chromosome chromosome);

    protected abstract void start(int loop);


}
