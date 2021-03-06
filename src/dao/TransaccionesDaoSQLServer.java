/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Categoria;
import dto.Cuenta;
import dto.Transacciones;
import dto.TransaccionesListas;
import java.sql.Date;
import java.util.ArrayList;
import java.sql.ResultSet;

public class TransaccionesDaoSQLServer extends TransaccionesDao {
    
    @Override
    public boolean insert(Transacciones obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        
        boolean id;
        
        StringBuilder query = new StringBuilder("{call sp_transaccionesInsert(");
        query.append(obj.getIdCategoria() + ",");
        query.append(obj.getMonto() + ",");
        query.append("'" + obj.getFecha() + "',");
        query.append("'" + obj.getDescripcion() + "',");
        query.append(obj.getIdCuenta() + ",");
        query.append("'" + obj.getHora()+ "')");
        query.append("}");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == false) {
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return true;
    }
    
    @Override
    public int update(Transacciones obj) throws Exception {
        return 0;
    }
    
    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuffer query = new StringBuffer("{call sp_transaccionesDelete(");
        query.append(id + ")");
        query.append("}");
        objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }
    
    @Override
    public Transacciones get(int id) {
        
        return null;
    }
    
    @Override
    public ArrayList<TransaccionesListas> getList() {
        ArrayList<TransaccionesListas> registros = new ArrayList<>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "{call sp_transaccionesSelectAll()}";
            
            ResultSet objResultSet = objConexion.ejecutarSelect(query);
            while (objResultSet.next()) {
                TransaccionesListas obj = new TransaccionesListas();
                
                int id_Transacciones = objResultSet.getInt("id_transacciones");
                obj.setIdTransacciones(id_Transacciones);
                
                int idCategoria = objResultSet.getInt("id_categoria");
                obj.setIdCategoria(idCategoria);
                
                String _nombre_Categoria = objResultSet.getString("nombre_categoria");
                obj.setNombreCategoria(_nombre_Categoria);
                
                float monto = objResultSet.getFloat("monto");
                obj.setMonto(monto);
                
                String descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(descripcion);
                
                int idCuenta = objResultSet.getInt("id_cuenta");
                obj.setIdCuenta(idCuenta);
                
                String Nombre_cuenta = objResultSet.getString("nombre_cuenta");
                obj.setNombreCuenta(Nombre_cuenta);
                
                Date date = objResultSet.getDate("fecha");
                obj.setFecha(date);
                
                String hora = objResultSet.getString("hora");
                obj.setHora(hora);
                
                registros.add(obj);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return registros;
    }
    
}
