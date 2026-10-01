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

    // 2. MÉTODO PARA LISTAR TODOS OS KITS CADASTRADOS (Sem apagar o anterior!)
public List<KitServico> listarPorServico(int idServicoBusca) {
    // Buscamos apenas os registros ativos! ✨
    String sql = "SELECT ks.servico_id, s.servico AS nome_servico, ks.produto_id, p.nomeProduto, ks.qtidadeProdutoPorServico " +
                 "FROM kitservico ks " +
                 "INNER JOIN servico s ON ks.servico_id = s.idServico " +
                 "INNER JOIN produto p ON ks.produto_id = p.idProduto " +
                 "WHERE ks.ativo = TRUE"; // Filtra os desativados!
                 
    List<KitServico> lista = new ArrayList<>();
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    try {
        conexaoJDBC.conectar();
        Connection conn = conexaoJDBC.getConexao();

        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                 
                while (rs.next()) {
                    KitServico kit = new KitServico();
                    kit.setServicoId(rs.getInt("servico_id"));
                    kit.setProdutoId(rs.getInt("produto_id"));
                    kit.setQtidadeProdutoPorServico(rs.getInt("qtidadeProdutoPorServico"));
                    kit.setNomeProduto(rs.getString("nomeProduto")); 
                    kit.setNomeServico(rs.getString("nome_servico")); // Captura o nome do Kit/Serviço! 🌟

                    lista.add(kit);
                }
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar kits do banco: " + e.getMessage(), e);
    } finally {
        conexaoJDBC.desconectar();
    }
    return lista;
}




    // 3. MÉTODO PARA REMOVER UM PRODUTO ESPECÍFICO DE UM KIT (Exclusão pela chave composta)
public void excluirItemDoKit(int idServico, int idProduto) {
    // Mudamos de DELETE para UPDATE! Não apaga, apenas desativa. 🔒
    String sql = "UPDATE kitservico SET ativo = FALSE WHERE servico_id = ? AND produto_id = ?";
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    try {
        conexaoJDBC.conectar();
        Connection conn = conexaoJDBC.getConexao();

        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idServico);
                stmt.setInt(2, idProduto);
                
                stmt.executeUpdate(); 
                System.out.println("Item desativado do kit com sucesso!");
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao desativar item do kit no banco: " + e.getMessage(), e);
    } finally {
        conexaoJDBC.desconectar();
    }
}


    // 4. NOVO MÉTODO: Lista TODOS os kits cadastrados no banco de uma só vez (Para a listagem pronta)
public List<KitServico> listarTodosOsKits() {
    // Adicionado o filtro de ativos no final! ✨
    String sql = "SELECT ks.servico_id, s.servico AS nome_servico, ks.produto_id, p.nomeProduto, ks.qtidadeProdutoPorServico " +
                 "FROM kitservico ks " +
                 "INNER JOIN servico s ON ks.servico_id = s.idServico " +
                 "INNER JOIN produto p ON ks.produto_id = p.idProduto " +
                 "WHERE ks.ativo = TRUE"; 
                 
    List<KitServico> lista = new ArrayList<>();
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    try {
        conexaoJDBC.conectar();
        Connection conn = conexaoJDBC.getConexao();

        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                 
                while (rs.next()) {
                    KitServico kit = new KitServico();
                    kit.setServicoId(rs.getInt("servico_id"));
                    kit.setProdutoId(rs.getInt("produto_id"));
                    kit.setQtidadeProdutoPorServico(rs.getInt("qtidadeProdutoPorServico"));
                    kit.setNomeProduto(rs.getString("nomeProduto"));
                    kit.setNomeServico(rs.getString("nome_servico")); // Descomentado e ativo! 🌟

                    lista.add(kit);
                }
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar todos os kits: " + e.getMessage(), e);
    } finally {
        conexaoJDBC.desconectar();
    }
    return lista;
}


}
