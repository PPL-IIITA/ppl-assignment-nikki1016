/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_4;

import java.io.FileNotFoundException;

/**
 *
 * @author nikita
 */
public class q4_main {
    /**
     * @param args 
     * <h1> Main method Q4 </h1>
     * allocate boyfriends to girlfriends
     * boyfriend gives gifts to his girlfriend
     * also calculate couple's happiness 
     * and find  k best happiest couples
     * and k best Compatible couples
     * and k least happiest couples
     * @throws FileNotFoundException if required file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        /**
        * @param object is general class object to generate girls, boys and gifts and store them in files        
        */
        generalClass object = new generalClass();
        object.arr_couple= new couples[object.noOfGirl];
            //allocate boyfriends to girlfriends
            //random no of couples
        object.noOfCouple =object.object1.allocate(object.arr_boys,object.arr_girls,object. arr_couple, object.bw1);
        // boyfriend gives gifts to his girlfriend
        object.object1.allocate_gift(object.arr_couple, object.arr_gifts,object.noOfCouple, object.noOfGift,object.bw1);
            //time to calculate couples' happiness 
        object.object1.calculate_happiness_comp(object.arr_couple,object.noOfCouple);
        int k;
        k = Math.abs(object.rand.nextInt(object.noOfCouple));
        if(k ==0)
            k = Math.abs(object.rand.nextInt(object.noOfCouple)-6);
             // k best happiest couples
        object.object1.bestKHappiestCouple(object.arr_couple,object.noOfCouple, k);
            
            
        object.object1.leastKHappiestCouple(object.arr_couple,object.noOfCouple, k,object.arr_boys, object.arr_girls,object.bw1);
            
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
