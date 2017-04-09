/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_9;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * Best_generic class for returning best k items 
 */
public class Best_generic <T>{
    Random rand = new Random();
    /**
     * returning best k items 
     * @param <T>
     * @param obj
     * @param k
     * @return T[]
     */
    public  <T> T[] genericMethod(T[] obj,int k) {
        @SuppressWarnings("unchecked")
        T[] res = Arrays.copyOf((T[]) obj, k);
        return res;
    }
}
