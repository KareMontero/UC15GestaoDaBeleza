package model;

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LogSistemaDAO {

    public List<LogSistema> listarComFiltroModulo(String moduloPesquisa) {
        List<LogSistema> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        String sql = "SELECT l.*, f.nomeFuncionario AS nome_usuario "
                + "FROM logs_sistema l "
                + "LEFT JOIN usuario u ON l.usuario_id = u.idUsuario "
                + "LEFT JOIN funcionario f ON u.funcionario_id = f.idFuncionario ";

        if (moduloPesquisa != null && !moduloPesquisa.trim().isEmpty()) {
            sql += "WHERE l.modulo LIKE ? ";
        }
        sql += "ORDER BY l.data_log DESC";

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    if (moduloPesquisa != null && !moduloPesquisa.trim().isEmpty()) {
                        stmt.setString(1, "%" + moduloPesquisa + "%");
                    }
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            LogSistema log = new LogSistema();
                            log.setId(rs.getInt("id"));
                            log.setNivel(rs.getString("nivel"));
                            log.setModulo(rs.getString("modulo"));
                            log.setMensagem(rs.getString("mensagem"));
                            log.setUsuarioId((Integer) rs.getObject("usuario_id"));
                            log.setIpOrigem(rs.getString("ip_origem"));
                            log.setDataLog(rs.getTimestamp("data_log"));
                            log.setNomeUsuario(rs.getString("nome_usuario") != null ? rs.getString("nome_usuario") : "SISTEMA");
                            lista.add(log);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }
}
