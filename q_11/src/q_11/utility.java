package q_11;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import IllegealException.*;
/**
 * 
 * utility class contains all methods that are generally used
 * @author nikita
 */
public class utility {
    Random rand = new Random();
    /**
        * Comparator to sort collections of couples as per their compatability in descending order
        * @see couples
        */
    public static class CoupleCompComparator implements Comparator<couples>{
        @Override
        public int compare(couples o1, couples o2) {
            if(o1 == null || o2 == null)
                return 0;
            return o1.compatability < o2.compatability ? 1 :(o1.compatability > o2.compatability ? -1 : 0); //Descending
        }
    }
    /**
        * Comparator to sort collections of couples as per their Happiness in descending order
        * @see couples
        */
    public static class CoupleHappyComparator implements Comparator<couples>{
        @Override
        public int compare(couples o1, couples o2) {
            if(o1 == null || o2 == null)
                return 0;
            return o1.happiness < o2.happiness ? 1 :(o1.happiness > o2.happiness ? -1 : 0); //Descending
        }
    }
    /**
        * Comparator to sort arrays of  gifts as their price in descending order
        * @see gifts
        */
    public static class GiftComparator implements Comparator<gifts>{
        @Override
        public int compare(gifts o1, gifts o2) {
            if(o1 == null || o2 == null)
                return 0;
            return o1.price < o2.price ? 1 :(o1.price > o2.price ? -1 : 0); //Descending
        }
    }
    
