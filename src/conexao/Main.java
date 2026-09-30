package conexao;

//import model.Funcionario;
//import model.FuncionarioDAO;
import view.TelaValidacaoUsuario;

/**
 *
 * @author karen
 */
public class Main {
  public static void main(String[] args) {
      // ConexaoJDBC c = new ConexaoJDBC();
      //  c.conectar();
       
          java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaValidacaoUsuario login = new TelaValidacaoUsuario();
                login.setVisible(true);
                login.setLocationRelativeTo(null); // Centraliza no monitor
            }
        });
        

        
       /* System.out.println("\n--- Iniciando Teste de Inserção ---");

        // 2. Instancia um funcionário de teste preenchendo os campos obrigatórios
        Funcionario teste = new Funcionario();
        teste.setNome("Karen Teste Sistema");
        teste.setCpf("12345678901"); // Use 11 dígitos numéricos
        teste.setCarteiraTrabalho("CTPS-999888");
        teste.setEmail("karen@teste.com");
        teste.setTelefoneContato("11999999999");
        teste.setFuncao("Gerência");
        teste.setDataNascimento("1995-05-15"); // Formato AAAA-MM-DD exigido pelo MySQL
        teste.setDataContratacao("2026-01-10");
        teste.setDisponibilidadeDeHorario("Integral");
        teste.setValorDoServico(150.00);
        teste.setAtivo(true);

        // 3. Instancia o DAO e tenta mandar pro banco de dados
        try {
            FuncionarioDAO dao = new FuncionarioDAO();
            dao.Adicionar(teste);
            System.out.println("🔥 SUCESSO ABSOLUTO! O funcionário foi gravado no MySQL.");
        } catch (Exception e) {
            System.out.println("❌ Ih, deu erro ao salvar o funcionário!");
            e.printStackTrace();
        }*/
    } 
}


