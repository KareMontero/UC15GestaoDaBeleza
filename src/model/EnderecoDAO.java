package model;

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnderecoDAO {

    public int adicionar(Endereco end) {
        String sql = "INSERT INTO endereco (logradouro, numero, bairro, enderecoComplemento, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?)";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();
        int idGerado = 0;

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {

                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                stmt.setString(1, end.getLogradouro());
                stmt.setString(2, end.getNumero());
                stmt.setString(3, end.getBairro());
                stmt.setString(4, end.getEnderecoComplemento());
                stmt.setString(5, end.getCidade());
                stmt.setString(6, end.getEstado());
                stmt.setString(7, end.getCep());

                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                }

                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar endereço: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
        return idGerado;
    }
}
