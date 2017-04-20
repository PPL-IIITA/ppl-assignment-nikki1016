/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_11;

import java.io.BufferedWriter;
import java.util.Random;

/**
 * abstract Boys class 
 * @see boys
 */

abstract public class boys {
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
    Random rand = new Random();
    public String bname;
    public int min_attractive_req;
    public int attractive;
    public int intelligence;
    public int budget;
    public girls gf;
    public girls ex_gf;

    public boolean status;
    public int tot_gift_amount;
    public int b_happiness;
    /**
     * constructor() for boys class 
     * @param bno boy_no for unique name
     */
    public boys(int bno){
        bname = new String("bb"+bno);
        attractive = rand.nextInt(10);
        intelligence = rand.nextInt(10);
        budget  = rand.nextInt(3000) + 1000;
        min_attractive_req  = rand.nextInt(10);   
        status = false;
        gf = null;
        ex_gf = null;
        tot_gift_amount=0;
        b_happiness = 0;
    }    
    /**
     * the method b_happiness() is to calculate boy's happiness
     * as per his type 
     * @return b_happiness
     */
    abstract int b_happiness();  
    /**
     * this method tells whether boy can give more gifts or not as per the criterion 
     * @param gift
     * @return 
     */
    abstract boolean cangift(gifts gift);       
    int getBudget(){
        return budget;
    }
    int getAttractive(){
        return attractive;
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
    abstract void give_gift(couples couple[],gifts gift[],int n,int noOfGift,BufferedWriter fp);
}



