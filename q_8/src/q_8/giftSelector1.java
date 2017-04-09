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
 * GiftSelector1 class subclass of gift selector
 */
public class giftSelector1 extends GiftSelector {
    //create giftbasket for each couple if boys' budget allows
    void allocate_gift(couples couple[],gifts gift[],int noOfCouple,int noOfGift,BufferedWriter fp){
        obj.sort(gift,noOfGift);
        try{
            fp.write("Gift Exchanges are : " );
            fp.newLine();
        
            
            for(i = 0; i < noOfCouple; i++){
                if(couple[i].boy == null)     
                    break;
                j=0;
                tt =new Timestamp(System.currentTimeMillis()); 

                if(j == 0 && gift[j].price > (couple[i].boy.budget ) ){
                    couple[i].boy.budget += gift[j].price;
                    couple[i].girl.arr_gift.add(gift[j]);
                    couple[i].girl.arr_time.add(tt);
                    couple[i].girl.tot_gift_value = gift[j].value;
                    couple[i].girl.tot_gift_amount = gift[j].price;
                    couple[i].boy.tot_gift_amount = gift[j].price;
                    System.out.println(couple[i].boy.bname +" give gift"+ gift[j].id+ " of type "+ gift[j].getClass().getSimpleName() + " of price "+gift[j].price+ " to " +couple[i].girl.gname+ " at " + tt);
                    fp.write(couple[i].boy.bname +" give gift"+ gift[j].id+ " of type "+ gift[j].getClass().getSimpleName() + " of price "+gift[j].price+ " to " +couple[i].girl.gname+ " at " + tt);
                    fp.newLine();
                }
            
                for(j =1; j< noOfGift; j++){
                    int k=noOfGift;
            //if boy is geek then gift one additional luxury gift to his girlfriend
    
                    if(couple[i].boy.getClass().getSimpleName().equals("Geek") && j==1 && gift[j].price <= couple[i].boy.budget - couple[i].boy.tot_gift_amount){
                        k = obj.find(gift);
                        if(k==-1) continue;
                        tempgift  = gift[k];
                        couple[i].girl.arr_gift.add(gift[k]);
                        tt =new Timestamp(System.currentTimeMillis()); 
                        couple[i].girl.arr_time.add(tt);
                        couple[i].girl.tot_gift_value = tempgift.value;
                        couple[i].girl.tot_gift_amount = tempgift.price;
                        couple[i].boy.tot_gift_amount = tempgift.price;
                        System.out.println(couple[i].boy.bname +" give gift"+gift[k].id +" of type "+gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple[i].girl.gname + " at " + tt );
                        fp.write(couple[i].boy.bname +" give gift"+gift[k].id +" of type "+gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple[i].girl.gname + " at " + tt );
                        fp.newLine();
                    }
                    else if(couple[i].boy.getClass().getSimpleName().equals("geeks") || couple[i].boy.getClass().getSimpleName().equals("miser")){
                        if(couple[i].girl.tot_gift_amount > couple[i].girl.maint_cost){
                            break;
                        }
                    }
                    else if(gift[j].price <= (couple[i].boy.budget - couple[i].girl.tot_gift_amount) && j!=k ){
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
                    else break; 
                }
            }
            
        }
        catch(Exception e){
                        
        }
    }
}
