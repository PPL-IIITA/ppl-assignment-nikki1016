/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_7;


/**
 *
 * findGF using Binary search
 */
public class findGF2  extends findGF{
    /**
    *@see findGF
     * find method will find the girlfriend of a particular boy using binary search
     * @param boy
     * @param hm
     * @return name of girlfriend of required boy 
     */
     String find (String boy,boys arr_boy[]){
        int i,j;
        for(i =0; i<arr_boy.length - 1; i++){
            for(j = i+1; j< arr_boy.length; j++){
                if(arr_boy[i].bname.compareTo(arr_boy[j].bname) > 0){
                    boys temp = arr_boy[i];
                    arr_boy[i] = arr_boy[j];
                    arr_boy[j] = temp;
                }
            }
        }
        i = binarysort(boy,arr_boy,0,arr_boy.length - 1);
        if(arr_boy[i].status == true)
            return arr_boy[i].gf.gname;
        else return null;
    }
     
    int binarysort(String boy,boys arr_boy[],int st,int fin){
        if(arr_boy[(fin+st)/2].bname.equals(boy))
            return (fin+st)/2;
        else if(arr_boy[(fin+st)/2].bname.compareTo(boy) > 0)
            return binarysort(boy,arr_boy,st,(fin+st)/2); 
        else 
            return binarysort(boy,arr_boy,(fin+st)/2+1,fin); 
    }
}
