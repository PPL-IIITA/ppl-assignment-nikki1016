/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_5;

import java.io.FileNotFoundException;

/**
 *
 *Q5 main class 
 */
public class q5_main {
    /**
     * @param args 
     * <h1> Main method Q3 </h1>
     * allocate boyfriends to girlfriends
     * either using allocate method that is allocate to all girls one by one
     * or allocate1 method that is make couples by select first one girl and then one boy
     * boyfriend gives gifts to his girlfriend
     * also calculate couple's happiness 
     * and find  k best happiest couples
     * and k best Compatible couples
     * @throws FileNotFoundException if required file is not found
     */
    public static void main(String[] args)  throws FileNotFoundException {
        /**
        * @param object is general class object to generate girls, boys and gifts and store them in files        
        */
        generalClass object = new generalClass();
        
        object.arr_couple= new couples[object.noOfGirl];
            //allocate boyfriends to girlfriends
            //random no of couples  
        int  t = Math.abs(object.rand.nextInt(2));
        if(t == 0){
            object.noOfCouple = object.object1.allocate(object.arr_boys,object.arr_girls,object. arr_couple, object.bw1);
        }
        else            
            object.noOfCouple = object.object1.allocate1(object.arr_boys,object.arr_girls, object.arr_couple, object.bw1);
        // boyfriend gives gifts to his girlfriend
        object.object1.allocate_gift(object.arr_couple, object.arr_gifts,object.noOfCouple, object.noOfGift,object.bw1);
            //time to calculate couples' happiness 
        object.object1.calculate_happiness_comp(object.arr_couple,object.noOfCouple);
        int k=0;
                
        k = Math.abs(object.rand.nextInt(object.noOfCouple));
        if(k <= 1)
            k = Math.abs(object.rand.nextInt(object.noOfCouple));
             // k best happiest couples
        object.object1.bestKHappiestCouple(object.arr_couple,object.noOfCouple, k);
        
        try{
                if(object.bw1!=null)
                    object.bw1.close();
                if(object.bw2!=null)
                    object.bw2.close();
                if(object.bw3!=null)
                    object.bw3.close();
                if(object.bw4!=null)
                    object.bw4.close();
            }
            catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        
    }    
}
