package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import ferramentas.FabricaConexao;
import model.Cliente;
import model.Venda;

public class VendaDao {
  
  public static boolean cadastroVeenda(Venda nova){
    try (Connection con = FabricaConexao.getConexaoPostgres()) {
      String sql = "INSERT INTO venda(dia, total, id_usuario) VALUES(?, ?, ?)";

      PreparedStatement trans = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      trans.setTimestamp(1, new Timestamp(nova.getDiaCompra().getTime()));
      trans.setDouble(2, nova.getTotalCompra());
      trans.setInt(3, nova.getUsuario() == null ? 0 : nova.getUsuario().getId());
      
      trans.execute();
      
      ResultSet tupla = trans.getGeneratedKeys();
      if (tupla.next()) {
        nova.setId(tupla.getInt("id"));
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static Venda buscaVendaId(int id){
    Venda resultado = null;
    
    try (Connection con = FabricaConexao.getConexaoPostgres()) {
      String sql = "SELECT * FROM venda WHERE id=?";

      PreparedStatement trans = con.prepareStatement(sql);
      trans.setInt(1, id);

      ResultSet tupla = trans.executeQuery();

      if(tupla.next()){
        Cliente temp = new Cliente();
        temp.setId(tupla.getInt("id_usuario"));
        resultado = new Venda(id, null, new Date(tupla.getTimestamp("dia").getTime()),
        temp, tupla.getDouble("total"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return resultado;
  }
}
