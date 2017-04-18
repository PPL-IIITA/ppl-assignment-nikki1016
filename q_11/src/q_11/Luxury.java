/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_11;

/**
 * luxury Gifts subclass of gifts
 * @see gifts
 * 
 */
public class Luxury extends gifts {
    /**
     * Luxury type of gifts class containing :
    * @param price  the price of gift;
    * @param value the value of gift
    * @param luxRating luxury rating
    * @param difficultyObtain difficulty to Obtain;
    * @param id unique id of gift
    * @see gifts
    */
     int luxRating;
     int difficultyObtain;
    /**
     * constructor() of luxury gifts
     * @param gfno for unique gift id 
     */    
     public Luxury(int gfno){
        super(gfno);
        value = rand.nextInt(100)+400;
        price = rand.nextInt(200)+600;
        luxRating = rand.nextInt(10);
        difficultyObtain = rand.nextInt(10); 
    }
}

