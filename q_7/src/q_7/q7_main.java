/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_7;

import java.io.FileNotFoundException;
import java.util.Hashtable;


/**
 *
 *Q7 main class 
 */
public class q7_main {
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
        */
        // generate boys , girls and gifts randomly and store them in seperate files
        generalClass object = new generalClass();
        //couples' array
        object.arr_couple= new couples[object.noOfGirl];
            //allocate boyfriends to girlfriends
            //random no of couples
        object.noOfCouple = object.object1.allocate(object.arr_boys,object.arr_girls,object. arr_couple, object.bw1);
            // boyfriend gives gifts to his girlfriend 
        object.object1.allocate_gift(object.arr_couple, object.arr_gifts,object.noOfCouple, object.noOfGift,object.bw1);
        int i,k;
        System.out.println("Which implementation do u want to choose for finding girlfriends of all boys? ");
        System.out.println("0 - array, sorted array, 1 - binary search or 2 - hash table.");
        
        k = object.rand.nextInt(3);
        System.out.println(k);
        //reference of base class findGF 
        findGF findgf;
        String girl ;
        // to select from stated implementation
        switch(k){
                // find gf from array of couples
                case 0:
                    for(i =0 ; i < object.noOfBoy; i++){   
                        findgf = new findGF1();
                        girl = ((findGF1)(findgf)).find(object.arr_boys[i].bname,object.arr_couple,object.noOfCouple);
                        if(girl == null){
                            System.out.println(object.arr_boys[i].bname + " is not committed ");
                        }
                        else{
                            System.out.println("girlfriend of "+ object.arr_boys[i].bname + " is " + girl);
                        }
                    }
                    break;
                    // find gf from sorted array using binary search
                case 1:
                    for(i =0 ;i< object.noOfBoy; i++){
                        findgf = new findGF2();
                        girl = ((findGF2)(findgf)).find(object.arr_boys[i].bname,object.arr_boys);
                        if(girl == null){
                            System.out.println(object.arr_boys[i].bname + " is not committed ");
                        }
                        else{
                            System.out.println("girlfriend of "+ object.arr_boys[i].bname + " is " + girl);
                        }
                    }
                    break;
                    // find gf from hashtables
                case 2:
                    Hashtable<boys,girls> hm = new Hashtable<boys,girls>();
                    for(i =0 ; i < object.noOfBoy; i++){
                        if(object.arr_boys[i].status )
                             hm.put(object.arr_boys[i],object.arr_boys[i].gf); 
                       
                    }
                         
                    for(i =0 ;i< object.noOfBoy; i++){
                        findgf = new findGF3();
                        girl = ((findGF3)(findgf)).find(object.arr_boys[i],hm);
                        if(girl == null){
                            System.out.println(object.arr_boys[i].bname + " is not committed ");
                        }
                        else{
                            System.out.println("girlfriend of "+ object.arr_boys[i].bname + " is " + girl);
                        }
                    }
                    break;
                default:
                    for(i =0 ;i< object.noOfBoy; i++){
            
                    }
                    break;    
        }
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
