/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IllegealException;

import q_11.*;

/**
 * exception raises when no type is found
 * @see Exception
 * @author nikita
 */
public class TypeNotAllowed extends Exception{
    /**
     * constructor() called when no type is found for this girl 
     * @param j girl id
     * @param girl girl for whom no type is found
     */
    public TypeNotAllowed(int  j,girls girl){
        System.out.println("Exception found : gg"+j+" - illegal type found");
    }
    /**
     * constructor() called when no type is found for this boy
     * @param j boy id
     * @param boy boy for whom no type is found
     */
    public TypeNotAllowed(int  j,boys boy){
        System.out.println("Exception found : bb"+j+" - illegal type found");
    }
    /**
     * constructor() called when no type is found for this gift
     * @param j gift id
     * @param gift gift for which no type is found
     */
    public TypeNotAllowed(int  j,gifts gift){
        System.out.println("Exception found : gift id "+ j +"  - illegal type found");
    }
    
}
