package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.Controller;

// TODO: Auto-generated Javadoc

public class MAIN {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Controller c=new Controller();
                    System.out.println("dump dati iniziato  sono fuori dal controller ");

                    c.dumpDati();
                    System.out.println("dump dati finito sono fuori dal controller ");
                    Home window = new Home(c);
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}