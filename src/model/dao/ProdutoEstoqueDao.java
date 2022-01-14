package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import ferramentas.FabricaConexao;
import model.ProdutoEstoque;

public class ProdutoEstoqueDao {
  //interrogação indica os valores que vão retornar
  private static final String INSERT = "INSERT INTO produto(nomee, preco, quantidade) VALUES(?,?,?)";

  public static boolean insereProduto(ProdutoEstoque novo){
    Vector<ProdutoEstoque> vet = new Vector<>();
    vet.add(novo);
    return insereLote(vet);
  }

  public static boolean carregaProdutoManual(ProdutoEstoque novo){
    try (Connection con = FabricaConexao.getConexaoPostgres()) {

      PreparedStatement trans = con.prepareStatement(INSERT);
        
      trans.setString(1, novo.getNome());
      trans.setDouble(2, novo.getPreco());
      trans.setInt(3, novo.getQuantidade());
      
      trans.execute();

      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean insereLote(Vector<ProdutoEstoque> produtos){
    try (Connection con = FabricaConexao.getConexaoPostgres()) {
      //configurar a transação que será executada
      //isso criará a transação, usando o priparedStatement
      for (ProdutoEstoque novo : produtos) {
        PreparedStatement trans = con.prepareStatement(INSERT);
        //setando os valores das interrogaçõees
        trans.setString(1, novo.getNome());
        trans.setDouble(2, novo.getPreco());
        trans.setInt(3, novo.getQuantidade());
      
        trans.execute();
      }
      

      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public static boolean updateProduto(ProdutoEstoque prod){
    try (Connection con = FabricaConexao.getConexaoPostgres()) {
      String sql = "UPDATE produto SET nomee=?, preco=?, quantidade=? WHERE id=?";
      PreparedStatement trans = con.prepareStatement(sql);
      trans.setString(1, prod.getNome());
      trans.setDouble(2, prod.getPreco());
      trans.setInt(3, prod.getQuantidade());
      trans.setInt(4, prod.getId());
      return trans.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static ProdutoEstoque consultaProduto(int id){
    ProdutoEstoque resultado = null;

    try (Connection con = FabricaConexao.getConexaoPostgres()) {
      String sql = "SELECT * FROM produto WHERE id=?";
      
      PreparedStatement trans = con.prepareStatement(sql);
      trans.setInt(1, id);
      
      ResultSet tuplas = trans.executeQuery();

      if(tuplas.next()){
        resultado = new ProdutoEstoque(id, tuplas.getString("nomee"), 
        tuplas.getInt("quantidade"), tuplas.getDouble("preco"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return resultado;
  }

  public static ProdutoEstoque consultaNomeProduto(String nome){
    ProdutoEstoque resultado = null;

    try (Connection con = FabricaConexao.getConexaoPostgres()) {
      String sql = "SELECT * FROM produto WHERE nomee=?";
      
      PreparedStatement trans = con.prepareStatement(sql);
      trans.setString(1, nome);
      
      ResultSet tuplas = trans.executeQuery();

      if(tuplas.next()){
        resultado = new ProdutoEstoque(tuplas.getInt("id"), nome, 
        tuplas.getInt("quantidade"), tuplas.getDouble("preco"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return resultado;
  }
}
