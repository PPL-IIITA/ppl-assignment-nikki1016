/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_8;

/**
 * 
    *The Normal girl subclass of girls
    * * @see girls
 */

public class Normal extends girls{
     /**
     * 
    * constructor
    * @param gno girl_no for unique girl id
    */
    public Normal(int gno){
        super(gno);
        attractive = rand.nextInt(8);
        intelligence = rand.nextInt(10);
        maint_cost = rand.nextInt(1000)+1000;
    }
    /**
     * the method g_happiness method to calculate girl's happiness
    * @return g_happiness
     */
     int g_happiness(){      
        g_happiness  = (int)(maint_cost - tot_gift_amount  );
        return g_happiness;
     }
}