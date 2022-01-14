/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package omnicellscheduler;

import java.util.ArrayList;

/**
 *
 * @author taylorhuffman
 */
public class Data {
    
    private static Data instance;
    
    private ArrayList<Drugs> drugs;
    
    public Data(){
    }
    
    public static Data getInstance(){
        if(instance == null){
            instance = new Data();
        }
        return instance;
    }
    
    
    public ArrayList<Drugs> getDrugs() {
        return drugs;
    }
    
    public void setDrugs(ArrayList<Drugs> drugs){
        this.drugs = drugs;
    }
}
