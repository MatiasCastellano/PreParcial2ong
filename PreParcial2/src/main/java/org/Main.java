package org;

import org.dto.AsignarDonacionDTO;
import org.dto.CrearDonacionDTO;
import org.dto.ResultadoDTO;
import org.dto.TotalRecaudadoPorTipoDTO;
import org.models.Donacion;
import org.service.Logica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        boolean continuar=true;
        Logica logica= Logica.getInstance();
        ResultadoDTO resultado= new ResultadoDTO();
        Scanner in=new Scanner(System.in);
    try {
        while (continuar) {
            mostrarMenu();
            int accion = in.nextInt();
            switch (accion) {
                case 1:
                    in.nextLine();
                    System.out.println("Ingrese el nombre del donante");
                    String nombre = in.nextLine();
                    System.out.println("Ingrese el tipo del donante: 1) Individual 2) Compania");
                    Donacion.Tipo tipoEnum = null;
                    int tipo = in.nextInt();
                    if (tipo == 1) {
                        tipoEnum = Donacion.Tipo.INDIVIDUAL;
                    } else if (tipo == 2) {
                        tipoEnum = Donacion.Tipo.COMPANY;
                    } else {
                        tipoEnum = Donacion.Tipo.INDIVIDUAL; //por defecto se asigna individual
                    }
                    System.out.println("Ingrese el monto");
                    BigDecimal monto = in.nextBigDecimal();
                    in.nextLine();
                    System.out.println("Ingrese la fecha de donacion: yyyy-MM-dd");
                    String fechaD = in.nextLine();
                    LocalDate fecha = LocalDate.parse(fechaD);
                    System.out.println("Ingrese la categoria");
                    String categoria = in.nextLine();
                    CrearDonacionDTO crearDonacion = new CrearDonacionDTO(nombre, tipoEnum, monto, fecha, categoria);
                    resultado = logica.crearDonacion(crearDonacion);
                    System.out.println(resultado.getMessage());
                    break;
                case 2:
                    System.out.println("Ingrese el id de donacion a asignar");
                    long id = in.nextLong();
                    in.nextLine();
                    System.out.println("Ingrese la fecha de asignacion: yyyy-MM-dd");
                    String fechaA = in.nextLine();
                    LocalDate fechaAsig = LocalDate.parse(fechaA);
                    System.out.println("Ingrese las notas");
                    String notas = in.nextLine();
                    AsignarDonacionDTO parametros= new AsignarDonacionDTO(id,fechaAsig,notas);
                    resultado= logica.asignarDonacion(parametros);
                    System.out.println(resultado.getMessage());
                    break;
                case 3:
                    List<TotalRecaudadoPorTipoDTO> resultados= logica.consultaTotalTipoDonante();
                    imprimir(resultados);

                case 5:
                    continuar=false;
                    break;
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    }
    private static void mostrarMenu () {
        System.out.println("Ingrese la opcion que desea hacer:");
        System.out.println("1. Crear Donacion");
        System.out.println("2. Asignar Donacion");
        System.out.println("3. Consultar total recaudado por tipo");
        System.out.println("5. Salir");
    }
    private static void imprimir(List<TotalRecaudadoPorTipoDTO> resultado){
        for(TotalRecaudadoPorTipoDTO r: resultado){
            System.out.println("Tipo: "+ r.getTipoDonacion());
            System.out.println("Cantidad: "+ r.getCantidad());
            System.out.println("Total: "+ r.getMonto());
        }

    }
}