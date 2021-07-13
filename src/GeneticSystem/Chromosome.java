package GeneticSystem;

import com.sun.corba.se.spi.copyobject.ReflectiveCopyException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: javad
 * Date: 10/20/11
 * Time: 9:16 PM
 */
public class Chromosome implements Serializable {
    public ArrayList<Gen> _gGens;
    private int _countofGen;
    private double _fitness = 0;
    int value;
    int countofExon, countofnucleotide;
    double result;

    public double get_fitness() {
        return _fitness;
    }

    public void set_fitness(double _fitness) {
        this._fitness = _fitness;
    }

    public Chromosome(int _countofGen, int countofExon, int countofnucleotide) {
        this._countofGen = _countofGen;
        this._gGens = new ArrayList<Gen>();
        this.countofExon = countofExon;
        this.countofnucleotide = countofnucleotide;
    }

    public void intilizeGens(int countOfExon, int countOfNucleotide) {
        this.countofExon = countOfExon;
        this.countofnucleotide = countOfNucleotide;
        intilizeGens();

    }

    public void intilizeGens() {
        for (int i = 0; i < _countofGen; i++) {
            _gGens.add(new Gen(TypeOfGen.Other));
            _gGens.get(i).createExons(countofExon, countofnucleotide);
        }

    }

    public void createGens(int countOfExon, int countOfNucleotide, int sRange, int eRange) {
        for (int i = 0; i < _countofGen; i++) {

            _gGens.add(new Gen(TypeOfGen.Other));
            _gGens.get(i).createExons(countOfExon, countOfNucleotide);
            _gGens.get(i).fillExon(sRange, eRange);

        }

    }

    public Object copy() throws ReflectiveCopyException {
        Chromosome ch = new Chromosome(_countofGen, countofExon, countofnucleotide);
        ch.intilizeGens();
        ch.set_fitness(get_fitness());
        ch.result = this.result;
        ch.value = this.value;
        for (int i = 0; i < _countofGen; i++) {
            for (int j = 0; j < countofExon; j++) {
                for (int k = 0; k < countofnucleotide; k++) {
                    ch._gGens.get(i)._exons.get(j)._nucleotides.get(k).set_iValue(this._gGens.get(i)._exons.get(j)._nucleotides.get(k).get_iValue());
                    ch._gGens.get(i)._exons.get(j)._nucleotides.get(k).set_sValue(this._gGens.get(i)._exons.get(j)._nucleotides.get(k).get_sValue());
                    ch._gGens.get(i)._exons.get(j)._nucleotides.get(k).set_dValue(this._gGens.get(i)._exons.get(j)._nucleotides.get(k).get_dValue());
                }

            }

        }
        return ch;
    }
}
