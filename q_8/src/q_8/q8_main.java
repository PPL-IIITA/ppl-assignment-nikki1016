/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_8;

import java.io.FileNotFoundException;

/**
 *
 *Q8 main class 
 */
public class q8_main {/**
     * @param args 
     * <h1> Main method Q3 </h1>
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
        object.noOfCouple =object.object1.allocate(object.arr_boys,object.arr_girls,object. arr_couple, object.bw1);
        System.out.println("Which implementation do u want to choose for allocating gifts to girlfriends by their boyfriends? ");
        System.out.println("0 - general way , or 1 - atleast one gift of all type gifts method ");
        /* to take input from programmer to know which implementation should we use to find gf of boys
            so randomnly that choice is generated and implement that choice to select which way you wanna gift the gifts to girlfriends
        */
        int k = object.rand.nextInt(2);
        int i;
        //reference of base class findGF 
        GiftSelector gf;
        
        // boyfriend gives gifts to his girlfriend 
        System.out.println(k);
        // to select from stated implementation
        switch(k){
                // find gf from array of couples
                case 0:
                        gf = new giftSelector1();
                        ((giftSelector1)(gf)).allocate_gift(object.arr_couple, object.arr_gifts,object.noOfCouple, object.noOfGift,object.bw1);
                    break;
                case 1:
                        gf = new giftSelector2();
                        ((giftSelector2)(gf)).allocate_gift(object.arr_couple, object.arr_gifts,object.noOfCouple, object.noOfGift,object.bw1);
                    break;
                default :
                        object.object1.allocate_gift(object.arr_couple, object.arr_gifts,object.noOfCouple, object.noOfGift,object.bw1);
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
