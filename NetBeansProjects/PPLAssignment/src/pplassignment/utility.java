/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pplassignment;
import java.io.*;
import java.util.*;

/**
 *
 * @author nikita
 */
//consists all functions used
public class utility {
    /*allocate boyfriends to girlfriends as per their requirements*/
    void allocate( boys arr_boys[], int no_of_Boy, girls arr_girls[],int no_of_Girl) {
       
        Random rand = new Random();
        char[] name;
        String nn;
        int l  = Math.abs(rand.nextInt(5)+5), j;
        int attractive,intelligence,min_attr,type,i;
        int bud;
        boys boy ;
        girls girl;
        for(i = 0; i< no_of_Boy; i++){
            name = new char[l];
            for(j =0; j< l; j++)
                name[j] = (char)Math.abs((rand.nextInt(26)+97));
            nn = new String(name);
            attractive = rand.nextInt(10);
            intelligence = rand.nextInt(10);
            bud  = rand.nextInt(3000) + 1000;
            min_attr  = rand.nextInt(10);
            type = rand.nextInt(3);
            boy = new boys(nn,attractive, intelligence, bud, min_attr, type);
            arr_boys[i] = (boy);
        }
        for(i = 0; i < no_of_Girl; i++){
            name = new char[l];
            for(j =0; j< l; j++)
                name[j] = (char)(rand.nextInt(26)+97);
            nn = new String(name);
            attractive = rand.nextInt(10);
            intelligence = rand.nextInt(10);
            bud  = rand.nextInt(2000) + 1000;
            min_attr  = rand.nextInt(10);
            type = rand.nextInt(3);
            girl = new girls(nn,bud,attractive, intelligence, type);
            arr_girls[i] = (girl);
        }
        
        for(i=0; i<no_of_Girl; i++){
            for(j=0; j< no_of_Boy; j++){
                if(available(arr_girls[i],arr_boys[j])){
                    arr_girls[i].bf = arr_boys[j];
                    arr_girls[i].status = true;
                    arr_boys[j].gf = arr_girls[i];
                    arr_boys[j].status = true;
                    System.out.println(arr_girls[i].gname +" committed to " +arr_boys[j].bname  );
                }   
            }
        }
    }
    /* check whether girl and boy are available to commit as per their status and requirements*/
    boolean available(girls girl,boys boy){
        if(girl.maint_cost < boy.budget && boy.min_attractive_req < girl.attractive && girl.status == false && boy.status == false){
            return true;
        }
        return false;
    }
    //generate gifts randomly
    void generate_gift(int noOfGifts, gifts arr_gift[]){
        Random rand = new Random();
        int type;
        String gtype,utilClass;
        int value,price, j,i,rating,difficulty,utilvalue;
        gifts gift;
        for(i = 0; i< noOfGifts; i++){
            type = rand.nextInt(3);
            switch(type){
                case 0:
                    gtype = new String("essential");
                    value = rand.nextInt(10);
                    price = rand.nextInt(500) + 500;
                    arr_gift[i] = new gifts(gtype,value, price);
                    break;
                case 1:
                    gtype = new String("luxury");
                    value = rand.nextInt(20);
                    price = rand.nextInt(500) + 750;
                    rating = rand.nextInt(10) + 10;
                    difficulty = rand.nextInt(10) + 2;
                    arr_gift[i] = new gifts(gtype,value,price,rating,difficulty);

                    break;
                case 2:
                    gtype = new String("utility");
                    value = rand.nextInt(30);
                    price = rand.nextInt(500) + 900;
                    utilvalue = rand.nextInt(10) + 3;
                    utilClass = "";      //new String("");
                    arr_gift[i] = new gifts(gtype,value,price,utilvalue,utilClass);
                    break;
            }           
           
        }
        
    }
    
    //make couples
    int makeCouple(girls girl[],couples couple[],BufferedWriter fp){
        int i,k=0;
        for(i = 0; i< girl.length;i++){
            if(girl[i].status ){
                
                couple[k] = new couples(girl[i].bf, girl[i]);
                try{
                    fp.write("couples " +k+" : " + couple[k].girl.gname + " " + couple[k].boy.bname);
                    fp.newLine();
                }
                catch(IOException ee){
                    ee.printStackTrace();
                }
                k++;
            }
        }
        return k;
    }
    
