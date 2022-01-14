package controler;

import java.util.Vector;

import model.Cliente;
import model.dao.ClienteDao;

public class ClienteControler {
  
  public static boolean cadastroCliente(Cliente novo){
    if(verificaInfoClientee(novo)){
      ClienteDao.insereCliente(novo);
      return true;
    }
    return false;
  }

  public static boolean updateCliente(Cliente cliente){
    if(verificaInfoClientee(cliente) && cliente.getId() > 0){
      return ClienteDao.atualiaCliente(cliente);
    }
    return false;
  }

  public static Vector<Cliente> buscaClientes(String nome){
    if(nome.length() >= 3){
      return ClienteDao.consultaClienteNome(nome.toUpperCase());
    }
    return null;
  }

  private static boolean verificaInfoClientee(Cliente cliente){
    if(cliente.getNome() == null || cliente.getEndereco() == null|| cliente.getTel() == null){
      return false;
    }else{
      
      if(cliente.getNome().length()>=3 && cliente.getEndereco().length()>=3 && cliente.getTel().length()>=8){
        String token[] = cliente.getTel().split("[A-Za-z]");
        String token1[] = cliente.getNome().split("[\\d]");

        if(token.length == 1 && token1.length == 1){
          String tel = cliente.getTel().replaceAll("[^\\d]", "");
          
          if(tel.length()>=8 && tel.length() <=11){
            cliente.setTel(tel);
            cliente.setNome(cliente.getNome().toUpperCase());
            cliente.setEndereco(cliente.getEndereco().toUpperCase());

            return true;
          }else{
            return false;
          }
        }else{
          return false;
        }
      }else{
        return false;
      }
    }
  }
  
  public static Cliente buscaClienteId(int id){
    if(id>0){
      return ClienteDao.buscaClienteId(id);
    }else{
      return null;
    }
  }
}
