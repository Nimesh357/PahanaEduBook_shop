package com.pahanaedu.model;
import java.io.Serializable;
public class Item implements Serializable {
    private long id; private String name; private String description; private double unitPrice;
    public Item(){}
    public Item(long id,String name,String description,double unitPrice){
        this.id=id; this.name=name; this.description=description; this.unitPrice=unitPrice;
    }
    public long getId(){return id;} public void setId(long v){id=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public double getUnitPrice(){return unitPrice;} public void setUnitPrice(double v){unitPrice=v;}
}
