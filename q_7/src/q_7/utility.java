package q_7;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
/**
 * 
 * contains all methods that are generally used
 */
public class utility {
    Random rand = new Random();
    /**
     * Allocate function allocate boyfriends to girlfriends as per their requirements
     * @param arr_boys collection of boys
     * @param arr_girls collection of girls
     * @param couple collection of couples
     * @param fp file pointer
     * @return 
     */
    int allocate( boys arr_boys[],  girls arr_girls[],couples couple[],BufferedWriter fp) {
        int i,k=0;
        try{
            //write in log file about committments 
            fp.write("Couples are: ");
            System.out.println("Couples are   "  );
            fp.newLine();
            for(i=0; i<arr_girls.length; i++){
                int m = available(arr_girls[i],arr_boys);
                // check which boy is suitable for ith girl as per requirements 
                if(m != -1){
                        couple[k] = new couples(arr_boys[m], arr_girls[i]);
                        //update boy and girl status who get committed
                        arr_girls[i].bf = arr_boys[m];
                        arr_girls[i].status = true;
                        arr_boys[m].gf = arr_girls[i];
                        arr_boys[m].status = true;
                        System.out.println(arr_girls[i].gname +" committed to " +arr_boys[m].bname  );
                        fp.write("couples " +k+" : " + couple[k].girl.gname + " " + couple[k].boy.bname +" " + couple[k].timestamp);
                        fp.newLine();
                        k++;
                       
                }   
            }
        }
        catch(IOException ee){
                ee.printStackTrace();
        }    
        return k;
        //return how many couples formed
    }
    
