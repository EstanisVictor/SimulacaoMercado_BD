package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import ferramentas.FabricaConexao;
import model.ProdutoEstoque;
import model.ProdutoVendido;
import model.Venda;

public class ProdutoVendidoDao {
  
  public static boolean cadastraProdutoVenda(Venda vendaProdutos){
    try (Connection con = FabricaConexao.getConexaoPostgres()) {
      String sql = "INSERT INTO produtovendido(id_venda, id_produto, quantidade, precodia) VALUES(?, ?, ?, ?)";

      
      for (ProdutoVendido prod : vendaProdutos.getProdutosComprados()) {
        PreparedStatement trans = con.prepareStatement(sql);
        trans.setInt(1, vendaProdutos.getId());
        trans.setInt(2, prod.getProdutoLoja().getId());
        trans.setInt(3, prod.getQuantidadeVendida());
        trans.setDouble(4, prod.getPrecoPraticado());

        trans.execute();
      }
      return true;
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static Vector<ProdutoVendido> buscaProdutosVenda(int idVenda){
    Vector<ProdutoVendido> vetProd = new Vector<>();
    try (Connection con = FabricaConexao.getConexaoPostgres()) {
      String sql = "SELECT * FROM produtovendido WHERE id_venda = ?";

      PreparedStatement trans = con.prepareStatement(sql);
      trans.setInt(1, idVenda);

      ResultSet tuplas = trans.executeQuery();

      while(tuplas.next()){
        ProdutoEstoque associado = new ProdutoEstoque();
        associado.setId(tuplas.getInt("id_produto"));

        ProdutoVendido temp = new ProdutoVendido(associado, tuplas.getInt("quantidade"), 
        tuplas.getDouble("precodia"));
        vetProd.add(temp);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return vetProd;
  }
}
