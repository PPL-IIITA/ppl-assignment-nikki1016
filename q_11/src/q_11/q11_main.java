/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_11;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.*;
import IllegealException.*;
/**
 *
 *<h1> Q11 main class </h1>
 */
public class q11_main {
        
    /**
     * @param args 
     * <h1> Main method Q11 </h1>
     * allocate boyfriends to girlfriends
     * boyfriend gives gifts to his girlfriend
     * also calculate couple's happiness 
     * and find  k best happiest couples
     * and k best Compatible couples
     * and k least happiest couples after t days 
     * @throws FileNotFoundException if required file is not found
     */
    public static void main(String[] args)throws FileNotFoundException {
        
        // TODO code application logic here
        /*
        @param object is general class object to generate girls, boys and gifts and store them in files 
       
        */
        try{
            generalClass object = new generalClass();
            object.arr_couple= new couples[object.noOfGirl];
                //allocate boyfriends to girlfriends
                //random no of couples
            object.noOfCouple = object.object1.allocate(object.arr_boys,object.arr_girls, object.arr_couple, object.bw1);
            // boyfriend gives gifts to his girlfriend
            object.object1.allocate_gift(object.arr_couple, object.arr_gifts,object.noOfCouple, object.noOfGift,object.bw1);
                //time to calculate couples' happiness 
            object.object1.calculate_happiness_comp(object.arr_couple,object.noOfCouple);
            int k,t=0;
            k = Math.abs(object.rand.nextInt(object.noOfCouple+1));
            
            if(k==0)
                k = object.noOfCouple;
            object.object1.bestKHappiestCouple(object.arr_couple, object.noOfCouple, k);
            object.object1.bestKCompatibleCouple(object.arr_couple, object.noOfCouple, k);
            for(int i=0; i<k; i++){
                int q = t;
                t = Math.abs(object.rand.nextInt(5))+q+1;
                try{
                    if(t > 30){
                        throw new IllegalTimeException();
                    }
                }
                catch(IllegalTimeException ee){
                    t = ee.correcttime(t,q);
                    Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

                }
                //System.out.println("t is "+t +"i is "+i);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Calendar cal = Calendar.getInstance();
                cal.setTime(timestamp);
                cal.add(Calendar.DAY_OF_MONTH, t);
                timestamp.setTime(cal.getTime().getTime());
                //System.out.println("t " + t + " timestamp " + timestamp);
                // k least happiest couples
                object.object1.leastKHappiestCouple(object.arr_couple,object.noOfCouple, k,object.arr_boys, object.arr_girls,object.bw1,timestamp,object.arr_gifts);
                System.out.println("");
                
                }
                if(object.bw1!=null)
                        object.bw1.close();
                    if(object.bw2!=null)
                        object.bw2.close();
                    if(object.bw3!=null)
                        object.bw3.close();
                    if(object.bw4!=null)
                        object.bw4.close();
        }
        catch(IOException ee){
            Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);
        }
        
    }    
}
