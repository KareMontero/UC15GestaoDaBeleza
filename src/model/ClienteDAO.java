package model;

import conexao.ConexaoJDBC;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void Adicionar(Cliente cli, List<Telefone> listaTelefones) {

        String sqlCliente = "INSERT INTO cliente (nomeCliente, cpf, dataNascimento, email, "
                + "observacao, ativo, fk_endereco) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String sqlTelefone = "INSERT INTO telefone (telefoneTipo, ddd, telefone, fk_cliente) VALUES (?, ?, ?, ?)";

        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();
        Connection conn = null;

        try {
            conexaoJDBC.conectar();
            conn = conexaoJDBC.getConexao();

            if (conn != null) {
                conn.setAutoCommit(false);

                int idClienteGerado = 0;

                try (PreparedStatement stmt = conn.prepareStatement(sqlCliente, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, cli.getNomeCliente());
                    stmt.setString(2, cli.getCpf());

                    if (cli.getDataNascimento() == null) {
                        stmt.setNull(3, java.sql.Types.DATE);
                    } else {
                        stmt.setDate(3, new java.sql.Date(cli.getDataNascimento().getTime()));
                    }

                    stmt.setString(4, cli.getEmail());
                    stmt.setString(5, cli.getObservacao());
                    stmt.setBoolean(6, cli.isAtivo());

                    if (cli.getFkEndereco() <= 0) {
                        stmt.setNull(7, java.sql.Types.INTEGER);
                    } else {
                        stmt.setInt(7, cli.getFkEndereco());
                    }

                    stmt.executeUpdate();

                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            idClienteGerado = rs.getInt(1);
                        }
                    }
                }

                if (listaTelefones != null && !listaTelefones.isEmpty()) {
                    try (PreparedStatement stmtTel = conn.prepareStatement(sqlTelefone)) {

                        for (Telefone tel : listaTelefones) {

                            String tipo = tel.getTelefoneTipo() != null ? tel.getTelefoneTipo().toLowerCase() : "celular";
                            stmtTel.setString(1, tipo);

                            stmtTel.setString(2, tel.getDdd());
                            stmtTel.setString(3, tel.getTelefone());
                            stmtTel.setInt(4, idClienteGerado);

                            stmtTel.addBatch();
                        }

                        stmtTel.executeBatch();
                    }
                }

                conn.commit();
                System.out.println("Cliente e telefone(s) cadastrados com sucesso!");
            }
        } catch (SQLException e) {

            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Rollback executado devido a um erro na transação.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new RuntimeException("Erro ao salvar cliente e telefones: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

    public List<Cliente> listarTodos() {
    // Query atualizada para buscar todos os telefones agrupados por vírgula
    String sql = "SELECT c.*, GROUP_CONCAT(CONCAT('(', t.ddd, ') ', t.telefone) SEPARATOR ', ') AS todos_telefones "
               + "FROM cliente c "
               + "LEFT JOIN telefone t ON c.idCliente = t.fk_cliente "
               + "GROUP BY c.idCliente "
               + "ORDER BY c.nomeCliente";
               
    List<Cliente> lista = new ArrayList<>();
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    try {
        conexaoJDBC.conectar();
        Connection conn = conexaoJDBC.getConexao();

        if (conn != null) {
            // Utilizando o try-with-resources para garantir o fechamento seguro de Recursos do JDBC
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("idCliente"));
                    c.setNomeCliente(rs.getString("nomeCliente"));
                    c.setCpf(rs.getString("cpf"));
                    c.setDataNascimento(rs.getDate("dataNascimento"));
                    c.setEmail(rs.getString("email"));
                    c.setDataCadastro(rs.getTimestamp("dataCadastro"));
                    c.setObservacao(rs.getString("observacao"));
                    c.setAtivo(rs.getBoolean("ativo"));
                    c.setFkEndereco(rs.getInt("fk_endereco"));

                    // Captura a string de telefones concatenados do banco
                    String tels = rs.getString("todos_telefones");
                    if (tels == null || tels.trim().isEmpty()) {
                        c.setTelefonesFormatados("Nenhum cadastrado");
                    } else {
                        c.setTelefonesFormatados(tels);
                    }

                    lista.add(c);
                }
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
    } finally {
        conexaoJDBC.desconectar();
    }
    return lista;
}


    public void excluir(int idCliente) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idCliente);
                stmt.executeUpdate();
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

  public List<Cliente> pesquisarPorNome(String nomeBusca) {
    // Query atualizada trazendo a junção de telefones com suporte ao filtro LIKE
    String sql = "SELECT c.*, GROUP_CONCAT(CONCAT('(', t.ddd, ') ', t.telefone) SEPARATOR ', ') AS todos_telefones "
               + "FROM cliente c "
               + "LEFT JOIN telefone t ON c.idCliente = t.fk_cliente "
               + "WHERE c.nomeCliente LIKE ? "
               + "GROUP BY c.idCliente "
               + "ORDER BY c.nomeCliente";
               
    List<Cliente> lista = new ArrayList<>();
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    try {
        conexaoJDBC.conectar();
        Connection conn = conexaoJDBC.getConexao();

        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Mantém o curinga '%' para buscar por qualquer parte do nome digitado
                stmt.setString(1, "%" + nomeBusca + "%");

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Cliente c = new Cliente();
                        c.setIdCliente(rs.getInt("idCliente"));
                        c.setNomeCliente(rs.getString("nomeCliente"));
                        c.setCpf(rs.getString("cpf"));
                        c.setDataNascimento(rs.getDate("dataNascimento"));
                        c.setEmail(rs.getString("email"));
                        c.setDataCadastro(rs.getTimestamp("dataCadastro")); // Adicionado para manter a tabela idêntica
                        c.setObservacao(rs.getString("observacao"));
                        c.setAtivo(rs.getBoolean("ativo"));
                        c.setFkEndereco(rs.getInt("fk_endereco"));

                        // Captura os múltiplos telefones mesmo durante a filtragem de pesquisa
                        String tels = rs.getString("todos_telefones");
                        if (tels == null || tels.trim().isEmpty()) {
                            c.setTelefonesFormatados("Nenhum cadastrado");
                        } else {
                            c.setTelefonesFormatados(tels);
                        }

                        lista.add(c);
                    }
                }
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao filtrar clientes do banco: " + e.getMessage(), e);
    } finally {
        conexaoJDBC.desconectar();
    }
    return lista;
}

}
