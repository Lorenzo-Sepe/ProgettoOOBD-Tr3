package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class A extends JTable{

    JFrame frame = new JFrame();
    Object data[][] = {{"1","Jahanzeb"},{"2","Ahmed"},{"3","Shaikh"}};
    String col[] = {"#","Names"};
    DefaultTableModel tableModelloNO = new DefaultTableModel(data, col) {
        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }};
    JTable table = new JTable(tableModelloNO);
    JScrollPane scroll = new JScrollPane(table);
    
    
    public static void main(String arg[]){
        new A();
    }

    public A() {

        table.addMouseListener(new Click());
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scroll);
        frame.add(table);
        frame.setVisible(true);
        table.setFocusable(false);

        table.setModel(tableModelloNO);
        
        
    }


    
   
    class Click extends MouseAdapter{
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount()==2)
                System.out.println(table.getSelectedRow());
        }
    }
}