/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_7;
/**
     * findGF using storing committed boys in a couple as an array
         *@see findGF

    */
public class findGF1 extends findGF{
    /**
    *@see findGF
     * find method will findGF using storing committed boys in a couple as an array
     * @param boy
     * @param hm
     * @return name of girlfriend of required boy 
     */
    String find (String boy,couples couple[],int noOfCouple){
        int i;
        for( i =0; i< noOfCouple; i++){
            if(couple[i] != null && couple[i].boy.bname.equals(boy))
                return couple[i].girl.gname;
        }
        return null;
    }

}
