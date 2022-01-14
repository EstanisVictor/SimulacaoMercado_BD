package Menu;

import java.util.Scanner;
import java.util.Vector;

import controler.ProdutoEstoqueControler;
import controler.VendaControle;
import model.ProdutoEstoque;
import model.Venda;

public class VendaMenu {
  public static void criarVenda(){
    Scanner teclado = new Scanner(System.in);
    
    Venda novaVenda = new Venda(ClienteMenu.verificaClienteExistente());
    Vector<Integer> quantidadesRecebidas= new Vector<>();
    while(true){

      System.out.println("Informe o id do produto que deseja realizar a compra:");
      int escolhaProdutoId = teclado.nextInt();

      if (escolhaProdutoId<0){
        break;
      }

      System.out.println("Informee a quantidade de produtos que deseja comprar:");
      int quant = teclado.nextInt();
      quantidadesRecebidas.add(quant);
      

      ProdutoEstoque novoProduto = ProdutoEstoqueControler.buscaProdutoId(escolhaProdutoId);
      novaVenda.addProdutoCompra(novoProduto, quant);
      
    }
    VendaControle.cadastroVenda(novaVenda);
    VendaControle.notaFiscal(novaVenda, quantidadesRecebidas);
  }
}
