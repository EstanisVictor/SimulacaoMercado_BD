package controler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import model.ProdutoEstoque;
import model.dao.ProdutoEstoqueDao;

public class ProdutoEstoqueControler {
  public static void inserirPordutoManual(String nome, int quantidade, double preco){

    ProdutoEstoque novo = new ProdutoEstoque(nome, quantidade, preco);
    
    if(novo!=null){
      ProdutoEstoqueDao.carregaProdutoManual(novo);
    }
  }

  public static void carregaEmLote(File arquivo){
    Vector<ProdutoEstoque> produtosArquivos = new Vector<>();
    if (arquivo!=null && arquivo.exists() && arquivo.isFile()) {
      try {
        
        FileReader marcaLeitura = new FileReader(arquivo);
        BufferedReader leitor = new BufferedReader(marcaLeitura);
        
        //leitura das linhas dos produtos
        String linha = leitor.readLine();
        while (linha!=null) {
          String infos[] = linha.split(";");
          if (infos.length == 3) {
            String nome = infos[0];
            try {
              double preco = Double.parseDouble(infos[1]);
              int quantidade = Integer.parseInt(infos[2]);

              ProdutoEstoque novo = new ProdutoEstoque(nome, quantidade, preco);
              produtosArquivos.add(novo);
            } catch (NumberFormatException ex) {
              System.err.println("Erro nos valores da linha: "+linha);
            }
          }
          linha = leitor.readLine();
        }
        leitor.close();
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }catch(IOException ex){
        ex.printStackTrace();
      }
    }
    if(!produtosArquivos.isEmpty()){
      //chamada ao ProdutoEstoqueDao para armazenar os valores em Banco de Dados
      ProdutoEstoqueDao.insereLote(produtosArquivos);
    }
  }

  public static boolean atualizaProduto(ProdutoEstoque prod){
    if(prod != null){
      return ProdutoEstoqueDao.updateProduto(prod);
    }else{
      return false;
    }
  }

  public static ProdutoEstoque buscaProdutoId(int id){
    
    if(id>0){
      return ProdutoEstoqueDao.consultaProduto(id);
    }else{
      System.err.println("Erro no valor do ID, deve ser maior ou igual a 0");
      return null;
    }

  }
}
