package view;

import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controler.ProdutoEstoqueControler;
import model.ProdutoEstoque;

public class ProdutoEstoqueView {
  public void iniciaInterface(){
    
    JOptionPane inicio = new JOptionPane();
    int op = inicio.showInternalConfirmDialog(null,
    "Deseja Carregar Produtos em Lote","Carregar em lote", JOptionPane.YES_NO_OPTION);

     if(op == JOptionPane.YES_OPTION){

        JFileChooser janelaArq = new JFileChooser("C:");

        op = janelaArq.showOpenDialog(null);

        if(op == JFileChooser.APPROVE_OPTION){
          ProdutoEstoqueControler.carregaEmLote(janelaArq.getSelectedFile());
        }else{
          JOptionPane.showMessageDialog(null, "É preciso escolher um arquivo para essa funcionalidade", 
          "erro", JOptionPane.WARNING_MESSAGE);
        }

     }else{
       System.exit(0);
     }
  }

  public void imprimeProduto(){
    Scanner teclado = new Scanner(System.in);
    
    System.out.println("Informe um produto para aprsentar as informações");
    int id = teclado.nextInt();

    ProdutoEstoque produto = ProdutoEstoqueControler.buscaProdutoId(id);

    if(produto == null){
      System.out.println("Não existe produto com o id informado");
    }else{
      System.out.println(produto);
    }
    
  }
}
