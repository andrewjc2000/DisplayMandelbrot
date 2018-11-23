package math;

/**
 * @author achafos
 */
public class ComplexComputation {

    
    public static CxNum cPow(CxNum num){
        return new CxNum(num.r * num.r - num.i * num.i, 2 * num.r * num.i);
    }
    
    public static CxNum cSin(CxNum num){
        //sin(a+bi)=sin(a) * cosh(b) +i cos(a) * sinh(b)
        return new CxNum(Math.sin(num.r) * Math.cosh(num.i), Math.cos(num.r) * Math.sinh(num.i));
    }

    public static int getMandelbrotValue(CxNum c, int maxIterations){
        CxNum z = new CxNum(0.0, 0.0);//these are always the starting values!
        int j = 0;
        CxNum sq;
        while(z.abs() < 2 && j < maxIterations){
            sq = cPow(z);
            z = new CxNum(sq.r + c.r, sq.i + c.i);
            j++;
        }
        return z.abs() <= 2 ? maxIterations : j;
    }
}
