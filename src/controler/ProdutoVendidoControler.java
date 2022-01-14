package controler;

import java.util.Vector;

import model.ProdutoVendido;
import model.Venda;
import model.dao.ProdutoVendidoDao;

public class ProdutoVendidoControler {
  
  public static boolean cadastroProdutosVenda(Venda vendaProdutos){

    if(vendaProdutos != null && vendaProdutos.getId() > 0){
      if(!vendaProdutos.getProdutosComprados().isEmpty()){
        return ProdutoVendidoDao.cadastraProdutoVenda(vendaProdutos);
      }
      return true;
    }else{
      return false;
    }
  }

  public static Vector<ProdutoVendido> buscaProdutosVenda(Venda vendaAtual){

    if(vendaAtual != null){
      Vector<ProdutoVendido>prods = ProdutoVendidoDao.buscaProdutosVenda(vendaAtual.getId());
      for(ProdutoVendido p : prods){
        p.setProdutoLoja(ProdutoEstoqueControler.buscaProdutoId(p.getProdutoLoja().getId()));
      }
      return prods;
    }
    return null;
  }
}
