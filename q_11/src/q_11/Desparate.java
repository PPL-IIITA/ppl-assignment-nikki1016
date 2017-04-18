/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_11;


/**
 * The desperate girl subclass of girls
 * @see girls
 */


public class Desparate extends girls{
    /**
    * constructor() of The desperate girl
    * @param gno girl_no for unique girl id
    *  @see girls
    */
    public Desparate(int gno){
        super(gno);
        attractive = rand.nextInt(3)+8;
        intelligence = rand.nextInt(10);
        maint_cost = rand.nextInt(3000)+1000;
    }
    /**
     * the method g_happiness method to calculate girl's happiness
     * happiness in a relationship is exponential to the total cost of gifts received over maintenance, including luxury gifts. g_happiness method to calculate girl's happiness
    * @return g_happiness
     */
    @Override
     int g_happiness(){   
         if(status == false ){
             g_happiness = 0;
             return g_happiness;
         }
        g_happiness = Math.getExponent(maint_cost - tot_gift_amount + tot_gift_value);
        return g_happiness;

     }
       /**
     * Available function check whether girl and boy are available to commit as per their status and requirements 
     * @param girl
     * @param boy
     * @return 
     */
    @Override
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
            if( boy[i].budget> att &&  maint_cost < boy[i].budget && boy[i].min_attractive_req < attractive &&  boy[i].status == false){
                att = boy[i].budget;
                in = i;
            }
            if(f ==1)
                 break;
   
        }
        return in;
    }
   
    
}