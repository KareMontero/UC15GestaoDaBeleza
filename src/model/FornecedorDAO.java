package model;

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author karen
 */
public class FornecedorDAO {

    public void Adicionar(Fornecedor forn, List<Telefone> listaTelefones) {

        String sqlFornecedor = "INSERT INTO fornecedor (razaoSocial, cnpj, pessoaDeContato, emailFornecedor, "
                + "ativo, observacao, fk_endereco) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String sqlTelefone = "INSERT INTO telefone (telefoneTipo, ddd, telefone, fk_fornecedor) VALUES (?, ?, ?, ?)";

        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {

                conn.setAutoCommit(false);

                int idFornecedorGerado = 0;

                try (PreparedStatement stmt = conn.prepareStatement(sqlFornecedor, Statement.RETURN_GENERATED_KEYS)) {

                    stmt.setString(1, forn.getRazaoSocial());
                    stmt.setString(2, forn.getCnpj());
                    stmt.setString(3, forn.getPessoaDeContato());
                    stmt.setString(4, forn.getEmailFornecedor());
                    stmt.setBoolean(5, forn.isAtivo());
                    stmt.setString(6, forn.getObservacao());

                    if (forn.getFkEndereco() == 0) {
                        stmt.setNull(7, java.sql.Types.INTEGER);
                    } else {
                        stmt.setInt(7, forn.getFkEndereco());
                    }

                    stmt.executeUpdate();

                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            idFornecedorGerado = rs.getInt(1);
                        }
                    }
                }

                if (listaTelefones != null && !listaTelefones.isEmpty()) {
                    try (PreparedStatement stmtTel = conn.prepareStatement(sqlTelefone)) {

                        for (Telefone tel : listaTelefones) {
                            stmtTel.setString(1, tel.getTelefoneTipo());
                            stmtTel.setString(2, tel.getDdd());
                            stmtTel.setString(3, tel.getTelefone());
                            stmtTel.setInt(4, idFornecedorGerado);

                            stmtTel.addBatch();
                        }

                        stmtTel.executeBatch();
                    }
                }

                conn.commit();
                System.out.println("Fornecedor e telefone(s) cadastrados com sucesso!");
            }
        } catch (SQLException e) {

            try {
                if (conexaoJDBC.getConexao() != null) {
                    conexaoJDBC.getConexao().rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Erro ao salvar fornecedor no banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

    public List<Fornecedor> listarTodos() {

        String sql = "SELECT * FROM fornecedor ORDER BY razaoSocial";
        List<Fornecedor> lista = new java.util.ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Fornecedor f = new Fornecedor();
                    f.setIdFornecedor(rs.getInt("idFornecedor"));
                    f.setRazaoSocial(rs.getString("razaoSocial"));
                    f.setCnpj(rs.getString("cnpj"));
                    f.setPessoaDeContato(rs.getString("pessoaDeContato"));
                    f.setEmailFornecedor(rs.getString("emailFornecedor"));
                    f.setAtivo(rs.getBoolean("ativo"));
                    f.setObservacao(rs.getString("observacao"));
                    f.setDataCadastro(rs.getTimestamp("dataCadastro"));
                    f.setFkEndereco(rs.getInt("fk_endereco"));

                    lista.add(f);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar fornecedores: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }

    public void excluir(int idFornecedor) {
        String sql = "DELETE FROM fornecedor WHERE idFornecedor = ?";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, idFornecedor);
                    stmt.executeUpdate();
                    System.out.println("Fornecedor e seus telefones excluídos com sucesso!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir fornecedor no banco: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

}
