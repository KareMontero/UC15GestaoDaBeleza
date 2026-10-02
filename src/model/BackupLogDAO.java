package model;

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BackupLogDAO {

    public List<BackupLog> listarTodos() {
        List<BackupLog> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();
        String sql = "SELECT * FROM backups_log ORDER BY data_backup DESC";

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        BackupLog backup = new BackupLog();
                        backup.setId(rs.getInt("id"));
                        backup.setNomeArquivo(rs.getString("nome_arquivo"));
                        backup.setTamanhoMb(rs.getDouble("tamanho_mb"));
                        backup.setStatus(rs.getString("status"));
                        backup.setMensagem(rs.getString("mensagem"));
                        backup.setDataBackup(rs.getTimestamp("data_backup"));
                        lista.add(backup);
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
