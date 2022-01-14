package Menu;

import java.util.Scanner;
import java.util.Vector;

import controler.ClienteControler;
import model.Cliente;

public class ClienteMenu {

  public static boolean cadastrarClienteLoja(){
    Scanner teclado = new Scanner(System.in);

    boolean novoCliente = false;
    
    System.out.println("\nVocê deseja cadastrar no sistema? Digite (1 para Sim ou 2 para Não)");
    int op = teclado.nextInt();

    while(op<=0 || op>2){
      System.err.println("Opção Inválida");
      System.out.println("Digite (S para Sim ou N para Não)");
      op = teclado.nextInt();
    }

    if(op==2){
      novoCliente = false;
    }

    if (op==1) {

      teclado.nextLine();

      System.out.println("Infoirme seu nome");
      String nome = teclado.nextLine();
      System.out.println("Infoirme seu endereco");
      String endereco = teclado.nextLine();
      System.out.println("Infoirme seu telefone");
      String tel = teclado.nextLine();

      Cliente temp = new Cliente(nome, endereco, tel);
      ClienteControler.cadastroCliente(temp);
      novoCliente = true;

    }
    return novoCliente;
  }

  public static Cliente verificaClienteExistente(){

    Scanner teclado = new Scanner(System.in);

    Cliente usuario = null;
    boolean receber = false;


    System.out.println("Você é cliente do Mercado? Digite (1 para Sim ou 2 para Não)");
    int op = teclado.nextInt();

    while(op<=0 || op>2){
      System.err.println("Opção Inválida");
      System.out.println("Digite (S para Sim ou N para Não)");
      op = teclado.nextInt();
    }

    if(op==2){
      receber = cadastrarClienteLoja();
      if(receber == true){

        System.out.println("Informe seu ID");
        int id = teclado.nextInt();

        usuario = ClienteControler.buscaClienteId(id);
        
        if(usuario == null){
          System.err.println("Não existe esse ID no nosso sistema");
        }

      }else{
        usuario = null;
      }  
    }

    if (op==1) {
      
      System.out.println("Informe seu ID");
      int id = teclado.nextInt();

      usuario = ClienteControler.buscaClienteId(id);
    }
    
    return usuario;
  }

  public static void opcaoCliente(){
    Scanner teclado = new Scanner(System.in);

    System.out.println("=============SEÇÃO DO CLIENTE=============");
    System.out.println("O que deseja fazer:\n"+
                  "1 - Cadastrar Cliente\n2 - Buscar Cliente pelo nomme\n3 - Buscar Cliente pelo Id\n4 - Sair");

    int op = teclado.nextInt();
    while(op!=4){
      
      while (op<=0 || op>4) {
        System.err.println("Opção Inválida");
        System.out.println("O que deseja fazer?\n"+
                  "1 - Cadastrar Cliente\n2 - Buscar Cliente pelo nomme\n3 - Buscar Cliente pelo Id\n4 - Sair");
        op = teclado.nextInt();
      }

      if(op==1){
        boolean receber = cadastrarClienteLoja();

        if(receber==true){
          System.out.println("\n\nCadastro feito com sucesso\n");
        }else{
          System.err.println("\n\nCadastro não realizado\n");
        }
        
      }

      if(op==2){
        //buscar pelo nome
        Vector<Cliente> clienteBuscado = new Vector<>();

        teclado.nextLine();

        System.out.println("Informe o nome completo");
        String nomeCompleto = teclado.nextLine().trim().toUpperCase();
        
        clienteBuscado = ClienteControler.buscaClientes(nomeCompleto);

        if(!clienteBuscado.isEmpty()){
          for (int i = 0; i < clienteBuscado.size(); i++) {
            System.out.println("ID: "+clienteBuscado.get(i).getId());
            System.out.println("Nome: "+clienteBuscado.get(i).getNome());
            System.out.println("Endereco: "+clienteBuscado.get(i).getEndereco());
            System.out.println("Telefone: "+clienteBuscado.get(i).getTel());
          }
        }
      }

      if(op==3){
        //buscar pelo id

      }

      System.out.println("O que deseja fazer?\n"+
                  "1 - Cadastrar Cliente\n2 - Buscar Cliente pelo nomme\n3 - Buscar Cliente pelo Id\n4 - Sair");
      op = teclado.nextInt();
    }
  }
}
