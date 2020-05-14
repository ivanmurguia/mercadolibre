package com.cloe.controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
  private Connection con = null;
  public int numeroColumnas;
  public Statement sentencia;
  public ResultSet resultado;
  
  private String URLconexion() {
  	return "jdbc:sqlserver://192.168.1.26;databaseName=OE_MODA2017;user=sa;password=B1Admin";
//  	return "jdbc:sqlserver://192.168.1.26;databaseName=Oe_Moda_RetailPruebas;user=sa;password=B1Admin";
  }
  public Connection conectar() {
      try {
	     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	     con = java.sql.DriverManager.getConnection(URLconexion());
      } catch (Exception e) {
      }
      return con;
  }
  public void desconectar() {
      try {
          if (con != null) {
              con.close();
          }
          con = null;
      } catch (Exception e) {
      }
  }
  public ResultSet consultar(String sql) {
      try {
          sentencia = conectar().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
          resultado = sentencia.executeQuery(sql);
      } catch (SQLException e) {
    	  System.out.println("Error ivan: " + e);
      }
      return resultado;
  }
  public String[] columnas() {
	  try {
		  ResultSetMetaData metaDatos;
		  metaDatos = resultado.getMetaData();
		  numeroColumnas = metaDatos.getColumnCount();
		  String[] columnas = new String[numeroColumnas];
		  for (int i = 0; i < numeroColumnas; i++)
		  {
			  columnas[i] = metaDatos.getColumnLabel(i + 1); 
		  }
		  return columnas;
	  } catch (SQLException e) {
		  return null;
	  }
  }
  public int filas() {
	  int filas = 0;
	  try {
		while(resultado.next()) {
			filas++;
		}
	} catch (SQLException e) {

	}
		return filas;
  }
  public boolean ejecutar(String sql) {
      try {
          sentencia = conectar().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
          sentencia.executeUpdate(sql);
          sentencia.close();
      } catch (SQLException e) {
    	  return false;
      }
      return true;
  }
}