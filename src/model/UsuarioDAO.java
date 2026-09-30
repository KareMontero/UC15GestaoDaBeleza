package model;

/**
 *
 * @author karen
 */

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
 
    // Método para autenticar o usuário na tela de login
    public Usuario autenticar(String login, String senha) {
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha_hash = ? AND ativo = TRUE";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();
        
        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();
            
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, login);
                stmt.setString(2, senha); // Nota: em produção o ideal seria comparar usando Bcrypt
                
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    Usuario user = new Usuario();
                    user.setIdUsuario(rs.getInt("idUsuario"));
                    user.setFuncionarioId(rs.getInt("funcionario_id"));
                    user.setLogin(rs.getString("login"));
                    user.setPerfil(rs.getString("perfil")); // Retorna 'gerente', 'atendente', etc.
                    user.setAtivo(rs.getBoolean("ativo"));
                    
                    // Atualiza a data do último login no banco de dados automaticamente
                    atualizarUltimoLogin(user.getIdUsuario(), conn);
                    
                    rs.close();
                    stmt.close();
                    return user; // Retorna o usuário autenticado com o seu respectivo cargo
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autenticar usuário: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return null; // Retorna null se não encontrar ou se os dados estiverem errados
    }

    private void atualizarUltimoLogin(int idUsuario, Connection conn) throws SQLException {
        String sqlUpdate = "UPDATE usuario SET data_ultimo_login = CURRENT_TIMESTAMP WHERE idUsuario = ?";
        try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
            stmtUpdate.setInt(1, idUsuario);
            stmtUpdate.executeUpdate();
        }
    }
}
