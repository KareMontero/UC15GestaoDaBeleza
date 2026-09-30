package model;

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDAO {

    // Método útil para buscar todos os telefones vinculados a um funcionário específico
    public List<Telefone> buscarPorFuncionario(int idFuncionario) {
        String sql = "SELECT * FROM telefone WHERE fk_funcionario = ?";
        List<Telefone> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idFuncionario);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Telefone t = new Telefone();
                    t.setIdTelefone(rs.getInt("idTelefone"));
                    t.setTelefoneTipo(rs.getString("telefoneTipo"));
                    t.setDdd(rs.getString("ddd"));
                    t.setTelefone(rs.getString("telefone"));
                    t.setFkFuncionario(rs.getInt("fk_funcionario"));
                    
                    lista.add(t);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar telefones do funcionário: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }
    
        // ADICIONE ESTE MÉTODO DENTRO DE TelefoneDAO.java:
    public List<Telefone> buscarPorCliente(int idCliente) {
        String sql = "SELECT * FROM telefone WHERE fk_cliente = ?";
        List<Telefone> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idCliente);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Telefone t = new Telefone();
                    t.setIdTelefone(rs.getInt("idTelefone"));
                    t.setTelefoneTipo(rs.getString("telefoneTipo"));
                    t.setDdd(rs.getString("ddd"));
                    t.setTelefone(rs.getString("telefone"));
                    t.setFkCliente(rs.getInt("fk_cliente"));
                    
                    lista.add(t);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar telefones do cliente: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }
    
        public List<Telefone> buscarPorFornecedor(int idFornecedor) {
        String sql = "SELECT * FROM telefone WHERE fk_fornecedor = ?";
        List<Telefone> lista = new java.util.ArrayList<>();
        conexao.ConexaoJDBC conexaoJDBC = new conexao.ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idFornecedor);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Telefone t = new Telefone();
                    t.setIdTelefone(rs.getInt("idTelefone"));
                    t.setTelefoneTipo(rs.getString("telefoneTipo"));
                    t.setDdd(rs.getString("ddd"));
                    t.setTelefone(rs.getString("telefone"));
                    t.setFkFornecedor(rs.getInt("fk_fornecedor"));
                    
                    lista.add(t);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar telefones do fornecedor: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }


}