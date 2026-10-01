package model;

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KitServicoDAO {

    // 1. MÉTODO PARA INSERIR UM PRODUTO NO KIT DO SERVIÇO
    public void Adicionar(KitServico kit) {
        String sql = "INSERT INTO kitservico (servico_id, produto_id, qtidadeProdutoPorServico) VALUES (?, ?, ?)";

        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, kit.getServicoId());
                    stmt.setInt(2, kit.getProdutoId());
                    stmt.setInt(3, kit.getQtidadeProdutoPorServico());

                    stmt.executeUpdate();
                    System.out.println("Produto vinculado ao serviço com sucesso no banco!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar item no kit de serviço: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

    // 2. MÉTODO PARA LISTAR OS PRODUTOS DE UM SERVIÇO ESPECÍFICO (Para carregar no JTable)
    public List<KitServico> listarPorServico(int idServicoBusca) {
        String sql = "SELECT * FROM kitservico WHERE servico_id = ?";
        List<KitServico> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, idServicoBusca);
                    
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            KitServico kit = new KitServico();
                            kit.setServicoId(rs.getInt("servico_id"));
                            kit.setProdutoId(rs.getInt("produto_id"));
                            kit.setQtidadeProdutoPorServico(rs.getInt("qtidadeProdutoPorServico"));

                            lista.add(kit);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens do kit do banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }

    // 3. MÉTODO PARA REMOVER UM PRODUTO ESPECÍFICO DE UM KIT (Exclusão pela chave composta)
    public void excluirItemDoKit(int idServico, int idProduto) {
        String sql = "DELETE FROM kitservico WHERE servico_id = ? AND produto_id = ?";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, idServico);
                    stmt.setInt(2, idProduto);
                    
                    stmt.executeUpdate();
                    System.out.println("Item removido do kit com sucesso!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover item do kit no banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }
}
