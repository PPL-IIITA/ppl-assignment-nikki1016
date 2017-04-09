/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_8;

/**
 *
 * utility Gifts subclass of gifts
 * @see gifts
 * 
 */
public class Utility extends gifts{
    /**
      * @see gifts
     * Gifts class containing :
    * @param price  the price of gift;
    * @param value the value of gift
    * @param utilValue utility value
    * @param utilClass utility class
    * @param id unique id of gift
    * abstract gifts class as there is no need to instantiate it 
    */
    int utilValue;
    String utilClass;
    /**
     * constructor
     * @param gfno for unique gift id 
     */
    public Utility(int gfno){
        super(gfno);
        value = rand.nextInt(100)+200;
        price = rand.nextInt(200)+450;
        utilValue = rand.nextInt(10);
        char n[] = new char[6];
        int j;
        for(j =0; j< 6; j++)
                n[j] = (char)(rand.nextInt(26)+97);
        utilClass =  new String(n);
    }
}
