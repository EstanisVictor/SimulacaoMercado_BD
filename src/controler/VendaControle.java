package controler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import model.Cliente;
import model.ProdutoVendido;
import model.Venda;
import model.dao.VendaDao;

public class VendaControle {
  public static boolean cadastroVenda(Venda nova){
    //para cada produto que esta sndo comprado, teremos que avaliar as quantidade que estão sendo compradas
    //checagem das quantidades
    for(ProdutoVendido prod : nova.getProdutosComprados()){
      if(prod.getQuantidadeVendida() > prod.getProdutoLoja().getQuantidade()){
        return false;
      }
    }
    //se tem quantidade no estoque, aqui é debitado dos produtos armazenados no estoque
    for(ProdutoVendido prod : nova.getProdutosComprados()){
      //pega o produto da loja e modifica a quantidade dele
      // é a quantidade que temos na loja subtraido pela quantidade que ta sendo comprada
      prod.getProdutoLoja().setQuantidade(prod.getProdutoLoja().getQuantidade() - prod.getQuantidadeVendida());
      if(!ProdutoEstoqueControler.atualizaProduto(prod.getProdutoLoja())){
        return false;
      }
    }
    //cadastro da nova venda para o banco
    if(VendaDao.cadastroVeenda(nova)){
      //cadastrando todos os produtos vendidos
      if(!ProdutoVendidoControler.cadastroProdutosVenda(nova)){
          return false;
      }
    }
    return true;
  }
  
  public static Venda buscavendaId(int id){
    if(id >0){
      //buscando o objeto da venda no banco
      Venda venda = VendaDao.buscaVendaId(id);

      //Buscar caso houver o cliente associado a venda
      Cliente temp = ClienteControler.buscaClienteId(id);
      venda.setUsuario(temp);

      //buscar todos os produtos vendidos ao cliente
      venda.setProdutosComprados(ProdutoVendidoControler.buscaProdutosVenda(venda));

      return venda;
    }else{
      return null;
    }
  }

  public static void notaFiscal(Venda recupera, Vector<Integer> quantidades){
    
    int numero = 1, passar=0;

    try{

      File arquivo = new File("notafiscal.txt");

      if (!arquivo.exists()) {
          arquivo.createNewFile();
      }

      FileWriter arq = new FileWriter(arquivo);
      BufferedWriter marcaEscrita = new BufferedWriter(arq);

      marcaEscrita.newLine();
      marcaEscrita.write("Cliente: "+recupera.getUsuario().getNome());
      marcaEscrita.newLine();
      marcaEscrita.write("=========================================================================");
      marcaEscrita.newLine();
      
      for (ProdutoVendido p : recupera.getProdutosComprados()) {

        marcaEscrita.write(numero+"   "+p.getProdutoLoja().getNome()
        +" --> "+p.getProdutoLoja().getPreco()+" * "+quantidades.get(passar));
        numero++;
        passar++;
        marcaEscrita.newLine();

      }

      marcaEscrita.write("=========================================================================");
      marcaEscrita.newLine();

      marcaEscrita.write("Total: R$"+String.format("%.2f",recupera.getTotalCompra()));
      
      marcaEscrita.close();
      arq.close();

  }catch(FileNotFoundException ex){
      ex.getMessage();
  }catch(IOException e){
      e.getMessage();
  }

  numero = 1;
  passar=0;
  System.out.println("\n\n");
  System.out.println("Cliente: "+recupera.getUsuario().getNome());
  System.out.println("=========================================================================");
  for (ProdutoVendido p : recupera.getProdutosComprados()) {

    System.out.println(numero+"   "+p.getProdutoLoja().getNome()
    +" --> "+p.getProdutoLoja().getPreco()+" * "+quantidades.get(passar));
    numero++;
    passar++;

  }
  System.out.println("=========================================================================");
  System.out.println("Total: R$"+String.format("%.2f",recupera.getTotalCompra()));
  }

}

