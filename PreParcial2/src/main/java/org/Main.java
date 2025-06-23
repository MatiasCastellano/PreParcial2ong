package org;

import org.dto.ResultadoDTO;
import org.service.Logica;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        boolean continuar=true;
        Logica logica= Logica.getInstance();
        ResultadoDTO resultado= new ResultadoDTO();
        Scanner in=new Scanner(System.in);
        while(continuar){

            mostrarMenu();
            int accion= in.nextInt();
            switch (accion){
                case 1:
                    in.nextLine();
                    System.out.println("Ingrese el nombre del donante");
                    String nombre= in.nextLine();
                    
            }
        }
    }
    private static void mostrarMenu () {
        System.out.println("Ingrese la opcion que desea hacer:");
        System.out.println("1. Crear Donacion");
        System.out.println("2. ..");
        System.out.println("3. ..");
        System.out.println("4. Salir");
    }
}