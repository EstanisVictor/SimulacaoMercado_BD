package integracaobd;

import Menu.MenuOptions;
import controler.ClienteControler;
import controler.ProdutoEstoqueControler;
import controler.VendaControle;
import model.Cliente;
import model.ProdutoEstoque;
import model.Venda;
import model.dao.ClienteDao;
import view.ProdutoEstoqueView;

public class App {

    public static void recuperaVenda(int id){
        Venda vendaAntiga = VendaControle.buscavendaId(id);

        System.out.println(vendaAntiga.getTotalCompra() + " "
        + vendaAntiga.getProdutosComprados().get(0).getProdutoLoja().getNome());
    }
    public static void main(String[] args) throws Exception {

        MenuOptions.menuOpcoes();
    }
}
