package com.pahanaedu.model;
import java.io.Serializable;
public class Customer implements Serializable {
    private long accountNumber; private String name; private String address; private String telephone; private int unitsConsumed;
    public Customer(){}
    public Customer(long accountNumber,String name,String address,String telephone,int unitsConsumed){
        this.accountNumber=accountNumber; this.name=name; this.address=address; this.telephone=telephone; this.unitsConsumed=unitsConsumed;
    }
    public long getAccountNumber(){return accountNumber;} public void setAccountNumber(long v){accountNumber=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getAddress(){return address;} public void setAddress(String v){address=v;}
    public String getTelephone(){return telephone;} public void setTelephone(String v){telephone=v;}
    public int getUnitsConsumed(){return unitsConsumed;} public void setUnitsConsumed(int v){unitsConsumed=v;}
}
