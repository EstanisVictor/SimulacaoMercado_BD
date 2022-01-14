package Menu;

import java.io.File;
import java.util.Scanner;

import controler.ProdutoEstoqueControler;
import model.ProdutoEstoque;

public class ProdutoEstoqueMenu {
  public static void opcoesProdutosEstoque(){
    Scanner teclado = new Scanner(System.in);
    System.out.println("=============SEÇÃO DO ESTOQUE=============");
    System.out.println("\nInforme uma das opções:\n"+
                      "1 - Cadastrar produtos usando arquivo\n2 - Cadastrar Produtos Manualmente\n"+
                      "3 - Imprimir Produtos do Estoque\n4 - Sair");
    int op = teclado.nextInt();
    while (op!=4) {

      while(op<=0 || op >4){
        System.err.println("NUMERO INVÁLIDO, informe novamente...");
        op = teclado.nextInt();
      }

      switch (op) {
        case 1:

          File arquivo = new File("ProdutosTeste.csv");
          ProdutoEstoqueControler.carregaEmLote(arquivo);

          break;

        case 2:

          System.out.println("Informe o nome para o produto:");
          String nome = teclado.nextLine();
          
          teclado.nextLine();

          System.out.println("Informe a quantidade de produtos para inserir no estoque:");
          int quantidade = teclado.nextInt();

          System.out.println("Informe o preço do produto:");
          double preco = teclado.nextDouble();

          ProdutoEstoqueControler.inserirPordutoManual(nome, quantidade, preco);
          break;
        
        case 3:

          ProdutoEstoque produtoEncontrado = null;

          System.out.println("Informe o id do produto que deseja buscar");
          int idBusca = teclado.nextInt();

          produtoEncontrado = ProdutoEstoqueControler.buscaProdutoId(idBusca);

          if(produtoEncontrado!=null){
            System.out.println("       Produto        |    Preco    |    Quantidade");
            System.out.println("     "+produtoEncontrado.getNome()+"        "+produtoEncontrado.getPreco()+
            "            "+produtoEncontrado.getQuantidade());
          }else{
            System.err.println("Não existe esse produto em nosso estoque");
          }
          break;
        
        default:
          break;
      }
      
      System.out.println("\nInforme uma das opções:\n"+
                      "1 - Cadastrar produtos usando arquivo\n2 - Cadastrar Produtos Manualmente\n"+
                      "3 - Imprimir Produto do Estoque\n4 - Sair");
      op = teclado.nextInt();

    }
  }
}
