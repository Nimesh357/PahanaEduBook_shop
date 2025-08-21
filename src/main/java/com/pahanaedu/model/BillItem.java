package com.pahanaedu.model;
import java.io.Serializable;
public class BillItem implements Serializable {
    private long itemId; private String itemName; private int quantity; private double unitPrice;
    public BillItem(){}
    public BillItem(long itemId,String itemName,int quantity,double unitPrice){
        this.itemId=itemId; this.itemName=itemName; this.quantity=quantity; this.unitPrice=unitPrice;
    }
    public long getItemId(){return itemId;} public void setItemId(long v){itemId=v;}
    public String getItemName(){return itemName;} public void setItemName(String v){itemName=v;}
    public int getQuantity(){return quantity;} public void setQuantity(int v){quantity=v;}
    public double getUnitPrice(){return unitPrice;} public void setUnitPrice(double v){unitPrice=v;}
    public double getLineTotal(){return unitPrice*quantity;}
}
