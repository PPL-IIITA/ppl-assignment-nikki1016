/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_11;

import java.util.Random;

/**
* abstract Gifts class 
 * @see gifts

 */
abstract public class gifts {
    /**
     * abstract gifts class as there is no need to instantiate it 
     * Gifts class containing :
    * @param price  the price of gift;
    * @param value the value of gift
    * @param id unique id of gift
    * @see gifts
    */
    Random rand = new Random();
    public int value;
    int price;
    public int id;
   /**
    * constructor() of super class gifts
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