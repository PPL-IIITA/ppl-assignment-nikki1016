/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pplassignment;

/**
 *
 * @author nikita
 */

//boys attributes with their types also
public class boys {
    public String bname;
    public int min_attractive_req;
    public int attractive;
    public int intelligence;
    public int budget;
    public String b_type;
    public girls gf;
    public boolean status;
    public int tot_gift_amount;
    public int b_happiness;

    public boys(String name,int attractive,int intelligence,int bud,int min_attr,int type){
        bname = name;
        budget = bud;
        min_attractive_req = min_attr;
        status = false;
        this.attractive = attractive;
        this.intelligence = intelligence;
        tot_gift_amount=0;
        b_happiness = 0;
        if(type == 0) b_type = "miser";
        else if(type == 1) b_type = "generous";
        else if(type == 2) b_type = "geeks";
    }
    
    
    
}
