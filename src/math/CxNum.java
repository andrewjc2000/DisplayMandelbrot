/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

public class CxNum {
    public double r, i;
    
    public CxNum(double real, double imag){
        r = real;
        i = imag;
    }
    
    public String str(){
        return r + " + " + i + "i";
    }
    
    public double abs(){
        return Math.sqrt(r * r + i * i);
    }
}
