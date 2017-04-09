/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_3;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;



abstract public class girls {
    /**
     *Girls class containing :
     * @see girls
     * @param gname unique name of each girl
     * @param attractive,
     * @param maint_cost maintenence cost ,
     * @param tot_gift_amount total gift amount gifted to her by her boyfriend,
     * @param status status whether she is committed or not,
     * @param happiness happiness of each girl as per her type
     * @param intelligence intelligence level
     * @param arr_gift array list containing all gifts given by her bf(if any)

    */
    String gname;
    public int attractive;
    public int maint_cost;
    public int intelligence;
    public int tot_gift_amount;
    public int tot_gift_value;
    public boys bf;
    public boys ex_bf;

    public boolean status;
    public int g_happiness;
    public ArrayList<gifts> arr_gift;
    public ArrayList<Timestamp> arr_time;
    Random rand = new Random();
    /**
     * constructor
     * @param gno girl_no for unique name
     */
    public girls(int gno){
        gname = new String("gg"+gno);
        tot_gift_amount = 0;
        tot_gift_value = 0;
        status = false;
        bf = null;
        ex_bf = null;
        this.arr_gift = new ArrayList<gifts>();
        arr_time = new ArrayList<Timestamp>();

    }    
    /**
     * the method g_happiness() is to calculate girl's happiness
     * as per her type 
     * @return g_happiness
     */
    abstract int g_happiness();       
    int getMaintainence(){
        return maint_cost;
    }
    int getAttractive(){
        return attractive;
    }
    int getIntelligence(){
        return intelligence;
    }
}



