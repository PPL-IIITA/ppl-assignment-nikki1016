/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IllegealException;

import q_11.*;
/**
 * exception raises when no suitable boy,girl or gift is found
 * @see Exception
 * @author nikita
 */
public class NotFoundException extends Exception{
    /**
     * constructor() called when no  girl is found
     * @param girl girl  not found
     */
    public NotFoundException(girls girl){
        System.out.println("Exception found : no girl found");
    }
    /**
     * constructor() called when no boy is found 
     * @param boy boy not found
     */
    public NotFoundException(boys boy){
        System.out.println("Exception found : no boy found");
    }
    /**
     * constructor() called when no gift is found 
     * @param gift gift not found
     */
    public NotFoundException(gifts gift){
        System.out.println("Exception found : no gift found");
    }
}