    /**
     * Available function check whether girl and boy are available to commit as per their status and requirements 
     * @param girl
     * @param boy
     * @return 
     */
    int available(girls girl,boys boy[]){
        
        if(girl.status == true )
                return -1;
        int i,in=-1;
        int att = -9999;
        
       // if girl is Choosy then select most attractive boy 
        if(girl.getClass().getSimpleName().equals("Choosy")){
            for(i=0;i < boy.length;i++){
                if(boy[i].attractive > att &&  girl.maint_cost < boy[i].budget && boy[i].min_attractive_req < girl.attractive && boy[i].status == false){
                    att = boy[i].attractive;
                    in = i;
                }
            }
        }
       // if girl is Normal then select most intelligent boy 
        else if(girl.getClass().getSimpleName().equals("Normal")){
            for(i=0;i < boy.length;i++){
                if(boy[i].intelligence > att &&  girl.maint_cost < boy[i].budget && boy[i].min_attractive_req < girl.attractive  && boy[i].status == false){
                    att = boy[i].intelligence;
                    in = i;
                }
            }
        }
        // if girl is Desparate then select most rich boy
        else if(girl.getClass().getSimpleName().equals("Desparate")){
            for(i=0;i < boy.length;i++){
                if(boy[i].budget> att &&  girl.maint_cost < boy[i].budget && boy[i].min_attractive_req < girl.attractive && girl.status == false && boy[i].status == false){
                    att = boy[i].budget;
                    in = i;
                }
            }
        }
        return in;
    }
   
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
     *create gift basket for each couple if boy's budget allows 
     * checking for each couple formed yet and allocate gifts to their girlfriends
     * @param couple
     * @param gift
     * @param noOfCouple
     * @param noOfGift
     * @param fp 
     */
    void allocate_gift(couples couple[],gifts gift[],int noOfCouple,int noOfGift,BufferedWriter fp){
        sort(gift,noOfGift);
        try{
            fp.write("Gift Exchanges are : " );
            fp.newLine();
            gifts tempgift;
            int i,j;
            
            for(i = 0; i < noOfCouple; i++){
                if(couple[i].boy == null)     
                    continue;
                j=0;
                Timestamp tt =new Timestamp(System.currentTimeMillis()); 

                if(j == 0 && gift[j].price > (couple[i].boy.budget ) ){
                    //add gift to their basket
                    couple[i].boy.budget += gift[j].price;
                    couple[i].girl.arr_gift.add(gift[j]);
                    couple[i].girl.arr_time.add(tt);
                    couple[i].girl.tot_gift_value = gift[j].value;
                    couple[i].girl.tot_gift_amount = gift[j].price;
                    couple[i].boy.tot_gift_amount = gift[j].price;
                    fp.write(couple[i].boy.bname +" give gift"+ gift[j].id+ " of type "+ gift[j].getClass().getSimpleName() + " of price "+gift[j].price+ " to " +couple[i].girl.gname+ " at " + tt);
                    fp.newLine();
                    System.out.println(couple[i].boy.bname +" give gift"+ gift[j].id+ " of type "+ gift[j].getClass().getSimpleName() + " of price "+gift[j].price+ " to " +couple[i].girl.gname+ " at " + tt);                    
                }
                for(j =1; j< noOfGift; j++){
                    int k=noOfGift;
                //if boy is geek then gift one additional luxury gift to his girlfriend

                    if(couple[i].boy.getClass().getSimpleName().equals("Geek") && j==1 && gift[j].price <= couple[i].boy.budget - couple[i].boy.tot_gift_amount){
                        //add gift to their basket  
                        k = find(gift);
                        if(k==-1) continue;
                        tempgift  = gift[k];
                        couple[i].girl.arr_gift.add(gift[k]);
                        tt =new Timestamp(System.currentTimeMillis()); 
                        couple[i].girl.arr_time.add(tt);
                        couple[i].girl.tot_gift_value = tempgift.value;
                        couple[i].girl.tot_gift_amount = tempgift.price;
                        couple[i].boy.tot_gift_amount = tempgift.price;
                        fp.write(couple[i].boy.bname +" give gift"+gift[k].id +" of type "+gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple[i].girl.gname + " at " + tt );
                        fp.newLine();
                        System.out.println(couple[i].boy.bname +" give gift"+gift[k].id +" of type "+gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple[i].girl.gname + " at " + tt );

                    }
                    else if(couple[i].boy.getClass().getSimpleName().equals("geeks") || couple[i].boy.getClass().getSimpleName().equals("miser")){
                        if(couple[i].girl.tot_gift_amount > couple[i].girl.maint_cost){
                            break;
                        }
                        //if cannot gift anymore gifts to their girlfriends, go to next couple and allocate gifts in their baskets
                    }
                    else if(gift[j].price <= (couple[i].boy.budget - couple[i].girl.tot_gift_amount) && j!=k ){
                        //add gift to their basket
                        couple[i].girl.arr_gift.add(gift[j]);
                        tt =new Timestamp(System.currentTimeMillis()); 
                        couple[i].girl.arr_time.add(tt);
                        couple[i].girl.tot_gift_value += gift[j].value;
                        couple[i].girl.tot_gift_amount += gift[j].price;
                        couple[i].boy.tot_gift_amount += gift[j].price;
                        fp.write(couple[i].boy.bname +" give gift"+gift[j].id +" of type "+gift[j].getClass().getSimpleName() +  "of price "+gift[j].price+ " to " +couple[i].girl.gname+ " at " + tt);
                        fp.newLine();
                        System.out.println(couple[i].boy.bname +" give gift"+gift[j].id +" of type "+gift[j].getClass().getSimpleName() +  "of price "+gift[j].price+ " to " +couple[i].girl.gname+ " at " + tt);

                    }
                    else break;
                }
            }
            
        }
        catch(Exception e){
                   
        }
    }
    
        
    /**
     * sort all the gifts as per their increasing price
     * @param gift
     * @param no_gift 
     */
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
   
    /**
     * find luxury gift that is not in gift basket of that couple
     * @param gift
     * @return i index where luxury gift is found in collection
     */
    int find(gifts gift[]){
        int i;
        for(i = 0 ; i< gift.length;i++){
            if(gift[i].getClass().equals("Luxury")){
                return i;
            }
        }
        return -1;
    }
}
