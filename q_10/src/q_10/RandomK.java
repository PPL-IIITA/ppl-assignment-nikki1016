/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_10;

import java.io.BufferedWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
/**
 * 
 * Random_k class
 */
 public class RandomK  {
    Random rand = new Random();

        /**
        * Comparator to sort arrays of girls as per attractiveness and status in descending order
        
        */
    public static class GirlComparator implements Comparator<girls>{
        @Override
        public int compare(girls o1, girls o2) {
            if(o1.attractive == o2.attractive && o1.status == false && o2.status == false){
                return 0; //Descending
            }
             else if(o2.status== true && o1.status == false){
                return -1 ;
            }
             else if(o1.status== true && o2.status == false){
                return 1 ;
            }
            if(o1.attractive < o2.attractive && o2.status == false){
                return 1 ;
                 //Descending
            }
            else if(o1.attractive < o2.attractive && o1.status == false && o2.status==true){
                return -1 ;
                 //Descending
            }
            else if(o1.attractive > o2.attractive && o1.status == false){
                return -1 ;
            }
            else if(o1.attractive > o2.attractive && o2.status == false){
                return -1 ;
            }
          return 0;  
        }
    }
     /**
        * Comparator to sort arrays of  boys as per budget in descending order
        
        */
    public static class BoyComparator implements Comparator<boys>{
        @Override
        public int compare(boys o1, boys o2) {
            if(o1.budget == o2.budget && o1.status == false && o2.status == false){
                return 0; //Descending
            }
             else if(o2.status== true && o1.status == false){
                return -1 ;
            }
             else if(o1.status== true && o2.status == false){
                return 1 ;
            }
            if(o1.budget < o2.budget && o2.status == false){
                return 1 ;
                 //Descending
            }
            else if(o1.budget < o2.budget && o1.status == false && o2.status==true){
                return -1 ;
                 //Descending
            }
            else if(o1.budget > o2.budget && o1.status == false){
                return -1 ;
            }
            else if(o1.budget > o2.budget && o2.status == false){
                return -1 ;
            }
          return 0;  
         }
    }
     /**
        * Comparator to sort arrays of  gifts as their value in descending order
        
        */
    public static class GiftComparator implements Comparator<gifts>{
        @Override
        public int compare(gifts o1, gifts o2) {
            return o1.value < o2.value ? 1 :(o1.value > o2.value ? -1 : 0); //Descending
        }
    }
    
    /**
     * allocate boyfriends to girlfriends as per their requirements
     * @param arr_boys
     * @param arr_girls
     * @param couple
     * @param fp
     * @return no of couples
     */
    
    int allocate_rand( boys arr_boys[],  girls arr_girls[],couples couple[],BufferedWriter fp) {
        /**
         * @param generic_girls to instantiate object of Random_generic class whose generic variable is girls
        * @param generic_boys to instantiate object of Random_generic class whose generic variable is boys
        *@param generic_gifts to instantiate object of Random_generic class whose generic variable is gifts
        * sort each of object of girls,boys and gifts using comparator 
        * 
         */
        int m= rand.nextInt(arr_girls.length)+3,k = 0;
        Random_generic <girls> generic_girls = new Random_generic<>();
        Random_generic<boys> generic_boys = new Random_generic<>();

        boys tempboy [] = generic_boys.genericMethod(arr_boys, m);
        Arrays.sort(arr_boys,new BoyComparator());
        Arrays.sort(arr_girls,new GirlComparator());
        
        int i,j;
        
        try{
            fp.write("Couples are: ");

            fp.newLine();
            for(i=0; i<arr_girls.length; i++){
                Arrays.sort(arr_boys,new BoyComparator());
                tempboy = generic_boys.genericMethod(arr_boys, m);
                boys boy = generic_boys.genericOne(tempboy, m);

                /* check whether randomnly selected boy is available to commit as per their status and requirements*/
                if(boy.status == false){
                    int t = find(arr_boys,boy);
                    couple[k] = new couples(arr_boys[t], arr_girls[i]);
                    arr_girls[i].bf = arr_boys[t];
                    arr_girls[i].status = true;
                    arr_boys[t].gf = arr_girls[i];
                    arr_boys[t].status = true;
                    //System.out.println(arr_girls[i].gname +" committed to " +arr_boys[t].bname   );
                    fp.write("couples " +k+" : " + couple[k].girl.gname + " " + couple[k].boy.bname +" " + couple[k].timestamp);
                    System.out.println("couples " +k+" : " + couple[k].girl.gname + " " + couple[k].boy.bname +" " + couple[k].timestamp);
                    fp.newLine();                   
                    k++; 
                }   
            }
        }
        
            
        catch(Exception ee){
        }    
            
       return k;
    }
    
 
    
    
    /**
     * check whether girl and boy are available to commit as per their status and requirements
     * @param gift
     * @param aa
     * @return 
     */
    int available(gifts gift[],ArrayList<gifts> aa){
        int i,in=-1;
        int att = 99999;
        for(i=0;i < gift.length;i++){
            
            if(att > gift[i].price && !aa.contains(gift[i])){
               // System.out.println(att +"   aa.contains(gift[i]) "+ aa.contains(gift[i]));
                att = gift[i].price;
                in = i;
            }
        }
        return in;
    }  
   
    
    /**
     * create gift basket for each couple if boy's budget allows
     * @param couple
     * @param gift
     * @param noOfCouple
     * @param noOfGift
     * @param fp 
     */
    void allocate_gift_random(couples couple[],gifts gift[],int noOfCouple,int noOfGift,BufferedWriter fp){
        //sort(gift,noOfGift);
        int m= rand.nextInt(gift.length-3)+3,k = 0;
        Random_generic <gifts> generic_gifts = new Random_generic<>();

        gifts tempgift []= generic_gifts.genericMethod(gift, m);
        Arrays.sort(gift,new GiftComparator());
        int i;
        try{
            fp.write("Gift Exchanges are : " );
            fp.newLine();
            System.out.println("Gift ----");
            int j;
            for(i = 0; i < noOfCouple; i++){
                k=-1;
                if(couple[i].boy == null || couple[i].girl == null)  {
                    continue;
                }
                gifts gf = generic_gifts.genericOne(tempgift,m);
                int t= Math.abs(rand.nextInt(m));
                Timestamp tt =new Timestamp(System.currentTimeMillis()); 
               // System.out.println(couple[i].girl.arr_gift.contains(gf));
                if( gf.price > (couple[i].boy.budget )  && !couple[i].girl.arr_gift.contains(gf)){
                    couple[i].boy.budget += gf.price;
                    couple[i].girl.arr_gift.add(gf);
                    couple[i].girl.arr_time.add(tt);
                    couple[i].girl.tot_gift_value += gf.value;
                    couple[i].girl.tot_gift_amount += gf.price;
                    couple[i].boy.tot_gift_amount += gf.price;
                    fp.write(couple[i].boy.bname +" give gift"+ gf.id+ " of type "+ gf.getClass().getSimpleName() + " of price "+gf.price+ " to " +couple[i].girl.gname+ " at " + tt);
                    fp.newLine();
                    System.out.println(couple[i].boy.bname +" give gift"+ gift[k].id+ " of type "+ gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple[i].girl.gname+ " at " + tt);                
                }
                gf = generic_gifts.genericOne(gift,m);
               
                while(! gf.getClass().getSimpleName().equals("Luxury")){
                    gf = generic_gifts.genericOne(gift,m);

                }
                if(couple[i].boy.getClass().getSimpleName().equals("Geeks")  && !couple[i].girl.arr_gift.contains(gf) && !couple[i].girl.arr_gift.get(0).getClass().getSimpleName().equals("Luxury")){
                    couple[i].boy.budget += gf.price;
                    couple[i].girl.arr_gift.add(gf);
                    couple[i].girl.arr_time.add(tt);
                    couple[i].girl.tot_gift_value += gf.value;
                    couple[i].girl.tot_gift_amount += gf.price;
                    couple[i].boy.tot_gift_amount += gf.price;
                    fp.write(couple[i].boy.bname +" give gift"+ gf.id+ " of type "+ gf.getClass().getSimpleName() + " of price "+gf.price+ " to " +couple[i].girl.gname+ " at " + tt);
                    fp.newLine();
                    System.out.println(couple[i].boy.bname +" give gift"+ gift[k].id+ " of type "+ gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple[i].girl.gname+ " at " + tt);                
                }
                for(j = 1; j<noOfGift; j++){
            //if boy is geek then gift one additional luxury gift to his girlfriend
                    gf = generic_gifts.genericOne(gift,m);

                    //gf = genericOne(gift,m);
                    if( gf.price >= couple[i].boy.budget - couple[i].girl.tot_gift_amount )
                        break;

                    if(couple[i].boy.getClass().getSimpleName().equals("geeks") || couple[i].boy.getClass().getSimpleName().equals("miser")){
                        if(couple[i].girl.tot_gift_amount > couple[i].girl.maint_cost){
                            break;
                        }
                    }
                    couple[i].girl.arr_gift.add(gf);
                    tt =new Timestamp(System.currentTimeMillis()); 
                    couple[i].girl.arr_time.add(tt);
                    couple[i].girl.tot_gift_value += gf.value;
                    couple[i].girl.tot_gift_amount += gf.price;
                    couple[i].boy.tot_gift_amount += gf.price;
                    fp.write(couple[i].boy.bname +" give gift"+gf.id +" of type "+gf.getClass().getSimpleName() + " of price "+gf.price+ " to " +couple[i].girl.gname + " at " + tt );
                    fp.newLine();
                    System.out.println(couple[i].boy.bname +" give gift"+gf.id +" of type "+gf.getClass().getSimpleName() + " of price "+gf.price+ " to " +couple[i].girl.gname + " at " + tt );
                }    
        }
    
        }
        catch(Exception e){                
        }
    } 
    
 
    
    /**to find a particular boy that is randomnly choosen from file 
     * 
     * @param arr_boys
     * @param boy
     * @return i index where that particular boy is found in the collection
     */
       int find(boys arr_boys[],boys boy){
           int i;
           for(i = 0; i<arr_boys.length; i++){
               if(arr_boys[i] == boy)
                   return i;
           }
           return -1;
       }
 }
