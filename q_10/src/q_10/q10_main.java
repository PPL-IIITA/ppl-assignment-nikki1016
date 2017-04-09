/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_10;

import java.io.FileNotFoundException;

/**
 *
 *Q10 main class 
 */
public class q10_main {
    /**
     * @param args 
     * <h1> Main method Q </h1>
     * allocate boyfriends to girlfriends
     * boyfriend gives gifts to his girlfriend
     * to take input from programmer to know which implementation should we use to find gf of boys
     * so randomly that choice is generated and implement that choice to find girlfriends of all boys
        
     * @throws FileNotFoundException if required file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {  
        /**
        * @param object is general class object to generate girls, boys and gifts and store them in files        
        *  @param randK object of RandomK class to make couples and allocate gifts to them using random out of best k method
       
        */
        generalClass object = new generalClass();
        object.arr_couple= new couples[object.noOfGirl];
        int i;
             //allocate boyfriends to girlfriends
            //random no of couples
        RandomK randk = new RandomK();
        object.noOfCouple = randk.allocate_rand(object.arr_boys,object.arr_girls,object. arr_couple, object.bw1);
        // boyfriend gives gifts to his girlfriend
        randk.allocate_gift_random(object.arr_couple, object.arr_gifts,object.noOfCouple, object.noOfGift,object.bw1);
            //time to calculate couples' happiness 
        object.object1.calculate_happiness_comp(object.arr_couple,object.noOfCouple);
        int k;
        k = Math.abs(object.rand.nextInt(object.noOfCouple));
        if(k ==0)
            k = Math.abs(object.rand.nextInt(object.noOfCouple)-6);
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
