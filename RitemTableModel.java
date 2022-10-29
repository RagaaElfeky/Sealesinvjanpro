/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wfdr.model;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author -
 */
public class RitemTableModel extends AbstractTableModel  {
     private ArrayList<RitemLine> items;
    private String[] columns = {"Name", "Price", "Count", "Total"};

    public RitemTableModel(ArrayList<RitemLine> items) {
        this.items = items;
    }

    public RitemTableModel() {
        
    }
    
    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RitemLine item = items.get(rowIndex);
        switch (columnIndex) {
            case 0: return item.getItemNum();
            case 1: return item.getItemName();
            case 2: return item.getItemPrice();
            case 3: return item.getItemCount();
            case 4: return item.getItemTotal();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
 

