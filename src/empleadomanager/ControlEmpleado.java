/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empleadomanager;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Balto
 */
public class ControlEmpleado {
    public static void main(String[] args) throws IOException {
        Scanner leer = new Scanner(System.in);
        EmpleadoManager emp= new EmpleadoManager();
        int opcion=0;
        do{
            System.out.println("\n\nMENU\n-----------");
            System.out.println("1- Agregar Empleado"+
                    "\n2- Listar Empleado"+
                    "\n3- Despedir Empleado"+
                    "\n4- Empleado Activo"+
                    "\n5- Salir");
            opcion=leer.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Ingrese su nombre: ");
                    String name=leer.next();
                    System.out.println("Ingrese su salario: ");
                    double sala=leer.nextDouble();
                    emp.addEmpleado(name, sala);
                    break;
                case 2:
                    System.out.println("Listas de los empleados: ");
                    emp.list();
                    break;
                case 3: 
                   
                    break;
                case 4:
                    
                    break;
                    
            }
        }while(opcion!=5);
    }
    
}
