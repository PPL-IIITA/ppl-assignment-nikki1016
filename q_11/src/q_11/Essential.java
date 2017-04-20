/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_11;

/**
 * Essential gifts subclass gifts
 * @see gifts
 */
public class Essential extends gifts{
    /**
     * essentialGifts class containing :
    * @param price  the price of gift;
    * @param value the value of gift
    * @param id unique id of gift
    * @see gifts
    */
    
    /**
     * constructor() of essential gift class
     * @param gfno for unique gift id 
     */
     
    public Essential(int gfno){
        super(gfno);
        value = rand.nextInt(100)+100;
        price = rand.nextInt(200)+150;
    }
}