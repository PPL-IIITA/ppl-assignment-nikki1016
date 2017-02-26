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
//girls attributes and her type
public class girls {
    public String gname;
    public int attractive;
    public int maint_cost;
    public int intelligence;
    public String g_type;
    public int tot_gift_amount;
    public int tot_gift_value;
    public boys bf;
    public boolean status;
    public int g_happiness;
    
    
    public girls(String name,int bud,int attr,int intelligent,int type){
        tot_gift_amount = 0;
        tot_gift_value = 0;
        gname = name;
        maint_cost = bud;
        attractive = attr;
        status = false;
        if(type == 0) g_type = "choosy";
        else if(type == 1) g_type = "normal";
        else if(type == 2) g_type = "desparate";
    }
    
    
}
