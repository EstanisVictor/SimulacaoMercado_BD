package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import ferramentas.FabricaConexao;
import model.Cliente;

public class ClienteDao {
  public static boolean insereCliente(Cliente novo){        
        
    try(Connection con = FabricaConexao.getConexaoPostgres()){
    
      String sql = "INSERT INTO cliente(nome,endereeco,tel) VALUES(?,?,?)"; 
        
      PreparedStatement trans = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
      trans.setString(1, novo.getNome());
      trans.setString(2, novo.getEndereco());
      trans.setString(3, novo.getTel());
      trans.execute();
      ResultSet tupla = trans.getGeneratedKeys();
      if(tupla.next()){
        novo.setId(tupla.getInt("id"));
        return true;
      }else{
        return false;
      }  
    }catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
  }

  public static Vector<Cliente> consultaClienteNome(String nome){
    Vector<Cliente> clientes = new Vector<>();

    try(Connection con = FabricaConexao.getConexaoPostgres()){
    
    String sql = "SELECT * FROM cliente WHERE nome=?"; 
       
    PreparedStatement trans = con.prepareStatement(sql); 
    trans.setString(1, nome);

    ResultSet tuplas = trans.executeQuery();
    while(tuplas.next()){
      Cliente temp = new Cliente(tuplas.getInt("id"), nome,
      tuplas.getString("endereeco"), tuplas.getString("tel"));

      clientes.add(temp);
    }
       
   }catch (SQLException e) {
       e.printStackTrace();
   }
   return clientes;
  }

  public static boolean atualiaCliente(Cliente usuario){
    try (Connection con = FabricaConexao.getConexaoPostgres()) {
      String sql = "UPDATE cliente SET nome=?, endereeco=? WHERE id=?";

      PreparedStatement trans = con.prepareStatement(sql);
      trans.setString(1, usuario.getNome());
      trans.setString(2, usuario.getEndereco());
      trans.setString(3, usuario.getTel());
      trans.setInt(4, usuario.getId());

      return trans.executeUpdate() > 0 ? true : false;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static Cliente buscaClienteId(int id){
    Cliente resultado = null;

    try (Connection con = FabricaConexao.getConexaoPostgres()) {
      
      String sql = "SELECT * FROM cliente WHERE id=?";
      PreparedStatement trans = con.prepareStatement(sql);

      trans.setInt(1, id);
      ResultSet tupla = trans.executeQuery();

      if(tupla.next()){
        resultado = new Cliente(tupla.getInt("id"), tupla.getString("nome"), 
        tupla.getString("endereeco"), tupla.getString("tel"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return resultado;
  }
}
