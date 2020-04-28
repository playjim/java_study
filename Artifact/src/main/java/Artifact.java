/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;

import static java.lang.System.out;

/**
 *
 * @author playjim
 */
public class Artifact {
    int id;
    String cellar;
    int century;
    
    public Artifact(int id) {
        this.id = id;
}
    public Artifact(int id, String cellar){
        this.id = id;
        this.cellar = cellar;
    }
    
    public Artifact(int id, String cellar, int century){
        this.id = id;
        this.cellar = cellar;
        this.century = century;
    }

    public static void printC(Artifact name){
        System.out.print(name.id +" ");
        if(name.cellar != null) {
            System.out.print(name.cellar + " ");
        }
        if(name.century != 0) {
            System.out.print(name.century + " ");
        }
    }

    public static void  main(String[] args){
        Artifact number_one = new Artifact(212121);
        Artifact number_two = new Artifact(212122,"Ацтеки");
        Artifact number_three = new Artifact(212123,"КУкухи",21);
        printC(number_three);
    }
}
