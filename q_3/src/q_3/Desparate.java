/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_3;

/**
 *
 * The desperate girl subclass of girls
 * @see girls
 */



public class Desparate extends girls{
    /**
     * @see girls
    *The desperate girl
    * constructor
    * @param gno girl_no for unique girl id
    */
    public Desparate(int gno){
        super(gno);
        attractive = rand.nextInt(3)+8;
        intelligence = rand.nextInt(10);
        maint_cost = rand.nextInt(3000)+1000;
    }
    /**
     * the method g_happiness method to calculate girl's happiness
     * happiness in a relationship is exponential to the total cost of gifts received over maintenance, including luxury gifts. g_happiness method to calculate girl's happiness
    * @return g_happiness
     */
    @Override
     int g_happiness(){      
        g_happiness = Math.getExponent(maint_cost - tot_gift_amount + tot_gift_value);
        return g_happiness;

     }
}