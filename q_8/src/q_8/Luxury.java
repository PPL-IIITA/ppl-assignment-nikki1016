/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_8;

/**
 *
 * 
 * luxury Gifts subclass of gifts
 * @see gifts
 * 
 */
public class Luxury extends gifts {
    /**
    * @see gifts
     * luxury Gifts class containing :
    * @param price  the price of gift;
    * @param value the value of gift
    * @param luxRating luxury rating
    * @param difficultyObtain difficulty to Obtain;
    * @param id unique id of gift
    */
     int luxRating;
     int difficultyObtain;
    /**
     * constructor
     * @param gfno for unique gift id 
     */    public Luxury(int gfno){
        super(gfno);
        value = rand.nextInt(100)+400;
        price = rand.nextInt(200)+600;
        luxRating = rand.nextInt(10);
        difficultyObtain = rand.nextInt(10); 
    }
}

