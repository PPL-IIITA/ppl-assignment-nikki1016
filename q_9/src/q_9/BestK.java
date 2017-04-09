/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_9;

import java.io.BufferedWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;



/**
 *
 * Best_generic class for returning best k items 
 */
 public class BestK  {
    Random rand = new Random();
    /**
* Comparator to sort arrays of girls  as per attractiveness in descending order
*
*/
    
    public static class GirlComparator implements Comparator<girls>{
        @Override
        public int compare(girls o1, girls o2) {
            return o1.attractive < o2.attractive ? 1 :(o1.attractive > o2.attractive ? -1 : 0); //Descending
        }
    }
      /**
* Comparator to sort arrays of boys  as per budget in descending order
*
*/
    public static class BoyComparator implements Comparator<boys>{
        @Override
        public int compare(boys o1, boys o2) {
            return o1.budget < o2.budget ? 1 :(o1.budget > o2.budget ? -1 : 0); //Descending
        }
    }
      /**
* Comparator to sort arrays of gifts as per value
*
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
     * @return no of couples formed
     */
    int allocate( boys arr_boys[],  girls arr_girls[],couples couple[],BufferedWriter fp) {
        int m= rand.nextInt(arr_girls.length),k = 0;
         Best_generic <girls> generic_girls = new Best_generic<>();
        Best_generic<boys> generic_boys = new Best_generic<>();

        girls tempgirl[] = generic_girls.genericMethod(arr_girls, m);
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
                int t = available(arr_girls[i],tempboy);
                //System.out.println(" t " + t);
                if( t != -1){
                    couple[k] = new couples(arr_boys[t], arr_girls[i]);
                    arr_girls[i].bf = arr_boys[t];
                    arr_girls[i].status = true;
                    arr_boys[t].gf = arr_girls[i];
                    arr_boys[t].status = true;
                    System.out.println(arr_girls[i].gname +" committed to " +arr_boys[t].bname   );
                    fp.write("couples " +k+" : " + couple[k].girl.gname + " " + couple[k].boy.bname +" " + couple[k].timestamp);
                    System.out.println("couples " +k+" : " + couple[k].girl.gname + " " + couple[k].boy.bname +" " + couple[k].timestamp);
                    fp.newLine();                   
                    k++; 
                }   
            }
        }
        catch(Exception ee){
            ee.printStackTrace();
        }    
            
       return k;
    }
    
 
    /**
     *    check whether girl and boy are available to commit as per their status and requirements
     * @param girl
     * @param boy
     * @return 
     */
    int available(girls girl,boys boy[]){
        if(girl.status == true )
                return -1;
        int i,in=-1;
        int att = -2;
        if(girl.getClass().getSimpleName().equals("Choosy")){
            
            for(i=0;i < boy.length;i++){
                if(boy[i].attractive > att &&  boy[i].status == false){
                    att = boy[i].attractive;
                    in = i;
                }
            }
        }
        else if(girl.getClass().getSimpleName().equals("Normal")){
            for(i=0;i < boy.length;i++){
                if(boy[i].intelligence > att &&  boy[i].status == false){
                    att = boy[i].intelligence;
                    in = i;
                }
            }
        }
        else{
            for(i=0;i < boy.length;i++){
                if(boy[i].budget> att &&  boy[i].status == false){
                    att = boy[i].budget;
                    in = i;
                }
            }
        }
        return in;
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
    void allocate_gift(couples couple[],gifts gift[],int noOfCouple,int noOfGift,BufferedWriter fp){
        //sort(gift,noOfGift);
        int m= rand.nextInt(gift.length),k = 0;
        Best_generic <gifts> generic_gifts = new Best_generic<>();
        Arrays.sort(gift,new GiftComparator());

        gifts tempgift []= generic_gifts.genericMethod(gift, m);
        try{
            fp.write("Gift Exchanges are : " );
            fp.newLine();
        
            gifts temp;
            int i,j;
            for(i = 0; i < noOfCouple; i++){
                k=-1;
                if(couple[i].boy == null || couple[i].girl == null)     
                {
                    continue;
                }
                j= 0;

                Timestamp tt =new Timestamp(System.currentTimeMillis()); 
                k = available(gift,couple[i].girl.arr_gift);
                //System.out.println("byy");

                if(k==-1 || k>= gift.length) {
                   // System.out.println(" dd byy    ");
                    continue;
                }

                if(j == 0 && gift[k].price > (couple[i].boy.budget ) ){
                                    System.out.println("    byy   j0  " +k );

                    couple[i].boy.budget += gift[k].price;
                    couple[i].girl.arr_gift.add(gift[k]);
                    couple[i].girl.arr_time.add(tt);
                    couple[i].girl.tot_gift_value += gift[k].value;
                    couple[i].girl.tot_gift_amount += gift[k].price;
                    couple[i].boy.tot_gift_amount += gift[k].price;
                    fp.write(couple[i].boy.bname +" give gift"+ gift[k].id+ " of type "+ gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple[i].girl.gname+ " at " + tt);
                    fp.newLine();
                    System.out.println(j+ " "+ couple[i].boy.bname +" give gift"+ gift[k].id+ " of type "+ gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple[i].girl.gname+ " at " + tt);

                }
             //   System.out.println("    cyy  " +k );

                for(j = 1; j<noOfGift; j++){
                //if boy is geek then gift one additional luxury gift to his girlfriend
                    k = available(gift,couple[i].girl.arr_gift);
                    if(k==-1 || k>=gift.length || gift[k].price >= couple[i].boy.budget - couple[i].girl.tot_gift_amount )
                        break;
                    //System.out.println("    byy   kkk " +k );

                    if(couple[i].boy.getClass().getSimpleName().equals("geeks") || couple[i].boy.getClass().getSimpleName().equals("miser")){
                        if(couple[i].girl.tot_gift_amount >= couple[i].girl.maint_cost){
                            break;
                        }
                    }
                    temp  = gift[k];
                    couple[i].girl.arr_gift.add(gift[k]);
                    tt =new Timestamp(System.currentTimeMillis()); 
                    couple[i].girl.arr_time.add(tt);
                    couple[i].girl.tot_gift_value += temp.value;
                    couple[i].girl.tot_gift_amount += temp.price;
                    couple[i].boy.tot_gift_amount += temp.price;
                    fp.write(couple[i].boy.bname +" give gift"+gift[k].id +" of type "+gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple[i].girl.gname + " at " + tt );
                    fp.newLine();
                    System.out.println(couple[i].boy.bname +" give gift"+gift[k].id +" of type "+gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple[i].girl.gname + " at " + tt );
                }    
            }
    
        }
        catch(Exception e){                
        }
    } 
    
 }
