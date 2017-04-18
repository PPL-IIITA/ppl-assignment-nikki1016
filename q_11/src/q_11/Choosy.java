/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_11;
/**
 *
 * The choosy girl subclass of girls
  * @see girls
 */
public class Choosy extends girls{
    /**
    * constructor() of The choosy girl class
    * @param gno girl_no for unique girl id
    * @see girls
    */
    public Choosy(int gno){
        super(gno);
        attractive = rand.nextInt(5)+9;
        intelligence = rand.nextInt(8);
        maint_cost = rand.nextInt(2000)+1000;
    }
    /**
     * the method g_happiness method to calculate girl's happiness
     * happiness in a relationship is logarithmic of the total cost of gifts achieved over
     * maintenance.
    * @return g_happiness
     */
    
    @Override
     int g_happiness(){  
         if(status == false){
             g_happiness = 0;
             return g_happiness;
         }
        int i,ess = 0 ,lux = 0,util = 0;
        int essd = 0,luxd = 0,utild = 0;
        gifts tt;
        for(i=0; i < arr_gift.size(); i++){
            tt = arr_gift.get(i);
            if(tt == null)
                continue;
            if(arr_gift.get(i) instanceof Essential){
                essd += arr_gift.get(i).price;
                ess += arr_gift.get(i).value;
            }
            else if(tt instanceof Luxury){
                luxd += arr_gift.get(i).price;
                lux += arr_gift.get(i).value;
            }
            else if(tt instanceof Utility){
                utild += arr_gift.get(i).price;
                util += arr_gift.get(i).value;
            }
        }
        int gift_amount = essd + luxd + utild;
        int gift_value  = ess + 2*lux + util;

        g_happiness = (int)(Math.log(maint_cost - gift_amount ) );
         return g_happiness;
   
     }
       /**
     * Available function check whether girl and boy are available to commit as per their status and requirements 
     * @param girl
     * @param boy
     * @return 
     */
    int available(boys boy[]){
          
        if(status == true )
                return -1;
        int i,in=-1;
        int att = -9999;
        int f = 0;
       for(i=0;i < boy.length;i++){
            if(boy[i]==null){
                continue;
            }
            if( boy[i].attractive > att && maint_cost < boy[i].budget && boy[i].min_attractive_req < attractive && boy[i].status == false){
                att = boy[i].attractive;
                in = i;
                                  
            }
       }
        return in;
    }
  
}