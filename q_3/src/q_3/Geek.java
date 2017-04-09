/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_3;

/* 
	*attributes of boys with their types :
	*boy name(unique),minimum attractive_requirement of their girlfriends,attractiveness,intelligence,budget status,
	*happiness,total_gift amount that he gifted to his gf and his gf (if any).
        *boys class is abstract since no need to instantiate this class as boy can be of any type.
	*b_happiness method to calculate boy's happiness
	*can_gift method to check whether boy can give more gifts or not
	*Generous boys whom happiness depends on their girlfriends' intelligence
        *The Geeks, who gift their girlfriend with enough gifts, equal or just over the maintenance cost. They
        *additionally give one luxury gift, if budget allows. The happiness of these boys is given by the intelligence
        *of their girlfriend alone.
*/
public class Geek extends boys{
    public Geek(int bno){
        super(bno);
    }
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

