package GUI;

import javax.swing.JFrame;

import Controller.Controller;
import GUI.Home;

public class Main{
    public static void main(String[] args) {
        JFrame Frame=new JFrame();
        Controller c= new Controller();
        c.dumpDati();
        JFrame  frameHome= new Home(c);
        frameHome.setVisible(true);

    }
}