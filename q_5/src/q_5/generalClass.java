/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * general class to generate girls , boys and gifts randomly
 */
public class generalClass  {
       /** 
       *    general class to generate girls , boys and gifts randomnly
       *   open all four files to update information of boys, girls and gifts as well their committments,breakups and gift exchanges.
       *   @param noOfGirl  random no of girls
       *   @param noOfBoy   random no of boys >> no of girls
       *   @param noOfGift  random no of gifts
       *   @param noOfCouple no of couples formed
       *   @param arr_girls  one collection of girls 
       *   @param arr_boys   one collection of boys
       *   @param arr_gifts  one collection of gifts
       *   @param arr_couple one collection of all couples
       *   @param  file1 to open log.txt
       *   @param  file2 to open "boy_testing.txt"
       *   @param  file3 to open "girl_testing.txt"
       *   @param  file4  to open "gift_testing.txt"
        
       */
        File file1 ;
        File file2;
        File file3;
        File file4;
        BufferedWriter bw1=null;
        BufferedWriter bw2 =null;
        BufferedWriter bw3=null;
        BufferedWriter bw4 =null;
        Random rand = new Random();
        utility object1 = new utility();
        int noOfGirl = rand.nextInt(15) + 5;           //random no of girls
        int noOfBoy = (rand.nextInt(3)+2) * noOfGirl;     //random no of boys >> no of girls
        int noOfGift = rand.nextInt(20) + 15;           //random no of gifts
        int noOfCouple ;
        girls arr_girls[]= new girls[noOfGirl];
        boys arr_boys[]= new boys[noOfBoy];
        gifts arr_gifts[]  = new gifts[noOfGift];
        couples arr_couple[] ;
        /**
         * constructor
         * @throws FileNotFoundException
         */
        public generalClass() throws FileNotFoundException {
        
            try{
                file1 = new File("log.txt");
                file2 = new File("boy_testing.txt");
                file3 = new File("girl_testing.txt");
                file4 = new File("gift_testing.txt");
                /* This logic will make sure that the file 
                 * gets created if it is not present at the
                 * specified location*/
                if (!file1.exists()) {
                    file1.createNewFile();
                }
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                if (!file3.exists()) {
                    file3.createNewFile();
                }
                if (!file4.exists()) {
                    file4.createNewFile();
                }
                bw1 = new BufferedWriter(new FileWriter(file1));
                bw2 = new BufferedWriter(new FileWriter(file2));
                bw3 = new BufferedWriter(new FileWriter(file3));
                bw4 = new BufferedWriter(new FileWriter(file4));
                //generate girls of three types randomnly
                int i,j;
                for(i = 0; i < noOfGirl; i++){
                    j = Math.abs(rand.nextInt(3));
                    if(j ==0)
                        arr_girls[i] = new Choosy(i);
                    else if(j == 1)
                        arr_girls[i] = new Normal(i);
                    else if(j ==2)
                        arr_girls[i] = new Desparate(i);
                }
                //generate girls of three types randomnly
                for(i = 0; i < noOfBoy; i++){
                    j = Math.abs(rand.nextInt(3));
                    if(j == 0)
                        arr_boys[i] = new Geek(i);
                    else if(j == 1)
                        arr_boys[i] = new Generous(i);
                    else if(j ==2)
                        arr_boys[i] = new Miser(i);
                }
                String br;

                bw2.write("-------Boys--------");
                bw2.newLine();
                bw2.write("boyname btype attr intg budget ");
                bw2.newLine();
                for(i = 0; i< noOfBoy; i++){
                    br = new String(arr_boys[i].bname + " " +arr_boys[i].getClass().getSimpleName()+" "+ arr_boys[i].attractive+" "+arr_boys[i].intelligence+" "+ arr_boys[i].budget);
                    bw2.write(br);
                    bw2.newLine();
                }
                bw2.write("-------------------------------");
                bw2.newLine();
                bw3.write("------Girls-------");
                bw3.newLine();
                bw3.write("girlname gtype attr intg maintainence_cost ");
                bw3.newLine();
                for(i = 0; i< noOfGirl; i++){
                    br = new String(arr_girls[i].gname + " " +arr_girls[i].getClass().getSimpleName()+" "+ arr_girls[i].attractive+" "+arr_girls[i].intelligence+" "+ arr_girls[i].maint_cost);
                    bw3.write(br);
                    bw3.newLine();
                }
                  // a collection of all types of gifts
                object1.generate_gift(noOfGift, arr_gifts,bw4);

            }
            catch(IOException ee){
                    ee.printStackTrace();
            }        
    }
}
