package model;

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void Adicionar(Produto prod) {

        String sql = "INSERT INTO produto (nomeProduto, descricao, precoVenda, estoqueAtual, ativo, fk_fornecedor) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, prod.getNomeProduto());
                    stmt.setString(2, prod.getDescricao());
                    stmt.setDouble(3, prod.getPrecoVenda());
                    stmt.setInt(4, prod.getEstoqueAtual());
                    stmt.setBoolean(5, prod.isAtivo());

                    if (prod.getFkFornecedor() == 0) {
                        stmt.setNull(6, java.sql.Types.INTEGER);
                    } else {
                        stmt.setInt(6, prod.getFkFornecedor());
                    }

                    stmt.executeUpdate();
                    System.out.println("Produto gravado com sucesso no banco!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar produto no banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

    public List<Produto> listarTodos() {
        String sql = "SELECT * FROM produto ORDER BY nomeProduto";
        List<Produto> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        Produto p = new Produto();
                        p.setIdProduto(rs.getInt("idProduto"));
                        p.setNomeProduto(rs.getString("nomeProduto"));
                        p.setDescricao(rs.getString("descricao"));
                        p.setPrecoVenda(rs.getDouble("precoVenda"));
                        p.setEstoqueAtual(rs.getInt("estoqueAtual"));
                        p.setAtivo(rs.getBoolean("ativo"));
                        p.setFkFornecedor(rs.getInt("fk_fornecedor"));

                        lista.add(p);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos do banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }

    public void excluir(int idProduto) {
        String sql = "DELETE FROM produto WHERE idProduto = ?";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, idProduto);
                    stmt.executeUpdate();
                    System.out.println("Produto deletado com sucesso!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto do banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

    public void atualizar(Produto prod) {
        String sql = "UPDATE produto SET nomeProduto = ?, descricao = ?, precoVenda = ?, "
                + "estoqueAtual = ?, ativo = ?, fk_fornecedor = ? WHERE idProduto = ?";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, prod.getNomeProduto());
                    stmt.setString(2, prod.getDescricao());
                    stmt.setDouble(3, prod.getPrecoVenda());
                    stmt.setInt(4, prod.getEstoqueAtual());
                    stmt.setBoolean(5, prod.isAtivo());

                    if (prod.getFkFornecedor() == 0) {
                        stmt.setNull(6, java.sql.Types.INTEGER);
                    } else {
                        stmt.setInt(6, prod.getFkFornecedor());
                    }

                    stmt.setInt(7, prod.getIdProduto());

                    stmt.executeUpdate();
                    System.out.println("Produto atualizado com sucesso!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto no banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

    public List<Produto> pesquisarPorNome(String nomeBusca) {
        String sql = "SELECT * FROM produto WHERE nomeProduto LIKE ? ORDER BY nomeProduto";
        List<Produto> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    // O "%" faz a busca encontrar qualquer parte do texto digitado
                    stmt.setString(1, "%" + nomeBusca + "%");

                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            Produto p = new Produto();
                            p.setIdProduto(rs.getInt("idProduto"));
                            p.setNomeProduto(rs.getString("nomeProduto"));
                            p.setDescricao(rs.getString("descricao"));
                            p.setPrecoVenda(rs.getDouble("precoVenda"));
                            p.setEstoqueAtual(rs.getInt("estoqueAtual"));
                            p.setAtivo(rs.getBoolean("ativo"));
                            p.setFkFornecedor(rs.getInt("fk_fornecedor"));

                            lista.add(p);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao filtrar produtos do banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }

}
