/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_8;

import java.io.BufferedWriter;
import java.sql.Timestamp;
/**
 *
 * GiftSelector2 class subclass of gift selector
 */
public class giftSelector2 extends GiftSelector {
    //sort all the gifts as per theri increasing price
    void sort(gifts gift[],int no_gift){
        int i,j;
       // System.out.println(gift.length);
        gifts temp;
        for(i=0; i < gift.length -1; i++){
            for(j= i+1; j< gift.length ; j++){
                if(gift[i].price > gift[j].price){
                    temp = gift[i];
                    gift[i] = gift[j];
                    gift[j] = temp;
                }
            }
        }
        
    }
    //find luxury gift
    int find(gifts gift[],int j){
        int i,min = 99999,min_in=-1;
        for(i = 0 ; i< gift.length;i++){
            if(j == 1 && gift[i].getClass().getSimpleName().equals("Luxury")  && min > gift[i].price){
                min_in = i;
                min  = gift[i].price;
            }
            else if(j == 0 && gift[i].getClass().getSimpleName().equals("Essential") && min > gift[i].price){
                min_in = i;
                min  = gift[i].price;
            }
            else if(j == 2 && gift[i].getClass().getSimpleName().equals("Utility") && min > gift[i].price){
                min_in = i;
                min  = gift[i].price;
            }
        }
        return min_in;
    }
    void allocate_gift(couples couple[],gifts gift[],int noOfCouple,int noOfGift,BufferedWriter fp){
        sort(gift,noOfGift);
        int a,b,c;
        a = find(gift,0);
        b = find(gift,1);
        c = find(gift,2);
       // System.out.println("Gift " + a + " " + b + " " + c );
       if(a ==-1 || b==-1 || c==-1) return;
        try{
            System.out.println("Gift Exchanges by second method are : " );
            fp.write("Gift Exchanges by second method are : " );
            fp.newLine();
            for(i = 0; i < noOfCouple; i++){
                if(couple[i].boy == null)  {   
                    System.out.println("null" );
                    continue;
                }
                tt = new Timestamp(System.currentTimeMillis()); 
                if(gift[a].price + gift[b].price+ gift[c].price > (couple[i].boy.budget ) ){
                    couple[i].boy.budget = gift[a].price + gift[b].price+ gift[c].price;
                }
                couple[i].girl.arr_gift.add(gift[a]);
                couple[i].girl.arr_gift.add(gift[b]);
                couple[i].girl.arr_gift.add(gift[c]);
                couple[i].girl.arr_time.add(tt);
                couple[i].girl.arr_time.add(tt);
                couple[i].girl.arr_time.add(tt);
                couple[i].girl.tot_gift_value = gift[a].value + gift[b].value + gift[c].value;
                couple[i].girl.tot_gift_amount = gift[a].price + gift[b].price+ gift[c].price ;
                couple[i].boy.tot_gift_amount = gift[a].price + gift[b].price+ gift[c].price ;
                System.out.println(couple[i].boy.bname +" give gift"+ gift[a].id+ " of type "+ gift[a].getClass().getSimpleName() + " of price "+gift[a].price+ " to " +couple[i].girl.gname+ " at " + tt);
                fp.write(couple[i].boy.bname +" give gift"+ gift[a].id+ " of type "+ gift[a].getClass().getSimpleName() + " of price "+gift[a].price+ " to " +couple[i].girl.gname+ " at " + tt);
                fp.newLine();
                System.out.println(couple[i].boy.bname +" give gift"+ gift[b].id+ " of type "+ gift[b].getClass().getSimpleName() + " of price "+gift[b].price+ " to " +couple[i].girl.gname+ " at " + tt);
                fp.write(couple[i].boy.bname +" give gift"+ gift[b].id+ " of type "+ gift[b].getClass().getSimpleName() + " of price "+gift[b].price+ " to " +couple[i].girl.gname+ " at " + tt);
                fp.newLine();
                System.out.println(couple[i].boy.bname +" give gift"+ gift[c].id+ " of type "+ gift[c].getClass().getSimpleName() + " of price "+gift[c].price+ " to " +couple[i].girl.gname+ " at " + tt);
                fp.write(couple[i].boy.bname +" give gift"+ gift[c].id+ " of type "+ gift[c].getClass().getSimpleName() + " of price "+gift[c].price+ " to " +couple[i].girl.gname+ " at " + tt);
                fp.newLine();
                
                for(j =0; j< noOfGift; j++){
            //if boy is geek then gift one additional luxury gift to his girlfriend
                    if(j == a || j == b || j == c)continue;
                    if(couple[i].boy.getClass().getSimpleName().equals("geeks") || couple[i].boy.getClass().getSimpleName().equals("miser")){
                        if(couple[i].girl.tot_gift_amount >= couple[i].girl.maint_cost){
                            break;
                        }  
                        else {
                            couple[i].girl.arr_gift.add(gift[j]);
                            tt =new Timestamp(System.currentTimeMillis()); 
                            couple[i].girl.arr_time.add(tt);
                            couple[i].girl.tot_gift_value += gift[j].value;
                            couple[i].girl.tot_gift_amount += gift[j].price;
                            couple[i].boy.tot_gift_amount += gift[j].price;
                            System.out.println(couple[i].boy.bname +" give gift"+gift[j].id +" of type "+gift[j].getClass().getSimpleName() +  " of price "+gift[j].price+ " to " +couple[i].girl.gname+ " at " + tt);
                            fp.write(couple[i].boy.bname +" give gift"+gift[j].id +" of type "+gift[j].getClass().getSimpleName() +  " of price "+gift[j].price+ " to " +couple[i].girl.gname+ " at " + tt);
                            fp.newLine();           
                        }
                    }
                    else if(gift[j].price <= (couple[i].boy.budget - couple[i].girl.tot_gift_amount) ){
                        couple[i].girl.arr_gift.add(gift[j]);
                        tt =new Timestamp(System.currentTimeMillis()); 
                        couple[i].girl.arr_time.add(tt);
                        couple[i].girl.tot_gift_value += gift[j].value;
                        couple[i].girl.tot_gift_amount += gift[j].price;
                        couple[i].boy.tot_gift_amount += gift[j].price;
                        System.out.println(couple[i].boy.bname +" give gift"+gift[j].id +" of type "+gift[j].getClass().getSimpleName() +  " of price "+gift[j].price+ " to " +couple[i].girl.gname+ " at " + tt);
                        fp.write(couple[i].boy.bname +" give gift"+gift[j].id +" of type "+gift[j].getClass().getSimpleName() +  " of price "+gift[j].price+ " to " +couple[i].girl.gname+ " at " + tt);
                        fp.newLine();           
                    }
                    //else break; 
                }
            }
        }
        catch(Exception e){
                        
        }
    }
}
