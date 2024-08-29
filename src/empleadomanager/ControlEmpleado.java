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
            System.out.println("\n**** MENU ****");
            System.out.println("1- Agregar Empleado"+
                               "\n2- Listar Empleado"+
                               "\n3- Despedir Empleado"+
                               "\n4- Empleado Activo"+
                               "\n5- Salir");
            System.out.println("Ingrese una de las opciones: ");
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
                    System.out.println("Ingrese codigo del empleado: ");
                    int id=leer.nextInt();
                    emp.EmpDespedir(id);
                    break;
                case 4:
                     System.out.println("Ingrese el codigo para ver el empleado activo: ");
                     int code=leer.nextInt();
                     boolean activo=emp.EmpActivo(code);
                     if(activo){
                         System.out.println("El empleado con el codigo: "+code+" Se encuentra Activo");
                     }else{
                         System.out.println("El empleado con el codigo: "+code+" No se encuentra Activo");
                     }
                    break;
            }
        }while(opcion!=5);
    }
    
}
