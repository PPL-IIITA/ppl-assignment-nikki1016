/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_3;

import java.util.Random;

/**
 *
     * abstract Gifts class 
 * @see gifts

 */


abstract public class gifts {
    /**
      * @see gifts
     * Gifts class containing :
    * @param price  the price of gift;
    * @param value the value of gift
    * @param id unique id of gift
    * abstract gifts class as there is no need to instantiate it 
    */
    Random rand = new Random();
    public int value;
    int price;
    int id;
   /**
    * constructor
    * @param gfno for unique gift id
    */
    public gifts(int gfno){
        id = gfno;
    }
    int getValue(){
        return value;
    }
    int getPrice(){
        return price;
    }
}