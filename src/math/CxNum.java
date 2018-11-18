package math;

/**
 * @author achafos
 */
public class CxNum {
    public double r, i;
    
    public CxNum(double real, double imag){
        r = real;
        i = imag;
    }
    
    public double abs(){
        return Math.sqrt(r * r + i * i);
    }

    @Override
    public String toString(){
        return r + " + " + i + "i";
    }
}
