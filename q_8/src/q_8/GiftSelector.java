/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q_8;

import java.io.BufferedWriter;
import java.sql.Timestamp;

/**
 *
 * abstract GiftSelector class
 */
abstract public class GiftSelector {
    utility obj = new utility();
    gifts tempgift;
    int i,j;
    Timestamp tt;
    
    /**
     * create gift basket for each couple if boy's budget allows
     * @param couple
     * @param gift
     * @param noOfGirl
     * @param noOfGift
     * @param fp 
     */
    abstract void allocate_gift(couples couple[],gifts gift[],int noOfGirl,int noOfGift,BufferedWriter fp);
}
