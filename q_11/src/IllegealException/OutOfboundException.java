/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IllegealException;

/**
 * exception raises when k is greater than total no of couples
 * @see Exception
 * @author nikita
 */
public class OutOfboundException extends Exception{
    /**
     * constructor() k is greater than  total no of couples
     * @param i k
     * @param j total no of couples
     */
    public OutOfboundException(int i,int j){
        System.out.println("Exception found : out of bound " +i + " > " + j );
    }
}
