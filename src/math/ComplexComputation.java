package math;

/**
 * @author achafos
 */
public class ComplexComputation {
    
    public static CxNum mFunction(CxNum z, CxNum c){
        CxNum ret = new CxNum(0.0, 0.0);
        CxNum sq = cPow(z);
        ret.r = sq.r + c.r;
        ret.i = sq.i + c.i;
        return ret;
    }
    
    public static CxNum cPow(CxNum num){
        return new CxNum(num.r * num.r - num.i * num.i, 2 * num.r * num.i);
    }
    
    public static CxNum cSin(CxNum num){
        //sin(a+bi)=sin(a) * cosh(b) +i cos(a) * sinh(b)
        return new CxNum(Math.sin(num.r) * Math.cosh(num.i), Math.cos(num.r) * Math.sinh(num.i));
    }
}
