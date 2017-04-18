/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IllegealException;

import q_11.couples;

/**
 * exception raises when no suitable boy (other than ex-boyfriend) is found for girl recently breakup
 * @see Exception
 * @author nikita
 */
public class BreakupException extends Exception{
    /**
     * constructor() when no suitable boy (other than ex-boyfriend) is found for girl recently breakup
     * @param couple tell for which girl who breakup cannot find any new boyfriend
     */
    public BreakupException(couples couple){
        System.out.println("Exception found : "+couple.girl.getgname() +" : no other boy is available for her after breakup with "+couple.boy.bname);
    }
}
