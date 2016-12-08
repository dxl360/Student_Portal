package com.example.duanli.student_portal;

import java.io.Serializable;

/**
 * Created by duanli on 10/23/16.
 */

public class Item implements Serializable {

    private int itemID;
    private String itemPicture;
    private int sellerID;
    private String itemName;
    private int price;
    private String contact;
    private String description;
    private int status; //0 available 1 reserved 2 resolved


    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getSellerID() {
        return sellerID;
    }
    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }


    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public Item(){
        super();
    }

    public Item(int itemID, String ItemName,int SellerID){
        super();
        this.itemID = itemID;
        this.itemName = ItemName;
        this.sellerID = SellerID;
    }

    public Item(int itemID, String ItemName,int SellerID, int Price, String Contact, String Description, int Status){
        super();
        this.itemID = itemID;
        this.itemName = ItemName;
        this.sellerID = SellerID;
        this.price = Price;
        this.contact = Contact;
        this.description = Description;
        this.status = Status;
    }
//
//    public Item(int itemID, String ItemName,String itemPicture, int SellerID,int Price,String Contact,String Description, int status) {
//        this.itemID = itemID;
//        this.itemName = ItemName;
//        this.itemPicture = itemPicture;
//        this.sellerID = SellerID;
//        this.price = Price;
//        this.contact = Contact;
//        this.description = Description;
//        this.status = status;
//    }


}