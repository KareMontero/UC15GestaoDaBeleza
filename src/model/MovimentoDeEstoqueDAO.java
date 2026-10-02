package model;

import conexao.ConexaoJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovimentoDeEstoqueDAO {

    public List<MovimentoDeEstoque> listarComFiltroProduto(String nomeProdutoPesquisa) {
        List<MovimentoDeEstoque> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        String sql = "SELECT e.*, p.nomeProduto AS nome_produto, f.nomeFuncionario AS nome_usuario "
                + "FROM estoque e "
                + "INNER JOIN produto p ON e.produto_id = p.idProduto "
                + "INNER JOIN usuario u ON e.usuario_id = u.idUsuario "
                + "INNER JOIN funcionario f ON u.funcionario_id = f.idFuncionario ";

        if (nomeProdutoPesquisa != null && !nomeProdutoPesquisa.trim().isEmpty()) {
            sql += "WHERE p.nomeProduto LIKE ? ";
        }

        sql += "ORDER BY e.data_movimentacao DESC";

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                    if (nomeProdutoPesquisa != null && !nomeProdutoPesquisa.trim().isEmpty()) {
                        stmt.setString(1, "%" + nomeProdutoPesquisa + "%");
                    }

                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            MovimentoDeEstoque mov = new MovimentoDeEstoque();
                            mov.setIdEstoque(rs.getInt("idEstoque"));
                            mov.setTipo(rs.getString("tipo"));
                            mov.setQtidadeProduto(rs.getInt("qtidadeProduto"));
                            mov.setMotivo(rs.getString("motivo"));
                            mov.setUsuarioId(rs.getInt("usuario_id"));
                            mov.setAgendaId((Integer) rs.getObject("agenda_id"));
                            mov.setProdutoId(rs.getInt("produto_id"));
                            mov.setDataMovimentacao(rs.getTimestamp("data_movimentacao"));

                            mov.setNomeProduto(rs.getString("nome_produto"));
                            mov.setNomeUsuario(rs.getString("nome_usuario"));

                            lista.add(mov);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar movimentações: " + e.getMessage());
            e.printStackTrace();
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }
}
