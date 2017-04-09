package q_10;

import java.io.*;
import java.util.*;
/**
 * 
 * contains all methods that are generally used
 */
public class utility {
    Random rand = new Random();
    
  
    /**
     * generate gifts randomly
     * @param noOfGifts
     * @param arr_gift
     * @param bw3 
     */
    void generate_gift(int noOfGifts, gifts arr_gift[],BufferedWriter bw3){
        try{
            int i,j;
            for(i = 0; i< noOfGifts; i++){
                j = rand.nextInt(3);
               switch(j){
                    case 0:
                        arr_gift[i] = new Essential(i);
                        break;
                    case 1:
                        arr_gift[i] = new Luxury(i);
                        break;
                    case 2:
                        arr_gift[i] = new Utility(i);
                        break;
                }           
           
            }
            bw3.write("------Gifts-------");
            bw3.newLine();
            bw3.write("giftid price value ");
            bw3.newLine();
            String br;
            for(i = 0; i< noOfGifts; i++){
                br = new String(arr_gift[i].id + " " +arr_gift[i].getClass().getSimpleName()+" "+ arr_gift[i].price+" "+arr_gift[i].value);
                bw3.write(br);
                bw3.newLine();
            }
        }
        catch(IOException e){
            
        }
        
    }
    
    
    
    /**
     * calculate happiness and compatibility of all couples
     * @param couple
     * @param noOfCouple 
     */
    void calculate_happiness_comp(couples couple[],int noOfCouple){
        int i;
        for(i =0; i< noOfCouple; i++){
            couple[i].happiness();
            couple[i].compatibility();
        }
    }
  
    /**
     * find k best happiest couples
     * @param couple
     * @param noOfCouple
     * @param k 
     */
    void bestKHappiestCouple(couples couple[],int noOfCouple,int k){
        int i,j;
        couples temp ;
        System.out.println("Best "+ k + " happiest couple --");
        //sort couples as per ther happiness in descending order
        for(i=0; i < noOfCouple -1; i++){
            for(j= i+1; j< noOfCouple ; j++){
                if(couple[i] != null && couple[i].happiness < couple[j].happiness){
                    temp = couple[i];
                    couple[i] = couple[j];
                    couple[j] = temp;
                }
            }
        }
        for(i =0; i< k; i++){
            
            if(couple[i] == null ){
                //System.out.println("byy");
                if(k < noOfCouple-1)
                    k++;
                else continue;
            }
            else           
                System.out.println("Couple: "+couple[i].boy.bname + " and " + couple[i].girl.gname);
        }
    }
    /**
     * find k best compatible couples
     * @param couple
     * @param noOfCouple
     * @param k 
     */
    void bestKCompatibleCouple(couples couple[],int noOfCouple,int k){
        int i,j;
        System.out.println("Best "+ k + " compatible couple --");
        //sort couples as per ther compatible in descending order
        couples temp ;
        for(i=0; i < noOfCouple -1; i++){
            for(j= i+1; j< noOfCouple ; j++){
                if(couple[i].compatability < couple[j].compatability){
                    temp = couple[i];
                    couple[i] = couple[j];
                    couple[j] = temp;
                }
            }
        }
        for(i =0; i<k;i ++){
         if(couple[i] == null ){
                if(k < noOfCouple-1)
                    k++;
                else continue;
            }
            else   
            System.out.println("Couple: "+couple[i].boy.bname + " and " + couple[i].girl.gname);
        }
    }
}