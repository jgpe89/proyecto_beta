/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiondb;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Carrera extends Alumno
{
    private String carrera;
    private String descripcion;
    
    public Carrera()
    
    {
        
    }

     public Carrera(int id)
    {
        Conexion con;         
        con = new Conexion();
        con.conectate();        
        Connection conx=con.getConn();
               
        try
        {
                Statement st = conx.createStatement();
                ResultSet res = st.executeQuery("SELECT * FROM carrera WHERE id_carrera="+String.valueOf(id));

                if (res.next()) {

                    this.setNombre(res.getString("descripcion")); 

               }
          conx.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }     
           
   }
     
      public Carrera(String descripcion)
    {
          this.descripcion=descripcion;
        
    }
      
    public String getCarrera() {
        return carrera;
    }   
   
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    
    @Override
    public int crear()
    {
        int retorno=0;
        Conexion con=new Conexion();         
        con.conectate();        
        Connection conx=con.getConn();
        
        try
        {
                Statement st = conx.createStatement();
                String sqlInsert="INSERT INTO carrera VALUES (default,?);";
                PreparedStatement pstmt=conx.prepareStatement(sqlInsert,PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, this.descripcion);
               
                int n=pstmt.executeUpdate();
                
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Guardado con Exito");
                }
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs != null && rs.next()) {
                     this.setId( rs.getInt(1));
                }
                else
                {
                    retorno=-1;
                }
               
                
                conx.close();
        }catch(SQLException | HeadlessException e)
        {
            System.out.println(e.getMessage());
            retorno=-1;
        }
        
        return retorno;
    }
    
    
    //inicio de update
    
    @Override
      public void actualizar() {
        Conexion con=new Conexion();         
        con.conectate();        
        Connection conx=con.getConn();
        
        try
        {
                Statement st = conx.createStatement();                

            
                conx.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            
        }
             
        }  
    //fin del update
    
    
    
   @Override
    public void eliminar() {
        
        Conexion con=new Conexion();         
        con.conectate();        
        Connection conx=con.getConn();
               
        try
        {
                Statement st = conx.createStatement();                
                st.execute("delete from carrera where id_carrera="+this.getId());        
                conx.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            
        }
        
    }




}

  
    

