/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_11;

import java.io.BufferedWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import IllegealException.*;

/**
    * Generous class subclass of boys
    * @see boys
    */

public class Generous extends boys{
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
     * constructor() of generous_boy class
     * @param bno boy_no for unique name
     */
    public Generous (int bno){
        super(bno);
    }
    /**
     * the method b_happiness() is to calculate boy's happiness
     * as per his type 
     * can_gift method to check whether boy can give more gifts or not
     *Generous boys whom happiness depends on their girlfriend's intelligence
     * @return b_happiness
     */
    int b_happiness(){ 
        if(status == false){
            b_happiness = 0;
            return b_happiness;
        }
        b_happiness = gf.intelligence;
        return b_happiness;
    }
    /**
     * this method tells whether boy can give more gifts or not as per the criterion 
     * @param gift
     * @return 
     */
    boolean cangift(gifts gift){
        if(gift.price  <= gf.maint_cost - tot_gift_amount)
            return true;
        else return false;
    }
   
   /**
    * create gift basket for each couple if boy's budget allows 
     * checking for couple formed yet and allocate gifts to his girlfriend
    * @param couple
    * @param gift
    * @param n
    * @param noOfGift
    * @param fp 
    */
    void give_gift(couples couple[],gifts gift[],int n,int noOfGift,BufferedWriter fp){
        Arrays.sort(gift, new utility.GiftComparator());
        try{
            fp.write("Gift Exchanges are : " );
            fp.newLine();
            gifts tempgift = null;
            int i,j;
            try{
                if(couple[n].boy == null)     
                    throw new NotFoundException(couple[n].boy);
                j=0;
                Timestamp tt =new Timestamp(System.currentTimeMillis()); 

                if(j == 0 && gift[j] != null && gift[j].price > (couple[n].boy.budget ) ){
                        //add gift to their basket
                        couple[n].boy.budget += gift[j].price;
                        couple[n].girl.arr_gift.add(gift[j]);
                        couple[n].girl.arr_time.add(tt);
                        couple[n].girl.tot_gift_value = gift[j].value;
                        couple[n].girl.tot_gift_amount = gift[j].price;
                        couple[n].boy.tot_gift_amount = gift[j].price;
                        fp.write(couple[n].boy.bname +" give gift"+ gift[j].id+ " of type "+ gift[j].getClass().getSimpleName() + " of price "+gift[j].price+ " to " +couple[n].girl.gname+ " at " + tt);
                        fp.newLine();
                        System.out.println(couple[n].boy.bname +" give gift"+ gift[j].id+ " of type "+ gift[j].getClass().getSimpleName() + " of price "+gift[j].price+ " to " +couple[n].girl.gname+ " at " + tt);                    
                }
                for(j =1; j< noOfGift; j++){
                    if(gift[j] == null)
                            continue;
                        int k=noOfGift;
                    //if boy is geek then gift one additional luxury gift to his girlfriend
                        try {
                            if(gift[j] != null && gift[j].price <= (couple[n].boy.budget - couple[n].girl.tot_gift_amount) && j!=k ){
                                //add gift to their basket
                                couple[n].girl.arr_gift.add(gift[j]);
                                tt =new Timestamp(System.currentTimeMillis()); 
                                couple[n].girl.arr_time.add(tt);
                                couple[n].girl.tot_gift_value += gift[j].value;
                                couple[n].girl.tot_gift_amount += gift[j].price;
                                couple[n].boy.tot_gift_amount += gift[j].price;
                                fp.write(couple[n].boy.bname +" give gift"+gift[j].id +" of type "+gift[j].getClass().getSimpleName() +  "of price "+gift[j].price+ " to " +couple[n].girl.gname+ " at " + tt);
                                fp.newLine();
                                System.out.println(couple[n].boy.bname +" give gift"+gift[j].id +" of type "+gift[j].getClass().getSimpleName() +  "of price "+gift[j].price+ " to " +couple[n].girl.gname+ " at " + tt);

                            }
                            else {
                                throw new GiftAllocationException(couple[n]);
                            }
                        }
                        catch(GiftAllocationException ee){
                            j = noOfGift;
                            Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

                        }
                    }

                }
                catch(NotFoundException ee){
                    Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

                }
            }
        catch(Exception e){
             e.printStackTrace();
        }
    }
    
    
}
