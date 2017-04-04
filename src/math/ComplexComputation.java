/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 *
 * @author 18achafos
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
        return new CxNum(Math.pow(num.r, 2) - Math.pow(num.i, 2), 2 * num.r * num.i);
    }
}
