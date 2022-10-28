/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wfdr.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Wfdr.model.RinvoiceTableModel;
import Wfdr.model.RinvoiceHeader;
import Wfdr.model.RitemLine;
import Wfdr.model.RitemTableModel;
import Wfdr.view.RinvFrame;
import Wfdr.view.RinvoiceHeaderDialog;
import Wfdr.view.RinvoiceLineDialog;
import javax.swing.table.TableModel;


/**
 *
 * @author -Ragaa ELfeky
 */
public class Rinvcontroller implements ActionListener, ListSelectionListener{
    
      private RinvFrame frame;
       private RinvoiceHeaderDialog headerDialog;
    private RinvoiceLineDialog lineDialog;

    public Rinvcontroller(RinvFrame frame) {
        this.frame = frame;
    }
      @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Row Selected");
        int selectedRow = frame.getInvTable().getSelectedRow();
        if (selectedRow == -1) {
            frame.getCustomerName().setText("");
            frame.getInvNum().setText("");
            frame.getInvoiceTotal().setText("");
            frame.getInvoiceDate().setText("");
            frame.setRitemTableModel(new RitemTableModel());
        } else {
            RinvoiceHeader selectedInvoice = frame.getInvoices().get(selectedRow);
            frame.getCustomerName().setText(selectedInvoice.getCustomerName());
            frame.getInvNum().setText("" + selectedInvoice.getInvNum());
            frame.getInvoiceTotal().setText("" + selectedInvoice.getTotal());
            frame.getInvoiceDate().setText(RinvFrame.sdf.format(selectedInvoice.getInvDate()));
            frame.setRitemTableModel(new RitemTableModel(selectedInvoice.getLines()) {});
        }
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
         //System.out.println("Hello " + ac);
          switch (ac) {
            case "New Invoice":
                newInvoice();
                break;

            case "Delete Invoice":
                deleteInvoice();
                break;

            case "New Item ":
                newItem();
                break;

            case "Delet Item":
                deletItem();
                break;

            case "Load File":
                loadFile(null, null);
                break;

            case "Save File":
                saveFile();
                break;
                 case "newInvoiceOK":
                newInvoiceOK();
                break;

            case "newInvoiceCancel":
                newInvoiceCancel();
                break;

            case "newItemOK":
                newItemOK();
                break;

            case "newItemCancel":
                newItemCancel();
                break;
        }
    }
    
  private void newInvoice() {
       RinvoiceHeaderDialog headerDialog = new RinvoiceHeaderDialog(frame);
        headerDialog.setVisible(true);
    }

    private void deleteInvoice() {
        int selectedRow=frame.getInvTable().getSelectedRow();      
        if (selectedRow != -1) {
            frame.getInvoices().remove(selectedRow);
            frame.getRinvoiceTableModel().fireTableDataChanged();
        }
        
    }

   

    private void saveFile() {
        //To change body of generated methods, choose Tools | Templates.
    }

    public void loadFile(String hPath, String lPath) {
         System.out.println("Files will be loaded!");
        File hFile = null;
        File lFile = null;
        if (hPath == null && lPath == null) {
            JFileChooser fc = new JFileChooser();
            JOptionPane.showMessageDialog(frame, "Please, select Header file!", "Header", JOptionPane.WARNING_MESSAGE);
            int result = fc.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                hFile = fc.getSelectedFile();
                JOptionPane.showMessageDialog(frame, "Please, select Line file!", "Line", JOptionPane.WARNING_MESSAGE);
                result = fc.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    lFile = fc.getSelectedFile();
                     }
            }
        } else {
            hFile = new File(hPath);
            lFile = new File(lPath);
        }
         if (hFile != null && lFile != null) {
            try {
                List<String> hData = readFile(hFile);
                /*  "1,12-11-2020,Ali"
                    "2,23-10-2021,Saleh"
                **/
                 List<String> lData = readFile(lFile);
                 /*
" 1 , Mobile , 3200 , 1"
 "1 , Cover , 20 , 2"
"1 , Headphone , 130 , 1"
"2 , Laptop , 4000 , 1"
"2 , Mouse , 35 , 1"
**/
         System.out.println("point");
          for (String header : hData) {
              String[] segments = header.split(",");
               int num = Integer.parseInt(segments[0]);
                    Date date = new Date();
                    try {
                        date = frame.sdf.parse(segments[1]);
                         } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(frame, "Error while parsing date: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    String name = segments[2];
                    RinvoiceHeader inv = new RinvoiceHeader(num, date, name);
                    frame.getInvoices().add(inv);
                    System.out.println("point");
                }
                frame.setRinvoiceTableModel(new RinvoiceTableModel(frame.getInvoices()));
 for (String line : lData) {
                    String[] segments = line.split(",");
                    int num = Integer.parseInt(segments[0]);
                    String name = segments[1];
                    int price = Integer.parseInt(segments[2]);
                    int count = Integer.parseInt(segments[3]);
                    RinvoiceHeader invoice = frame.getInvoiceByNum(num);
                    RitemLine invLine = new RitemLine(name, price, count, invoice);
                }
                System.out.println("point");
} catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error while reading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private List<String> readFile(File f) throws IOException{
         FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        List<String> lines = new ArrayList<>();
        String line = null;
         while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        return lines;
    
        //To change body of generated methods, choose Tools | Templates.
    }

   

        //To change body of generated methods, choose Tools | Templates.

  
    

    private void newItemOK() {
         String name = lineDialog.getItemNameField().getText();
        int count = Integer.parseInt(lineDialog.getItemCountField().getText());
        int price = Integer.parseInt(lineDialog.getItemPriceField().getText());
        int selectedInvIndex = frame.getInvTable().getSelectedRow();
        RinvoiceHeader inv = frame.getInvoices().get(selectedInvIndex);
        
        new RitemLine(name, price, count, inv);
        frame.getRinvoiceTableModel().fireTableDataChanged();
        frame.getInvTable().setRowSelectionInterval(selectedInvIndex, selectedInvIndex);
        newItemCancel();
        //To change body of generated methods, choose Tools | Templates.
    }
 private void newItemCancel() {
         lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

    private void newInvoiceCancel() {
         try {
            String dateStr = headerDialog.getInvDateField().getText();
            String name = headerDialog.getCustNameField().getText();

            Date date = frame.sdf.parse(dateStr);
            int num = frame.getNextInvNum();
            RinvoiceHeader inv = new RinvoiceHeader(num, date, name);
            frame.getInvoices().add(inv);
            frame.getRinvoiceTableModel().fireTableDataChanged();
            newInvoiceCancel();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Error in Date format", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
    }

    private void newInvoiceOK() {
        
        headerDialog.setVisible(false);
        headerDialog.dispose();
        headerDialog = null;
        
        
        
    }
private void newItem() {
       int selectedInvoice = frame.getInvTable().getSelectedRow();
        if (selectedInvoice == -1) {
            JOptionPane.showMessageDialog(frame, "First, select Invoice to add item to it", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            lineDialog = new RinvoiceLineDialog(frame);
            lineDialog.setVisible(true);//To change body of generated methods, choose Tools | Templates.
    }

   
         //To change body of generated methods, choose Tools | Templates.
    }

    private void deletItem() {
       int selectedInvoice = frame.getInvTable().getSelectedRow();
        int selectedItem = frame.getItemTable().getSelectedRow();
        if (selectedInvoice != -1 && selectedItem != -1) {
           RinvoiceHeader invoice = frame.getInvoices().get(selectedInvoice);
            invoice.getLines().remove(selectedItem);
            frame.getRitemTableModel().fireTableDataChanged();
            frame.getRinvoiceTableModel().fireTableDataChanged();
            frame.getInvTable().setRowSelectionInterval(selectedInvoice, selectedInvoice);
        }
    }

   

   
          }
        
    
        
    


    

  

    

    

   
   
    
