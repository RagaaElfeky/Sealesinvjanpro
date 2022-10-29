/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wfdr.model;

/**
 *
 * @author -
 */
public class RitemLine {
       private String itemName;
    private double itemPrice;
    private int itemCount;
    private int itemNum;
    private RinvoiceHeader invoice;

    public RitemLine(String itemName, double itemPrice, int itemCount, RinvoiceHeader invoice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
           this.invoice = invoice;
    }
    public int getItemNum() {
        return itemNum;
    }
     public void setItemNum(int itemNo) {
        this.itemNum = itemNum;
    }

    public double getItemTotal() {
        return itemPrice * itemCount;
    }
    
    public RinvoiceHeader getInvoice() {
        return invoice;
    }

    public void setInvoice(RinvoiceHeader invoice) {
        this.invoice = invoice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
     @Override
    public String toString() {
        return "InvoiceItem{" + ",itemNum="+itemNum+"itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemCount=" + itemCount +  '}';
    }
}
