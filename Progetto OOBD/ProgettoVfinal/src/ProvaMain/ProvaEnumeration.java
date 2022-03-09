package ProvaMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

import Controller.Controller;
import Model.*;

public class ProvaEnumeration {

    private static BufferedReader reader;

    public static void main(String[] args) {


        EnumPrefissoNumero[] arrayEnumPrefissi = EnumPrefissoNumero.class.getEnumConstants();
        for (EnumPrefissoNumero enumPrefissoNumero : arrayEnumPrefissi) {
        	String prefisso = new String (enumPrefissoNumero.getPrefisso().toString());
        	System.out.println(prefisso.substring(prefisso.indexOf('+')+1));
           // System.out.println(enumPrefissoNumero.getPrefisso().toString());
        }
        System.out.println("prova enum:"+ EnumPrefissoNumero.Italia.getPrefisso());
        System.out.println("is uguale a Italia: "+isPrefissoNumero(EnumPrefissoNumero.Italia.getPrefisso()));


}

    public static boolean isPrefissoNumero(String value) {
        EnumPrefissoNumero[] arrayEnumPrefissi = EnumPrefissoNumero.class.getEnumConstants();
        for (EnumPrefissoNumero enumPrefissoNumero : arrayEnumPrefissi) {
            if(enumPrefissoNumero.getPrefisso().equals(value))  return true; 
          }
          return false;
        }


}