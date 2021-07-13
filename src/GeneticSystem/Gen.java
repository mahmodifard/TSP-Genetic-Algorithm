package GeneticSystem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: javad
 * Date: 10/17/11
 * Time: 8:27 PM
 */
public class Gen implements Serializable {

    public ArrayList<Exon> _exons;
    public Exon _epistasis;
    private int _countOfExon;
    private int _countOfNucleotide;
    private TypeOfGen type;
    private String _genLabel;

    public Gen(TypeOfGen typeOfGen) {
        _exons = new ArrayList<Exon>();
        this.type = typeOfGen;

    }

    public void createExons(int countOfExon, int countOfNucleotide) {
        _countOfExon = countOfExon;
        _countOfNucleotide = countOfNucleotide;
        for (int i = 0; i < countOfExon; i++) {
            _exons.add(new Exon(countOfNucleotide));
        }

    }

    public void fillExon(int sRange, int eRange) {
        for (int i = 0; i < _countOfExon; i++) {
            _exons.get(i).fillNucleotide(sRange, eRange);
        }
        whatEpistasis();
    }

    public void whatEpistasis() {
        if (!_exons.isEmpty())
            _epistasis = _exons.get(0);
        else _epistasis = new Exon(_countOfNucleotide);
    }
}
