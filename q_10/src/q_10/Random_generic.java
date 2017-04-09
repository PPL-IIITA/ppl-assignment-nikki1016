/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_10;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * Random_generic class
 */
public class Random_generic <T>{
    Random rand = new Random();
    /**
     * genericMethod to select best k out of n 
     * @param <T>
     * @param obj
     * @param k
     * @return 
     */
    public  <T> T[] genericMethod(T[] obj,int k) {
        @SuppressWarnings("unchecked")
        T[] res = Arrays.copyOf((T[]) obj, k);
        return res;
    }
    /**
     * genericOne method to select randomly out of best k
     * @param <T>
     * @param obj
     * @param k
     * @return 
     */
    public <T> T genericOne(T[] obj,int k){
        @SuppressWarnings("unchecked")
        int i = rand.nextInt(k);
        T res = (T)obj[i];
        return res;
    }
}
