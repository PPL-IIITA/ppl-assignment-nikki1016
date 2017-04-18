/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_11;
/**
 * 
    *The Normal girl subclass of girls
    *  @see girls
 */
public class Normal extends girls{
     /**
    * constructor() of The Normal girl
    * @param gno girl_no for unique girl id
    *  @see girls
    */
    public Normal(int gno){
        super(gno);
        attractive = rand.nextInt(8);
        intelligence = rand.nextInt(10);
        maint_cost = rand.nextInt(1000)+1000;
    }
    /**
     * the method g_happiness method to calculate girl's happiness which is linear to the total cost of gifts achieved over maintenance,including luxury gifts. 
    * @return g_happiness
     */
     int g_happiness(){    
         if(status == false ){
             g_happiness = 0;
             return g_happiness;
         }
        g_happiness  = (int)(maint_cost - tot_gift_amount  );
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
            if( boy[i].intelligence > att &&  maint_cost < boy[i].budget && boy[i].min_attractive_req < attractive  && boy[i].status == false){
                att = boy[i].intelligence;
                in = i;
            }
            if (f ==1)
                break;         
            }
       
        return in;
    }
   
    
}