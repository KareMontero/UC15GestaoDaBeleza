package model;

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;

public class FuncionarioDAO {

    public void Adicionar(Funcionario func, List<Telefone> listaTelefones) {

        String sqlFuncionario = "INSERT INTO funcionario (nomeFuncionario, cpf, dataNascimento, email, "
                + "funcao, carteiraTrabalho, disponibilidadeDeHorario, valorDoServico, "
                + "ativo, dataCadastro, fk_endereco) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlTelefone = "INSERT INTO telefone (telefoneTipo, ddd, telefone, fk_funcionario) VALUES (?, ?, ?, ?)";

        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {

                conn.setAutoCommit(false);

                int idFuncionarioGerado = 0;

                try (PreparedStatement stmt = conn.prepareStatement(sqlFuncionario, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, func.getNomeFuncionario());
                    stmt.setString(2, func.getCpf());

                    if (func.getDataNascimento() == null) {
                        stmt.setNull(3, java.sql.Types.DATE);
                    } else {
                        stmt.setDate(3, new java.sql.Date(func.getDataNascimento().getTime()));
                    }

                    stmt.setString(4, func.getEmail());
                    stmt.setString(5, func.getFuncao());
                    stmt.setString(6, func.getCarteiraTrabalho());
                    stmt.setString(7, func.getDisponibilidadeDeHorario());
                    stmt.setDouble(8, func.getValorDoServico());
                    stmt.setBoolean(9, func.isAtivo());

                    if (func.getDataCadastro() == null) {
                        stmt.setNull(10, java.sql.Types.DATE);
                    } else {
                        stmt.setDate(10, new java.sql.Date(func.getDataCadastro().getTime()));
                    }

                    if (func.getFkEndereco() == 0) {
                        stmt.setNull(11, java.sql.Types.INTEGER);
                    } else {
                        stmt.setInt(11, func.getFkEndereco());
                    }

                    stmt.executeUpdate();

                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            idFuncionarioGerado = rs.getInt(1);
                        }
                    }
                }

                if (listaTelefones != null && !listaTelefones.isEmpty()) {
                    try (PreparedStatement stmtTel = conn.prepareStatement(sqlTelefone)) {

                        for (Telefone tel : listaTelefones) {

                            stmtTel.setString(1, tel.getTelefoneTipo());
                            stmtTel.setString(2, tel.getDdd());
                            stmtTel.setString(3, tel.getTelefone());
                            stmtTel.setInt(4, idFuncionarioGerado);

                            stmtTel.addBatch();
                        }

                        stmtTel.executeBatch();
                    }
                }

                conn.commit();
                System.out.println("Funcionário e telefone(s) cadastrados com sucesso!");
            }
        } catch (SQLException e) {

            try {
                if (conexaoJDBC.getConexao() != null) {
                    conexaoJDBC.getConexao().rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Erro ao salvar funcionário e telefones: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

    public List<Funcionario> listarTodos() {

        String sql = "SELECT f.*, e.logradouro, e.numero, e.bairro, e.cidade, e.estado "
                + "FROM funcionario f "
                + "LEFT JOIN endereco e ON f.fk_endereco = e.idEndereco "
                + "ORDER BY f.nomeFuncionario";

        List<Funcionario> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                TelefoneDAO telDAO = new TelefoneDAO();

                while (rs.next()) {
                    Funcionario f = new Funcionario();
                    f.setIdFuncionario(rs.getInt("idFuncionario"));
                    f.setNomeFuncionario(rs.getString("nomeFuncionario"));
                    f.setCpf(rs.getString("cpf"));
                    f.setDataNascimento(rs.getDate("dataNascimento"));
                    f.setEmail(rs.getString("email"));
                    f.setFuncao(rs.getString("funcao"));
                    f.setCarteiraTrabalho(rs.getString("carteiraTrabalho"));
                    f.setDataContratacao(rs.getTimestamp("dataContratacao"));
                    f.setDisponibilidadeDeHorario(rs.getString("disponibilidadeDeHorario"));
                    f.setValorDoServico(rs.getDouble("valorDoServico"));
                    f.setAtivo(rs.getBoolean("ativo"));
                    f.setDataCadastro(rs.getDate("dataCadastro"));
                    f.setFkEndereco(rs.getInt("fk_endereco"));

                    String textoEndereco = "Sem endereço";
                    if (rs.getString("logradouro") != null) {
                        textoEndereco = rs.getString("logradouro") + ", " + rs.getString("numero") + " - " + rs.getString("bairro");
                    }

                    List<Telefone> telefonesDoFunc = telDAO.buscarPorFuncionario(f.getIdFuncionario());

                    StringBuilder textoTelefones = new StringBuilder();
                    for (Telefone t : telefonesDoFunc) {
                        if (textoTelefones.length() > 0) {
                            textoTelefones.append(" | ");
                        }
                        textoTelefones.append(t.getTelefoneTipo()).append(": (").append(t.getDdd()).append(") ").append(t.getTelefone());
                    }

                    if (textoTelefones.length() == 0) {
                        textoTelefones.append("Sem telefone");
                    }

                    lista.add(f);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return lista;
    }

    public void excluir(int idFuncionario) {
        String sql = "DELETE FROM funcionario WHERE idFuncionario = ?";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idFuncionario);
                stmt.executeUpdate();
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir funcionário: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }

    public List<Funcionario> pesquisarPorNome(String nomeBusca) {

        String sql = "SELECT * FROM funcionario WHERE nomeFuncionario LIKE ? ORDER BY nomeFuncionario";
        List<Funcionario> lista = new ArrayList<>();
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                    stmt.setString(1, "%" + nomeBusca + "%");

                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            Funcionario f = new Funcionario();
                            f.setIdFuncionario(rs.getInt("idFuncionario"));
                            f.setNomeFuncionario(rs.getString("nomeFuncionario"));
                            f.setCpf(rs.getString("cpf"));
                            f.setDataNascimento(rs.getDate("dataNascimento"));
                            f.setEmail(rs.getString("email"));
                            f.setFuncao(rs.getString("funcao"));
                            f.setCarteiraTrabalho(rs.getString("carteiraTrabalho"));
                            f.setDataContratacao(rs.getTimestamp("dataContratacao"));
                            f.setDisponibilidadeDeHorario(rs.getString("disponibilidadeDeHorario"));
                            f.setValorDoServico(rs.getDouble("valorDoServico"));
                            f.setAtivo(rs.getBoolean("ativo"));
                            f.setDataCadastro(rs.getDate("dataCadastro"));
                            f.setFkEndereco(rs.getInt("fk_endereco"));

                            String textoEndereco = "Sem endereço";
                            if (rs.getString("logradouro") != null) {
                                textoEndereco = rs.getString("logradouro") + ", " + rs.getString("numero") + " - " + rs.getString("bairro");
                            }
                            TelefoneDAO telDAO = new TelefoneDAO();
                            List<Telefone> telefonesDoFunc = telDAO.buscarPorFuncionario(f.getIdFuncionario());

                            StringBuilder textoTelefones = new StringBuilder();
                            for (Telefone t : telefonesDoFunc) {
                                if (textoTelefones.length() > 0) {
                                    textoTelefones.append(" | ");
                                }
                                textoTelefones.append(t.getTelefoneTipo()).append(": (").append(t.getDdd()).append(") ").append(t.getTelefone());
                            }

                            if (textoTelefones.length() == 0) {
                                textoTelefones.append("Sem telefone");
                            }
                            lista.add(f);
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
