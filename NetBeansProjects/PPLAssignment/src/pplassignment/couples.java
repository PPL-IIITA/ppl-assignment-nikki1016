/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pplassignment;
//import pplassignment.boys;
//import pplassignment.girls;
//import pplassignment.gifts;
import java.util.*;
/**
 *
 * @author nikita
 */
public class couples {
    public boys boy;
    public girls girl;
    int happiness;
    int compatability;
    //list of all the gifts gifted by boyfriend to his girlfriend
    public ArrayList<gifts> arr_gift;
    public couples(boys boy,girls girl){
        this.boy = boy;
        this.girl = girl;
        this.arr_gift = new ArrayList<gifts>();
    }
    //happiness of girlfriend as per her type
    int g_happiness(){
        
        int i,ess = 0 ,lux = 0,util = 0;
        int essd = 0,luxd = 0,utild = 0;
        String tt;
        for(i=0; i < arr_gift.size(); i++){
            tt = arr_gift.get(i).gifttype;
            if(tt.equals("essential")){
                essd += arr_gift.get(i).price;
                ess += arr_gift.get(i).value;
            }
            else if(tt.equals("luxury")){
                luxd += arr_gift.get(i).price;
                lux += arr_gift.get(i).value;
            }
            else if(tt.equals("utitlity")){
                utild += arr_gift.get(i).price;
                util += arr_gift.get(i).value;
            }
        }
        girl.tot_gift_amount = essd + luxd + utild;
        girl.tot_gift_value  = ess + lux + util;

        if(girl.g_type.equals("choosy")){
            girl.g_happiness = (int)(Math.log(girl.maint_cost - girl.tot_gift_amount ) + 2*lux);
        }
        else if(girl.g_type.equals("normal")){
            girl.g_happiness  = (int)(girl.maint_cost - girl.tot_gift_amount  );
        }
        else if(girl.g_type.equals("desparate")){
            girl.g_happiness = Math.getExponent(girl.maint_cost - girl.tot_gift_amount + girl.tot_gift_value);
        }
        return girl.g_happiness;
    }
    //happiness of boyfriend as per his type
    int b_happiness(){
        
        if(boy.b_type.equals("miser")){
            boy.b_happiness = (int)(boy.budget - boy.tot_gift_amount);
        }
        else if(boy.b_type.equals("generous")){
            boy.b_happiness = boy.gf.g_happiness;
        }
        else if(boy.b_type.equals("geeks")){
            boy.b_happiness = boy.gf.intelligence;
        }
        return boy.b_happiness;
    }
    //happiness of couple depending on their happinesses
    void happiness(){
        
        happiness =  g_happiness() + b_happiness();
    }
    //compatability  of couple 
    void compatibility(){
        double t1 = boy.budget - girl.maint_cost;
        int t2 = Math.abs(boy.attractive - girl.attractive );
        int t3 = Math.abs(boy.intelligence - girl.intelligence);
        compatability = (int)(t1+t2+t3);
    }
}
