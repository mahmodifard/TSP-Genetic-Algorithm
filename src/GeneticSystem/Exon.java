package GeneticSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: javad mahmodifars
 * Date: 10/17/11
 * Time: 9:26 PM
 */
public class Exon implements Serializable {

    public ArrayList<Nucleotide> _nucleotides;
    private int _countAnInt;
    private double _fitness;

    public Exon(int countOfNucleotide) {

        _nucleotides = new ArrayList<Nucleotide>();
        _countAnInt = countOfNucleotide;
        initilizeNucleotide();
    }

    public void initilizeNucleotide() {
        _nucleotides.clear();
        for (int i = 0; i < _countAnInt; i++) {
            _nucleotides.add(new Nucleotide());

        }
    }

    public void fillNucleotide(int sRange, int eRange) {
        initilizeNucleotide();
        Random random = new Random();
        int x;
        for (int i = 0; i < _countAnInt; i++) {
            x = sRange + (random.nextInt(eRange - sRange));
            _nucleotides.get(i).set_iValue(x);

        }
    }
}
