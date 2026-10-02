package conexao;

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

    }
}
