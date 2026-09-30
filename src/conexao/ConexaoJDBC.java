package conexao;
  
// importa estas duas bibliotecas
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author karen
 */
public class ConexaoJDBC {


   private Connection conexao;
   //construtor
   public Connection getConexao(){
    return conexao;   
   }
   // métodos
   public void conectar(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestao_da_beleza_UC_15","root", "##K@10r@73##");
           System.out.println("Conexão bem sucedida");
       } catch (ClassNotFoundException cnfe) {
           System.out.println("Falha ao carregara classe de coneção: classe não encontrada" + cnfe);
       } catch(SQLException se) {
           System.out.println("Falha ao conectar com o banco de dados! Erro de SQL" + se);
       }
   }
   
   public void desconectar() {
        try {
            if(conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Desconectado com sucesso!");
            }
        }catch (SQLException se) {
            System.out.println("Erro ao desconectar " + se);
        }
} 
}
