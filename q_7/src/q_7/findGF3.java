/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_7;

import java.util.Hashtable;

/**
 * extends find GF to find gf using Hash table
 * @see findGF 

 */
public class findGF3 extends findGF{
    /**
     * find method will find the girlfriend of a particular boy in a Hash table
     * @param boy
     * @param hm
     * @return name of girlfriend of required boy 
     */
    String find (boys boy,Hashtable<boys,girls> hm ){
        if(boy.status)
            return hm.get(boy).gname;
        else return null;
    }
}