    /**
     * this method Allocate function allocate boyfriends to girlfriends as per their requirements
     *  check which boy is suitable for each girl as per requirements
     *  update boy and girl status who get committed
     *  write in log file about commitments 
     * @param arr_boys collection of boys
     * @param arr_girls collection of girls
     * @param couple collection of couples
     * @param fp file pointer
     * @return k no of couples formed
     * @throws NotFoundException if no boy is found for girl
     */
    int allocate( boys arr_boys[],  girls arr_girls[],couples couple[],BufferedWriter fp) {
        int i,k=0;
        try{
            fp.write("Couples are: ");
            System.out.println("Couples are   "  );
            fp.newLine();
            for(i=0; i<arr_girls.length; i++){
                try{
                    if(arr_girls[i]==null){
                        throw new NotFoundException(arr_girls[i]);
                    }
                    int m = arr_girls[i].available(arr_boys);
                    try{
                        if(m == -1){
                            throw new AllocationException(arr_girls[i]);
                        }
                        if(m > -1){
                            couple[k] = new couples(arr_boys[m], arr_girls[i]);
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
                    catch(AllocationException ee){
                        Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);
                    }
                     
                }
                catch(NotFoundException ee){
                    Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

                }  
            }
        }
        catch(IOException ee){
                //ee.printStackTrace();
                Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

        }  
        System.out.println("Total couples formed : "+k);
        return k;
        //return how many couples formed
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
        Arrays.sort(gift, new GiftComparator());
        try{
            fp.write("Gift Exchanges are : " );
            fp.newLine();
            gifts tempgift = null;
            int i,j;
            
            for(i = 0; i < noOfCouple; i++){
                try{
                    if(couple[i].boy == null)     
                        throw new NotFoundException(couple[i].boy);
                    j=0;
                    Timestamp tt =new Timestamp(System.currentTimeMillis()); 

                    if(j == 0 && gift[j] != null && gift[j].price > (couple[i].boy.budget ) ){
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
                        if(gift[j] == null)
                            continue;
                        int k=noOfGift;
                    //if boy is geek then gift one additional luxury gift to his girlfriend
                        try {
                            if(couple[i] != null && couple[i].boy.getClass().getSimpleName().equals("Geek") && j==1 && gift[j].price <= couple[i].boy.budget - couple[i].boy.tot_gift_amount){
                                //add gift to their basket  
                                k = find(gift);
                                if(k==-1) 
                                    throw new GiftAllocationException(couple[i],1);
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
                            else if(gift[j] != null && gift[j].price <= (couple[i].boy.budget - couple[i].girl.tot_gift_amount) && j!=k ){
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
                            else {
                                throw new GiftAllocationException(couple[i]);
                            }
                        }
                        catch(GiftAllocationException ee){
                            j = noOfGift;
                            Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

                        }
                    }

                }
                catch(NotFoundException ee){
                    Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

                }
            }
            
        }
        catch(Exception ee){
            Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);
             ee.printStackTrace();
        }
    }
    
    
    
    /**
     *calculate happiness and compatibility of all couples 
     * @param couple
     * @param noOfCouple 
     */
    void calculate_happiness_comp(couples couple[],int noOfCouple){
        int i;
        for(i =0; i< noOfCouple; i++){
            if(couple[i] == null) continue;
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
        try{
            if(k > noOfCouple)
                throw new OutOfboundException(k,noOfCouple);
        
            int i,j;
            System.out.println("Best "+ k + " happiest couple --");
            Arrays.sort(couple,new CoupleHappyComparator() );
            for(i =0; i< k; i++){

                if(couple[i] == null ){
                    //System.out.println("byy");
                    if(k < noOfCouple-1)
                        k++;
                }
                else           
                    System.out.println("Couple: "+couple[i].boy.bname + " and " + couple[i].girl.gname);
            }
        }
        catch(OutOfboundException ee){
            Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

        }
    }
    /**
     * find k best compatible couples
     * @param couple
     * @param noOfCouple
     * @param k 
     */
    void bestKCompatibleCouple(couples couple[],int noOfCouple,int k){
        
        try{
            if(k > noOfCouple)
                throw new OutOfboundException(k,noOfCouple);
        
            int i,j;
            System.out.println("Best "+ k + " compatible couple --");
            //sort couples as per ther compatible in descending order
            Arrays.sort(couple,new CoupleCompComparator() );
            for(i =0; i<k;i ++){
             if(couple[i] == null ){
                    if(k < noOfCouple-1)
                        k++;
                }
                else   
                System.out.println("Couple: "+couple[i].boy.bname + " and " + couple[i].girl.gname);
            }
        }
        catch(OutOfboundException ee){
                    Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);
 
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
            if(gift[i] != null && gift[i].getClass().equals("Luxury")){
                return i;
            }
        }
        return -1;
    }
    /**
     * find boy
     * @param boyy
     * @param boy
     * @return i index where that boy is found in collection
     */
    int findboy(boys boyy,boys boy[]){
        int i;
        for(i = 0 ; i< boy.length;i++){
            if(boy[i]!= null && boy[i].bname.equals(boyy.bname)){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * find girl
     * @param girll
     * @param girl
     * @return i index where that girl is found in collection
     */
    int findgirl(girls girll,girls girl[]){
        int i;
        for(i = 0 ; i< girl.length;i++){
            if(girl[i] != null && girl[i].gname.equals(girll.gname)){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * find k least happiest couples
     * @param couple
     * @param noOfCouple
     * @param k
     * @param arr_boys
     * @param arr_girls
     * @param bw1
     * @param tt 
     */
    void leastKHappiestCouple(couples couple[],int noOfCouple,int k,boys arr_boys[],girls arr_girls[],BufferedWriter bw1,Timestamp tt,gifts gift[]){
        try{
            calculate_happiness_comp(couple,noOfCouple);
            int i,j;
            Arrays.sort(couple, new CoupleHappyComparator());
            /*couples temp ;
            for(i=0; i < noOfCouple -1; i++){
                for(j= i+1; j< noOfCouple ; j++){
                    if(couple[i] != null && couple[j] != null && couple[i].happiness < couple[j].happiness){
                        temp = couple[i];
                        couple[i] = couple[j];
                        couple[j] = temp;
                    }
                }
            }*/
            System.out.println("Least " + k +" Happiest Couple -- ");
            bw1.write("Least " + k + " Happiest Couple");
            bw1.newLine();

            for(i = noOfCouple-1; i > noOfCouple - k-1 && i>=0; i--){
                if(couple[i] == null ) {
                    k ++;
                    continue;
                }
                String br =new String("Couple: "+couple[i].boy.bname + " and " + couple[i].girl.gname +" breaks up at " + tt);
                System.out.println(br);
                bw1.write(br);
                bw1.newLine();
                boys boy = couple[i].boy;
                girls girl = couple[i].girl;
                
                int in = findgirl(girl,arr_girls);
                if(in ==-1) continue;
                else {
                    arr_girls[in].status = false;
                    arr_girls[in].arr_gift = null;
                    arr_girls[in].arr_time = null;
                    arr_girls[in].ex_bf = boy;
                    arr_girls[in].tot_gift_amount = 0;
                    arr_girls[in].tot_gift_value = 0;
                    arr_girls[in].bf =null;
                    arr_girls[in].g_happiness = 0;  
                }
                try{
                    if(allocate_notex(arr_boys,arr_girls[in])){
                        couple[i] = new couples(arr_girls[in].bf,girl,tt);
                        try{
                            bw1.write(couple[i].girl.gname +" committed to " +couple[i].boy.bname + " at " + couple[i].timestamp );
                            bw1.newLine();
                            System.out.println(couple[i].girl.gname +" committed to " +couple[i].boy.bname + " at " + couple[i].timestamp );

                        }
                        catch(IOException ee){
                            Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

                        }
                        allocate_gift_one(couple[i], gift, noOfCouple, bw1);
                    }
                    else {
                        throw new BreakupException(couple[i]);
                    }
                }
                catch(BreakupException ee){
                    couple[i]=null;
                    Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

                }
                in = findboy(boy,arr_boys);
                if(in ==-1) continue;
                else {
                    arr_boys[in].status = false;
                    arr_boys[in].ex_gf = girl;
                    arr_boys[in].tot_gift_amount = 0;
                    arr_boys[in].b_happiness = 0;
                    arr_boys[in].gf = null;
                    arr_boys[in].status = false;
                }      
            }
        }
        catch(IOException ee){
                    Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

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
    void allocate_gift_one(couples couple,gifts gift[],int noOfGift,BufferedWriter fp){
        if(couple==null)return;
        Arrays.sort(gift, new GiftComparator());
        try{
            fp.write("Gift Exchanges are : " );
            fp.newLine();
            gifts tempgift = null;
            int j;
            j=0;
                    Timestamp tt =new Timestamp(System.currentTimeMillis()); 

                    if(j == 0 && gift[j] != null && gift[j].price > (couple.boy.budget ) ){
                        //add gift to their basket
                        couple.boy.budget += gift[j].price;
                        couple.girl.arr_gift.add(gift[j]);
                        couple.girl.arr_time.add(tt);
                        couple.girl.tot_gift_value = gift[j].value;
                        couple.girl.tot_gift_amount = gift[j].price;
                        couple.boy.tot_gift_amount = gift[j].price;
                        fp.write(couple.boy.bname +" give gift"+ gift[j].id+ " of type "+ gift[j].getClass().getSimpleName() + " of price "+gift[j].price+ " to " +couple.girl.gname+ " at " + tt);
                        fp.newLine();
                        System.out.println(couple.boy.bname +" give gift"+ gift[j].id+ " of type "+ gift[j].getClass().getSimpleName() + " of price "+gift[j].price+ " to " +couple.girl.gname+ " at " + tt);                    
                    }
                    for(j =1; j< noOfGift; j++){
                        if(gift[j] == null)
                            continue;
                        int k=noOfGift;
                    //if boy is geek then gift one additional luxury gift to his girlfriend
                        try {
                            if(couple.boy.getClass().getSimpleName().equals("Geek") && j==1 && gift[j].price <= couple.boy.budget - couple.boy.tot_gift_amount){
                                //add gift to their basket  
                                k = find(gift);
                                if(k==-1) 
                                    throw new GiftAllocationException(couple,1);
                                tempgift  = gift[k];
                                couple.girl.arr_gift.add(gift[k]);
                                tt =new Timestamp(System.currentTimeMillis()); 
                                couple.girl.arr_time.add(tt);
                                couple.girl.tot_gift_value = tempgift.value;
                                couple.girl.tot_gift_amount = tempgift.price;
                                couple.boy.tot_gift_amount = tempgift.price;
                                fp.write(couple.boy.bname +" give gift"+gift[k].id +" of type "+gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple.girl.gname + " at " + tt );
                                fp.newLine();
                                System.out.println(couple.boy.bname +" give gift"+gift[k].id +" of type "+gift[k].getClass().getSimpleName() + " of price "+gift[k].price+ " to " +couple.girl.gname + " at " + tt );

                            }
                            else if(couple.boy.getClass().getSimpleName().equals("geeks") || couple.boy.getClass().getSimpleName().equals("miser")){
                                if(couple.girl.tot_gift_amount > couple.girl.maint_cost){
                                    break;
                                }
                                //if cannot gift anymore gifts to their girlfriends, go to next couple and allocate gifts in their baskets
                            }
                            else if(gift[j] != null && gift[j].price <= (couple.boy.budget - couple.girl.tot_gift_amount) && j!=k ){
                                //add gift to their basket
                                
                                couple.girl.arr_gift.add(gift[j]);
                                tt =new Timestamp(System.currentTimeMillis()); 
                                couple.girl.arr_time.add(tt);
                                couple.girl.tot_gift_value += gift[j].value;
                                couple.girl.tot_gift_amount += gift[j].price;
                                couple.boy.tot_gift_amount += gift[j].price;
                                fp.write(couple.boy.bname +" give gift"+gift[j].id +" of type "+gift[j].getClass().getSimpleName() +  "of price "+gift[j].price+ " to " +couple.girl.gname+ " at " + tt);
                                fp.newLine();
                                System.out.println(couple.boy.bname +" give gift"+gift[j].id +" of type "+gift[j].getClass().getSimpleName() +  "of price "+gift[j].price+ " to " +couple.girl.gname+ " at " + tt);

                            }
                            else {
                                throw new GiftAllocationException(couple);
                            }
                        }
                        catch(GiftAllocationException ee){
                            j = noOfGift;
                            Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);

                        }
                    }

                }            
        catch(Exception e){
             e.printStackTrace();
        }
    }
    
    
    
    /**
     * allocate boyfriends to girlfriends as per their requirements other than ex
     * @param arr_boys
     * @param girl
     * @return true or false
     */
    boolean allocate_notex( boys arr_boys[], girls girl) {
            int j;
            for(j=0; j< arr_boys.length; j++){
                if(girl!= null && arr_boys[j]!= null && available_notex(girl,arr_boys[j])){
                    girl.bf = arr_boys[j];
                    girl.status = true;
                    arr_boys[j].gf = girl;
                    arr_boys[j].status = true;
                    return true;
                }   
            }
        return false;    
    }
    
    /**
     * check whether any other boy available for that girl as per their requirements other than ex
     * @param girl
     * @param boy
     * @return true or false 
     */
    boolean available_notex(girls girl,boys boy){
        if(girl.maint_cost < boy.budget && boy.min_attractive_req < girl.attractive && girl.status == false && boy.status == false && girl.ex_bf != boy && boy.ex_gf != girl){
            return true;
        }
        return false;
  
    }  
}
