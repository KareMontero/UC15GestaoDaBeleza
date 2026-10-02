package model;

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario autenticar(String login, String senha) {
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha_hash = ? AND ativo = TRUE";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, login);
                stmt.setString(2, senha);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    Usuario user = new Usuario();
                    user.setIdUsuario(rs.getInt("idUsuario"));
                    user.setFuncionarioId(rs.getInt("funcionario_id"));
                    user.setLogin(rs.getString("login"));
                    user.setPerfil(rs.getString("perfil"));
                    user.setAtivo(rs.getBoolean("ativo"));

                    atualizarUltimoLogin(user.getIdUsuario(), conn);

                    rs.close();
                    stmt.close();
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autenticar usuário: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return null;
    }

    private void atualizarUltimoLogin(int idUsuario, Connection conn) throws SQLException {
        String sqlUpdate = "UPDATE usuario SET data_ultimo_login = CURRENT_TIMESTAMP WHERE idUsuario = ?";
        try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
            stmtUpdate.setInt(1, idUsuario);
            stmtUpdate.executeUpdate();
        }
    }
}
