package GeneticSystem;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: javad
 * Date: 10/17/11
 * Time: 8:24 PM
 */
public class Nucleotide implements Serializable {

    private double _dValue;
    private String _sValue;
    private int _iValue;

    public Nucleotide() {
    }

    public Nucleotide(double x) {
        _dValue = x;
    }

    public Nucleotide(int _iValue) {
        this._iValue = _iValue;
    }

    public Nucleotide(String _sValue) {

        this._sValue = _sValue;
    }

    public double get_dValue() {
        return _dValue;
    }

    public void set_dValue(double _dValue) {
        this._dValue = _dValue;
    }

    public String get_sValue() {
        return _sValue;
    }

    public void set_sValue(String _sValue) {
        this._sValue = _sValue;
    }

    public int get_iValue() {
        return _iValue;
    }

    public void set_iValue(int _iValue) {
        this._iValue = _iValue;
    }
}
