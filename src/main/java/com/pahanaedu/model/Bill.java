package com.pahanaedu.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList; import java.util.List;
public class Bill implements Serializable {
    private long id; private long customerAccountNumber; private String customerName;
    private LocalDateTime createdAt = LocalDateTime.now();
    private List<BillItem> items = new ArrayList<>();
    public Bill(){}
    public Bill(long id,long customerAccountNumber,String customerName){
        this.id=id; this.customerAccountNumber=customerAccountNumber; this.customerName=customerName;
    }
    public long getId(){return id;} public void setId(long v){id=v;}
    public long getCustomerAccountNumber(){return customerAccountNumber;} public void setCustomerAccountNumber(long v){customerAccountNumber=v;}
    public String getCustomerName(){return customerName;} public void setCustomerName(String v){customerName=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public List<BillItem> getItems(){return items;} public void setItems(List<BillItem> v){items=v;}
    public double getTotalUnitsConsumed(){ return items.stream().mapToInt(BillItem::getQuantity).sum(); }
    public double getTotalAmount(){ return items.stream().mapToDouble(BillItem::getLineTotal).sum(); }
}
