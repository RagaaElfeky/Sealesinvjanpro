/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wfdr.model;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author -
 */
public class RinvoiceHeader { 
     private int invNum;
    private Date invDate;
    private String customerName;
    private ArrayList<RitemLine> lines;

    public RinvoiceHeader(int invNum, Date invDate, String customerName) {
        this.invNum = invNum;
        this.invDate = invDate;
        this.customerName = customerName;
    }

    public double getTotal() {
          double total = 0.0;
        
        for (RitemLine line : getLines()) {
            total += line.getItemTotal();
        }
        
        return total;
    }
    
    public ArrayList<RitemLine> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
         this.customerName = customerName;
    }

    public int getInvNum() {
        return invNum;
    }

    public void setInvNum(int invNum) {
        this.invNum = invNum;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }
      @Override
    public String toString() {
        return "RinvoiceHeader{" + "invnum=" + invNum + ", customerName=" + customerName + ", invDate=" + invDate + '}';
    }
}
