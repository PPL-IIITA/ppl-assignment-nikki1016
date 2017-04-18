/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IllegealException;

import q_11.*;

/**
 * exception raises when no more gifts are available for the boy to gift to his girlfriend
 * @see Exception
 * @author nikita
 */
public class GiftAllocationException extends Exception{
    /**
     * constructor() called when geek boy cannot gift luxury gift to his girlfriend
     * @param couple gift exchanges b/w couple
     * @param i tell whether boy is geek or not
     */
    public GiftAllocationException(couples couple,int i){
        System.out.println("Exception found : " + couple.boy.bname + " (geek type) cannot give luxury gift to his gf "+ couple.girl.getgname());
    }
    /**
     * constructor() called when no more gifts are available for the boy to gift to his girlfriend
     * @param couple gift exchanges b/w couple
     */
    public GiftAllocationException(couples couple){
        System.out.println("Exception found : " + couple.boy.bname + " cannot give more gifts to his gf "+ couple.girl.getgname());
    }
    /**
     * constructor() called when no more gifts are available for the boy to gift to his girlfriend
     * @param bname gift exchanges b/w bname boy and girl
     * @param girl gift exchanges b/w bname boy and girl
     */
    public GiftAllocationException(String bname,girls girl){
        System.out.println("Exception found : " + bname + " cannot give more gifts to his gf "+ girl.getgname());
    }
    /**
     * constructor() called when no more gifts are available for the boy to gift to his girlfriend
      * @param bname gift exchanges b/w bname boy and girl
     * @param girl gift exchanges b/w bname boy and girl
     * @param i whether boy is geek or not
     */
    public GiftAllocationException(String bname,girls girl,int i){
        System.out.println("Exception found : " + bname + " (geek type) cannot give luxury gift to his gf "+ girl.getgname());
    }
}
