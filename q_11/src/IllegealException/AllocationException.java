/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IllegealException;

import q_11.*;

/**
 * exception raises when no suitable boy is found for particular girl
 * @see Exception
 * @author nikita
 */

public class AllocationException extends Exception{
    /**
     * constructor() no suitable boy is found for particular girl
     * @param girl to know for which girl
     */
    public AllocationException(girls girl){
        System.out.println("Exeption found : no boy is available for this girl named "+ girl.getgname());
    }
}
