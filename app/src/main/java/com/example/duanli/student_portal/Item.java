package com.example.duanli.student_portal;

import java.io.Serializable;

/**
 * Created by duanli on 10/23/16.
 */

public class Item implements Serializable {

    private int itemID;
    private String itemPicture;
    private int Seller;
    private String sellerName;
    private String itemName;
    private String price;
    private String contact;
    private String description;
    private int status; //0 available 1 reserved 2 resolved

    public String getSellerName() {
        return sellerName;
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
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

    public Item(){}

    public Item(String SellerName,String ItemName,String Price,String Contact,String Description) {
        sellerName = SellerName;
        itemName = ItemName;
        price = Price;
        contact = Contact;
        description = Description;
    }

    public boolean addItem(){
//        DatabaseManager DbMan = DatabaseManager.getInstance();
//        DbMan.addItem(sellerName,itemName,price,contact,description);
        return true;
    }

}
