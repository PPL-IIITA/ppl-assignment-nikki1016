/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IllegealException;

import java.util.Random;

/**
 * exception raises when time period extends one month
 * @see Exception
 * @author nikita
 */
public class IllegalTimeException extends Exception{
    Random rand = new Random();
    /**
     * constructor() called when time period extends one month
     * @param a new timestamp
     * @param b previous timestamp
     * @return a new timestamp between one month
     */
    public int correcttime(int a,int b){
        a -= b;
        a = Math.abs(rand.nextInt(5))+ b +1;
        if(a > 30){
            a=30;
        }
        return a;
    }
}
