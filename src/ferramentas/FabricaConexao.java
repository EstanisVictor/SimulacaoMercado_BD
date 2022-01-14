package ferramentas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class FabricaConexao {
  private static final String ipPostgres = "localhost:5432/mercearia";
  private static final String userPostgres = "postgres";
  private static final String senha = "victor700";
  private static Connection conexaoPostgres;

  public static Connection getConexaoPostgres(){
    try {
      
      if(conexaoPostgres == null || conexaoPostgres.isClosed()){
        conexaoPostgres = DriverManager.getConnection("jdbc:postgresql://"+ipPostgres, 
          userPostgres, senha);
      }

      return conexaoPostgres;
    } catch (SQLException ex) {
        System.err.println("Não foi possivel estabelecer a coneexão com o banco");
        return null;
    }
  }
}
