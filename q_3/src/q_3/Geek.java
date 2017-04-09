/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_3;

public class Geek extends boys{
     /**
     *Boys class containing :
     * @see boys
     * @param bname unique name of each boy
     * @param attractive,
     * @param min_attractive_req minimum attractive_requirement of their girlfriends  ,
     * @param tot_gift_amount total_gift amount that he gifted to his gf and his gf (if any).
     * @param status status whether she is committed or not,
     * @param happiness happiness of each boy as per his type
     * @param intelligence intelligence level
     * @param arr_gift array list containing all gifts given by her bf(if any)
    */
    
    /**
     * constructor
     * @param bno boy_no for unique name
     */
    public Geek(int bno){
        super(bno);
        attractive = rand.nextInt(5)+3;
        intelligence = rand.nextInt(5)+5;
        budget  = rand.nextInt(2500) + 1000;
        min_attractive_req  = rand.nextInt(5)+3; 
    }
    /**
     * the method b_happiness() is to calculate boy's happiness
     * as per his type 
     * can_gift method to check whether boy can give more gifts or not
     *Generous boys whom happiness depends on their girlfriend's intelligence
     * @return b_happiness
     */
    int b_happiness(){       
        b_happiness = (int)(budget - tot_gift_amount);
        return b_happiness;
    }
    boolean cangift(gifts gift){
        if(gift.price <= gf.maint_cost - tot_gift_amount)
            return true;
        else return false;
    }
    
}