    //create giftbasket for each couple if boys' budget allows
    void allocate_gift(couples couple[],gifts gift[],int noOfGirl,int noOfGift,BufferedWriter fp){
        sort(gift,noOfGift);
        gifts tempgift = new gifts("",0,0);
        int i,j;
        
        for(i = 0; i < noOfGirl; i++){
            if(couple[i].boy == null)     
                break;
            
            for(j =0; j< noOfGift; j++){
                int k=noOfGift;
                if(couple[i].boy.b_type.equals("geeks") && j==0 && gift[j].price <= (couple[i].boy.budget - couple[i].girl.tot_gift_amount )){
                    k = find(gift);
                    if(k==-1) continue;
                    couple[i].arr_gift.add(gift[k]);
                    couple[i].girl.tot_gift_value = tempgift.value;
                    couple[i].girl.tot_gift_amount = tempgift.price;
                    couple[i].boy.tot_gift_amount = tempgift.price;
                    try{
                        fp.write(couple[i].boy.bname +" give "+gift[k].gifttype + " of price "+gift[k].price+ " to " +couple[i].girl.gname);
                        fp.newLine();
                    }
                    catch(Exception e){
                        
                    }
                }
                if(gift[j].price <= (couple[i].boy.budget - couple[i].girl.tot_gift_amount) && j!=k ){
                    couple[i].arr_gift.add(gift[j]);
                    couple[i].girl.tot_gift_value += gift[j].value;
                    couple[i].girl.tot_gift_amount += gift[j].price;
                    
                    couple[i].boy.tot_gift_amount += gift[j].price;
                    try{
                        fp.write(couple[i].boy.bname +" give "+gift[j].gifttype + "of price "+gift[j].price+ " to " +couple[i].girl.gname);
                        fp.newLine();
                    }
                    catch(Exception e){
                        
                    }
                }
                else if(j == 0 && gift[j].price > (couple[i].boy.budget - couple[i].boy.tot_gift_amount) ){
                    couple[i].boy.budget += gift[j].price;
                    j--;
                    
                }
                else break;
                
            }
            //if boy is geek then gift one additional luxury gift to his girlfriend
            
        }
    
    }
    //calculate happiness and compatibility of all couples
    void calculate_happiness_comp(couples couple[],int noOfCouple){
        int i;
        for(i =0; i< noOfCouple; i++){
            couple[i].happiness();
            couple[i].compatibility();
        }
    }
    //find k best happiest couples
    void bestKHappiestCouple(couples couple[],int noOfCouple,int k){
        int i,j;
        couples temp ;
        for(i=0; i < noOfCouple -1; i++){
            for(j= i+1; j< noOfCouple ; j++){
                if(couple[i].happiness < couple[j].happiness){
                    temp = couple[i];
                    couple[i] = couple[j];
                    couple[j] = temp;
                }
            }
        }
        for(i =0; i< k; i++){
            System.out.println("Couple: "+couple[i].boy.bname + " and " + couple[i].girl.gname);
        }
    }
    //find k best compatible couples
    void bestKCompatibleCouple(couples couple[],int noOfCouple,int k){
        int i,j;
        couples temp ;
        for(i=0; i < noOfCouple -1; i++){
            for(j= i+1; j< noOfCouple ; j++){
                if(couple[i].compatability < couple[j].compatability){
                    temp = couple[i];
                    couple[i] = couple[j];
                    couple[j] = temp;
                }
            }
        }
        for(i =0; i<k;i ++){
            System.out.println("Couple: "+couple[i].boy.bname + " and " + couple[i].girl.gname);
        }
    }
    //sort all the as per theri increasing price
    void sort(gifts gift[],int no_gift){
        int i,j;
        System.out.println(gift.length);
        gifts temp = new gifts("",0,0);
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
    int find(gifts gift[]){
        int i;
        for(i = 0 ; i< gift.length;i++){
            if(gift[i].gifttype.equals("luxury")){
                return i;
            }
        }
        return -1;
    }
    
}
