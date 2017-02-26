/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pplassignment;

import java.io.*;
import java.util.Random;

/**
 *
 * @author nikita
 */
public class q2_main {
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        
        File file1 ;
        File file2;
        BufferedWriter bw1=null;
        BufferedWriter bw2 =null;
        try{
              file1 = new File("log.txt");
              file2 = new File("testing.txt");
	 /* This logic will make sure that the file 
	  * gets created if it is not present at the
	  * specified location*/
            if (!file1.exists()) {
               file1.createNewFile();
            }
            if (!file2.exists()) {
               file2.createNewFile();
            }
            bw1 = new BufferedWriter(new FileWriter(file1));
            bw2 = new BufferedWriter(new FileWriter(file2));
            String br;
            
            Random rand = new Random();
            int noOfGirl = rand.nextInt(15) + 5;       //random no of girls
            int noOfBoy = (rand.nextInt(3)+2) * noOfGirl;        //random no of boys >> no of girls
            int noOfGift = rand.nextInt(20) + 15,i;              //random no of gifts

            girls arr_girls[] = new girls[noOfGirl];
            boys arr_boys[] = new boys[noOfBoy];
            gifts arr_gifts[] = new gifts[noOfGift];
            couples arr_couple[]= new couples[noOfGirl];
            utility object1 = new utility();
            //allocate boyfriends to girls
            object1.allocate(arr_boys,noOfBoy, arr_girls,noOfGirl);  
            //generate gifts
            object1.generate_gift(noOfGift, arr_gifts);
            bw2.write("-------Boys--------");
            bw2.newLine();
            bw2.write("boyname btype attr intg budget ");
            bw2.newLine();
           
            for(i = 0; i< noOfBoy; i++){
                br = new String(arr_boys[i].bname + " " +arr_boys[i].b_type +" "+ arr_boys[i].attractive+" "+arr_boys[i].intelligence+" "+ arr_boys[i].budget);
                bw2.write(br);
                bw2.newLine();
            }
            bw2.write("-------------------------------");
            bw2.newLine();
            bw2.write("------Girls-------");
            bw2.newLine();
            bw2.write("girlname gtype attr intg maintainence_cost ");
            bw2.newLine();
            for(i = 0; i< noOfGirl; i++){
                br = new String(arr_girls[i].gname + " " +arr_girls[i].g_type+" "+ arr_girls[i].attractive+" "+arr_girls[i].intelligence+" "+ arr_girls[i].maint_cost);
                bw2.write(br);
                bw2.newLine();
            }
            bw2.write("-------------------------------");
            bw2.newLine();
            bw2.write("------Gifts--------");
            bw2.newLine();
            bw2.write("gftype price val luxrat diffOb utValue ");
            bw2.newLine();
            for(i = 0; i< noOfGift; i++){
                br = new String(arr_gifts[i].gifttype + "  " +arr_gifts[i].price +"  "+ arr_gifts[i].value + "   "+arr_gifts[i].luxRating+"   "+ arr_gifts[i].difficultyObtain + "   "+ arr_gifts[i].utilValue);
                bw2.write(br);
                bw2.newLine();

            }
            int noOfCouple = object1.makeCouple(arr_girls, arr_couple,bw1);         //no of couple randomly generated

            object1.calculate_happiness_comp(arr_couple,noOfCouple);

            object1.allocate_gift(arr_couple, arr_gifts, noOfCouple, noOfGift,bw1);
            int k = Math.abs(rand.nextInt(noOfCouple)-3);
            System.out.println("Best "+ k + " happiest couple --");
            object1.bestKHappiestCouple(arr_couple,noOfCouple, k);
            System.out.println("Best "+ k + " compatible couple --");
            object1.bestKCompatibleCouple(arr_couple,noOfCouple, k);
        }
        catch(IOException ee){
            ee.printStackTrace();
        }
        finally { 
            try{
                if(bw1!=null)
                    bw1.close();
                if(bw2!=null)
                    bw2.close();
            }
            catch(IOException ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
        
    }
}
