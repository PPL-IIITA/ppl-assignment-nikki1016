/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_6;

import java.sql.Timestamp;
/**
 * 
 * Couples Class 
 * @see couples
*/
public class couples {
    
/**
 * @see couples
*Couples Class containing 
* @param boy boyfriend
* @param girl girlfriend
* @param happiness happiness of couple
* @param compatability compatability of couple
* @param timestamp when both get committed
*/
    boys boy;
    public girls girl;
    int happiness;
    int compatability;
    Timestamp timestamp;
    /**
     *constructors  
     * one for simple commitment's
     * @param boy boy 
     * @param girl girl
     */
    public couples(boys boy,girls girl){
        this.boy = boy;
        this.girl = girl;
        timestamp = new Timestamp(System.currentTimeMillis());
    }
    /**
     * constructor
     * for couples  committed at a particular time
     * @param boy boy 
     * @param girl girl
     * @param t timestamp
     */
    public couples(boys boy,girls girl,Timestamp t){
        this.boy = boy;
        this.girl = girl;
        timestamp = t;
    }
    /**
     * happiness method to calculate happiness of couple depending on happiness of both
     */

    void happiness(){
        happiness =  girl.g_happiness() + boy.b_happiness();
    }
    /**
     *   compatability method to calculate compatability of couple 
     */
    void compatibility(){
        double t1 = boy.budget - girl.maint_cost;
        int t2 = Math.abs(boy.attractive - girl.attractive );
        int t3 = Math.abs(boy.intelligence - girl.intelligence);
        compatability = (int)(t1+t2+t3);
    }
    int getHappiness(){
        return happiness;
    }
    int getCompatibility(){
        return compatability;
    }
}
