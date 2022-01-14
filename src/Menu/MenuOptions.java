package Menu;

import java.util.Scanner;

public class MenuOptions {

  public static void menuOpcoes(){
    Scanner teclado = new Scanner(System.in);
    System.out.println("\n\nInforme o que deseja acessar:"+
                      "\n1 - Estoque de Produtos\n2 - Histórico de Produtos Vendido\n"+
                      "3 - Clientes\n4 - Venda\n5 - Sair");
    int op = teclado.nextInt();
    while(op!=5){

      while(op<=0 || op >5){
        System.err.println("NUMERO INVÁLIDO, informe novamente...");
        op = teclado.nextInt();
      }
      
      switch (op) {
        case 1:
          //produtos
          ProdutoEstoqueMenu.opcoesProdutosEstoque();
          break;
        
        case 2:
          //produtos vendido
          break;
        
        case 3:
          //clientes
          ClienteMenu.opcaoCliente();
          break;
        
        case 4:
          //vendas
            VendaMenu.criarVenda();
          break;

        default: 
          break;
      }
      System.out.println("\n\nInforme o que deseja acessar:"+
                      "\n1 - Estoque de Produtos\n2 - Histórico de Produtos Vendido\n"+
                      "3 - Clientes\n4 - Venda\n5 - Sair");
      op = teclado.nextInt();
    }
  }

}
