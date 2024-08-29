/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package empleadomanager;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Balto
 */
public class EmpleadoManager {
 private RandomAccessFile rcods,remps;
    public EmpleadoManager(){
        try{
            //1- Asegurar que el folder company existe
            File mf=new File("Company");
            mf.mkdir();
            //2- Instanciar los RAFs dentro de company
            rcods=new RandomAccessFile("Company/codigos.emp","rw");
            remps=new RandomAccessFile("Company/empleados.emp","rw");
            //3- Iinicializar el archivo de codigos 
            initCodes();
        }catch(IOException e){
            System.out.println("No deberia de pasar esto");
        }
    }
    
    private void initCodes()throws IOException{
        if(rcods.length()==0)
            rcods.writeInt(1);
    }
    
    private int getCode()throws IOException{
       rcods.seek(0);
       int code=rcods.readInt();
       rcods.seek(0);
       rcods.writeInt(code+1);
       return code;
    }
    
    public void addEmpleado(String name,double salario)throws IOException{
        /*
        Formato:
        codigo - int
        nombre - String
        salario - double
        fecha contratacion - Calendar - long
        fecha despido - Calendar - long
        */
        remps.seek(remps.length());
        int code=getCode();
        remps.writeInt(code);
        remps.writeUTF(name);
        remps.writeDouble(salario);
        remps.writeLong(Calendar.getInstance().getTimeInMillis());
        remps.writeLong(0);
        createEmployeeFolder(code);
        
    }
    private String employeeFolder(int code){
        return "Company/empleado"+code;
    }
    
    private void createEmployeeFolder(int code)throws IOException{
        File f=new File(employeeFolder(code));
        f.mkdir();
        createYearSalesFileFor(code);
    }
    
    private RandomAccessFile salesFileFor(int code)throws IOException{
        String dirPadre=employeeFolder(code);
        int yearActual=Calendar.getInstance().get(Calendar.YEAR);
        String path=dirPadre+"/ventas"+yearActual+".emp";
        return new RandomAccessFile(path,"rw");
    }
    
    private void createYearSalesFileFor(int code)throws IOException{
        RandomAccessFile ryear=salesFileFor(code);
        if(ryear.length()==0){
            for(int m=0;m<12;m++){
                ryear.writeDouble(0);
                ryear.writeBoolean(false);
            }
        }
    }
    
    public void list()throws IOException{
        remps.seek(0);
        while(remps.getFilePointer()<remps.length()){
            int code=remps.readInt();
            String name=remps.readUTF();
            double salary=remps.readDouble();
            Date fechaC= new Date(remps.readLong());
            long fechaD=remps.readLong();
            if(fechaD==0){
                System.out.println(code+" - "+name+" - Lps."+salary+" - "+fechaC);
            }else{
                System.out.println("No tenemos a ningun empleado en la lista...");
            }
        }
        
    }
    
    public boolean EmpDespedir(int code) throws IOException {
        if (EmpActivo(code) == false) {
            return false;
        } else {
            String name = remps.readUTF();
            remps.skipBytes(16);
            remps.writeLong(new Date().getTime());
            System.out.println("Despidiendo a "+name);
            return true;
        }
    } 
    
    public boolean EmpActivo(int code) throws IOException {

        return false;
    }
}
