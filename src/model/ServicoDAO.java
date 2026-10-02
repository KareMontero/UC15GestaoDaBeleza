package model;

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    public void Adicionar(Servico serv) {
        String sql = "INSERT INTO servico (servico, descricao, preco, categoria, estoqueProduto, qtidadeProduto, ativo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, serv.getServico());
                    stmt.setString(2, serv.getDescricao());
                    stmt.setDouble(3, serv.getPreco());
                    stmt.setString(4, serv.getCategoria());
                    stmt.setBoolean(5, serv.isEstoqueProduto());
                    stmt.setInt(6, serv.getQtidadeProduto());
                    stmt.setBoolean(7, serv.isAtivo());

                    stmt.executeUpdate();
                    System.out.println("Serviço gravado com sucesso no banco!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar serviço no banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

    public List<Servico> listarTodos() {
        String sql = "SELECT * FROM servico ORDER BY servico";
        List<Servico> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        Servico s = new Servico();
                        s.setIdServico(rs.getInt("idServico"));
                        s.setServico(rs.getString("servico"));
                        s.setDescricao(rs.getString("descricao"));
                        s.setPreco(rs.getDouble("preco"));
                        s.setCategoria(rs.getString("categoria"));
                        s.setEstoqueProduto(rs.getBoolean("estoqueProduto"));
                        s.setQtidadeProduto(rs.getInt("qtidadeProduto"));
                        s.setAtivo(rs.getBoolean("ativo"));

                        lista.add(s);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar serviços do banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }

    public void excluir(int idServico) {
        String sql = "DELETE FROM servico WHERE idServico = ?";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, idServico);
                    stmt.executeUpdate();
                    System.out.println("Serviço deletado com sucesso!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir serviço do banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

    public void atualizar(Servico serv) {
        String sql = "UPDATE servico SET servico = ?, descricao = ?, preco = ?, "
                + "categoria = ?, estoqueProduto = ?, qtidadeProduto = ?, ativo = ? WHERE idServico = ?";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, serv.getServico());
                    stmt.setString(2, serv.getDescricao());
                    stmt.setDouble(3, serv.getPreco());
                    stmt.setString(4, serv.getCategoria());
                    stmt.setBoolean(5, serv.isEstoqueProduto());
                    stmt.setInt(6, serv.getQtidadeProduto());
                    stmt.setBoolean(7, serv.isAtivo());
                    stmt.setInt(8, serv.getIdServico());

                    stmt.executeUpdate();
                    System.out.println("Serviço atualizado com sucesso!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar serviço no banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

    public List<Servico> pesquisarPorNome(String nomeBusca) {
        String sql = "SELECT * FROM servico WHERE servico LIKE ? ORDER BY servico";
        List<Servico> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, "%" + nomeBusca + "%");

                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            Servico s = new Servico();
                            s.setIdServico(rs.getInt("idServico"));
                            s.setServico(rs.getString("servico"));
                            s.setDescricao(rs.getString("descricao"));
                            s.setPreco(rs.getDouble("preco"));
                            s.setCategoria(rs.getString("categoria"));
                            s.setEstoqueProduto(rs.getBoolean("estoqueProduto"));
                            s.setQtidadeProduto(rs.getInt("qtidadeProduto"));
                            s.setAtivo(rs.getBoolean("ativo"));

                            lista.add(s);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao filtrar serviços do banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }
}
